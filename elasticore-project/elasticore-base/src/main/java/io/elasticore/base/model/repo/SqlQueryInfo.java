package io.elasticore.base.model.repo;

import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.util.StringUtils;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SqlQueryInfo {

    private String[] JPA_PREDEFIEND_METHODS = new String[] {"findById","findAllById"};

    private String sqlTxt;

    private List<String> columnNameList;
    private List<String> tableList;
    private Set<String> setVarNameList;

    private String targetTableName;
    private boolean isNativeQuery;
    private Entity targetEntity;


    private boolean isSelectQuery = false;


    private Items<Field> setVarFieldItems = Items.create(Field.class);

    private SqlQueryInfo(String sqlTxt, boolean isNativeQuery) {
        this.sqlTxt = sqlTxt;
        this.isNativeQuery = isNativeQuery;
    }

    public static SqlQueryInfo creat(String sqlTxt, boolean isNativeQuery) {
        return new SqlQueryInfo(sqlTxt, isNativeQuery);
    }

    public void initialize(ModelLoaderContext ctx) {


        parseSQL2(this.sqlTxt);

        this.setVarNameList = StringUtils.extractVariablesSet(this.sqlTxt);
        this.columnNameList = StringUtils.extractFirstSelectColumnNames(this.sqlTxt);


        if (tableList == null)
            return;

        this.targetTableName = tableList.get(0);


        if (this.isNativeQuery) {
            this.targetEntity = ctx.getEntityItems().filter(e->e.getTableName().equalsIgnoreCase(targetTableName));
        } else {
            this.targetEntity = ctx.getEntityItems().findByName(targetTableName);
        }

        setVarNameList.stream()
                .forEach(varName -> {
                    Field f = this.targetEntity.getItems().findByName(varName);
                    if(f!=null)
                        this.setVarFieldItems.addItem(f);
                });


        setRepositoryMethodName();
    }

    @SneakyThrows
    private void parseSQL2(String sql) {

        try {

            Statement statement = CCJSqlParserUtil.parse(sql);
            if (statement instanceof Select) {
                isSelectQuery = true;

                Select selectStatement = (Select) statement;

                String methodNm = getMethodName(selectStatement);
                if (methodNm != null)
                    System.err.println(methodNm);

                TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
                this.tableList = tablesNamesFinder.getTableList(selectStatement);
                System.out.println("Tables in the query: " + tableList);

                SelectBody selectBody = selectStatement.getSelectBody();

                if (selectBody instanceof PlainSelect) {
                    PlainSelect plainSelect = (PlainSelect) selectBody;

                    List<SelectItem> selectItems = plainSelect.getSelectItems();

                    // AllColumns

                    Expression expression = plainSelect.getWhere();
                    if (expression instanceof EqualsTo) {
                        // where 1개일때
                        EqualsTo equalsTo = (EqualsTo) expression;
                        Expression e1 = equalsTo.getLeftExpression();
                        Expression e2 = equalsTo.getRightExpression();

                        System.err.println(e1 + " " + e2);
                        //Column a = (Column) equalsTo.getLeftExpression();
                        //Column b = (Column) equalsTo.getRightExpression();
                    } else if (expression instanceof AndExpression) {
                        // 1개 이상일때
                        AndExpression ae = (AndExpression) expression;
                        Expression e1 = ae.getLeftExpression();
                        Expression e2 = ae.getRightExpression();

                        System.err.println(e1 + " " + e2);
                    } else {
                        System.err.println(expression);
                    }


                }


            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


    private String getMethodName(Select selectQuery) {
        StringBuilder sb = new StringBuilder();

        SelectBody selectBody = selectQuery.getSelectBody();
        if (selectBody instanceof PlainSelect) {
            PlainSelect plainSelect = (PlainSelect) selectBody;

            List<SelectItem> selectItems = plainSelect.getSelectItems();
            if (selectItems.size() == 1) {
                SelectItem selectItem = selectItems.get(0);
                if (selectItem instanceof AllColumns) {
                    sb.append("findByAll");


                    Expression expression = plainSelect.getWhere();
                    sb.append("test");

                }
            }
        }
        if (sb.length() == 0)
            return null;

        return sb.toString();
    }

    private String jpaMethodName;

    private boolean isEnableNativeJpaMethod = false;

    public void setRepositoryMethodName() {

        StringBuilder sb = new StringBuilder();

        if(isSelectQuery) {

            if(isUniqueSearch()) {
                sb.append("findBy");
            }else {
                sb.append("findAllBy");
            }

            List<Field> fieldList = setVarFieldItems.getItemList();
            int sz = setVarFieldItems.size();
            for(int i=0;i<sz;i++) {
                Field f = fieldList.get(i);
                String srchField = StringUtils.capitalize(f.getName());
                if(i>0)
                    sb.append("And");
                sb.append(srchField);
            }
            this.jpaMethodName = sb.toString();

            if(!Arrays.stream(JPA_PREDEFIEND_METHODS).anyMatch(this.jpaMethodName::equals))
                this.isEnableNativeJpaMethod = true;

        }
    }

    public String getJpaMethodName() {
        return jpaMethodName;
    }

    public boolean isEnableNativeJpaMethod() {
        return isEnableNativeJpaMethod;
    }

    public Items<Field> getSetVarFieldItems() {
        return setVarFieldItems;
    }


    public Entity getTargetEntity() {
        return this.targetEntity;
    }

    public boolean isUniqueSearch() {
        boolean isUniqueSearch = false;
        for(Field f: this.setVarFieldItems.getItemList()) {
            if(f.isPrimaryKey() || f.isUnique()) {
                isUniqueSearch = true;
            }
        }
        return isUniqueSearch;
    }

    public String getDefaultReturnType() {

        String entityName = this.targetEntity.getIdentity().getName();
        if(isUniqueSearch())
            return "Optional<"+entityName+">";
        else
            return "List<"+entityName+">";
    }


    public String getSqlTxt() {
        return sqlTxt;
    }
}

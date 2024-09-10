package io.elasticore.base.model.repo;

import io.elasticore.base.model.core.BaseECoreModelContext;
import io.elasticore.base.model.core.BaseModelDomain;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.entity.PkField;
import io.elasticore.base.model.listener.ModelObjectListener;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.util.ConsoleLog;
import io.elasticore.base.util.MapWrapper;
import io.elasticore.base.util.StringUtils;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlQueryInfo {

    private String[] JPA_PREDEFIEND_METHODS = new String[]{"findById", "findAllById"};

    private String domainId;

    private String sqlTxt;

    private List<String> columnNameList;
    private List<String> tableList;
    private Set<String> setVarNameList;

    private String targetTableName;
    private boolean isNativeQuery;
    private Entity targetEntity;


    private boolean isSelectQuery = false;

    private boolean isOwnOutputDTO = false;


    private boolean isDynamicQuery = false;

    private DataTransfer outputDataTransfer = null;


    private Items<Field> setVarFieldItems = Items.create(Field.class);


    private MapWrapper repositoryContext;


    private final AtomicInteger whereAndCount = new AtomicInteger(0);


    private Map<String, String> typeMap;

    private boolean pageable;

    private String returnType;
    private String returnParamtype;

    private int selectColumnCount = 0;

    private SqlQueryInfo(String domainId, String sqlTxt, boolean isNativeQuery, boolean pageable
            , MapWrapper repositoryContext, String returnType) {
        this.domainId = domainId;
        this.sqlTxt = sqlTxt;
        this.isNativeQuery = isNativeQuery;
        this.pageable = pageable;
        this.repositoryContext = repositoryContext;
        this.returnType = returnType;
        this.returnParamtype = StringUtils.findParameterType(returnType);


    }

    public static SqlQueryInfo creat(String domainId, String sqlTxt, boolean isNativeQuery, boolean pageable, MapWrapper mapWrapper, String returnType) {
        return new SqlQueryInfo(domainId, sqlTxt, isNativeQuery, pageable, mapWrapper, returnType);
    }

    public static boolean containsIfComment(String input) {
        if (input == null) {
            return false;
        }
        // Regex to match /* if:... */
        String regex = "/\\*\\s*if:[^*]*\\*/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }


    public void initialize(ModelLoaderContext ctx) {

        parseSqlQueryText(this.sqlTxt);

        if (containsIfComment(this.sqlTxt))
            this.isDynamicQuery = true;

        this.setVarNameList = StringUtils.extractVariablesSet(this.sqlTxt);
        this.columnNameList = StringUtils.extractFirstSelectColumnNames(this.sqlTxt);


        if (tableList == null)
            return;

        this.targetTableName = tableList.get(0);


        if (this.isNativeQuery) {
            this.targetEntity = ctx.getEntityItems().filter(e -> e.getTableName().equalsIgnoreCase(targetTableName));
        } else {
            this.targetEntity = ctx.getEntityItems().findByName(targetTableName);
            if (this.targetEntity == null) {
                throw new IllegalArgumentException("NOT FOUND TBL NAME: " + targetTableName);
            }
        }

        setVarNameList.stream()
                .forEach(varName -> {
                    //Field f = this.targetEntity.getItems().findByName(varName);
                    Field f = (Field) this.targetEntity.getAllFieldListMap().get(varName);
                    if (f != null)
                        this.setVarFieldItems.addItem(f);
                });


        setRepositoryMethodName();
    }


    public boolean isDynamicQuery() {
        return this.isDynamicQuery;
    }

    private String getFieldType(String columnName) {
        String type = typeMap.get(columnName);
        if (type == null)
            type = "string";

        return type;
    }

    public boolean isSelectQuery() {
        return this.isSelectQuery;
    }


    public int getSelectColumnCount() {
        return this.selectColumnCount;
    }

    public boolean isOwnOutputDTO() {
        return isOwnOutputDTO;
    }


    public DataTransfer getDataTransfer() {
        if(this.isOwnOutputDTO && this.outputDataTransfer ==null) {
            try {
                return BaseModelDomain.getModelDomain(this.domainId).getModel().getDataTransferModels().findByName(this.returnParamtype);
            }catch (RuntimeException re) {

            }
        }

        return outputDataTransfer;
    }


    private void makeDtoModel(List<SelectItem> selectItems) {

        if (typeMap == null) return;

        if (selectItems == null || selectItems.size() == 0) return;

        Items<Field> items = Items.create(Field.class);

        // AllColumns
        for (SelectItem item : selectItems) {

            if (item instanceof SelectExpressionItem) {
                SelectExpressionItem s = (SelectExpressionItem) item;

                String fieldName = null;
                try {
                    Column c = s.getExpression(Column.class);
                    if (c != null) {
                        fieldName = c.getColumnName();
                    }
                } catch (ClassCastException cce) {
                }

                try {
                    fieldName = s.getAlias().getName();

                } catch (NullPointerException npe) {
                }


                String newNAme = StringUtils.snakeToCamel(fieldName.toLowerCase(Locale.ROOT));
                String type = getFieldType(fieldName);


                Field f = Field.builder().name(newNAme).type(type).build();
                items.addItem(f);
            } else if (item instanceof AllColumns) {

            } else {
                System.err.println("[WARN] CHECK SELECT SQL : " + this.sqlTxt + " " + item.getClass().getName());
            }


        }

        if (items.size() < 1)
            return;

        String outputDtoName = StringUtils.capitalize(repositoryContext.getString("id")) + "Output";

        try {
            if("Object[]".equals(this.returnParamtype)) {
                outputDataTransfer = DataTransfer.create(this.domainId, outputDtoName, items, null);

                ModelObjectListener.getInstance().onCreateDataTransfer(outputDataTransfer);

                this.isOwnOutputDTO = true;
            }

            if(this.returnParamtype!=null && !"Object".equals(this.returnParamtype))
                this.isOwnOutputDTO = true;





        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public String getReturnParamtype() {
        return this.returnParamtype;
    }

    @SneakyThrows
    private void parseSqlQueryText(String sql) {

        try {


            Statement statement = CCJSqlParserUtil.parse(sql);
            if (statement instanceof Select) {

                if (this.isNativeQuery) {
                    this.typeMap = getTypeMap(sql);
                }


                isSelectQuery = true;

                Select selectStatement = (Select) statement;

                String methodNm = getMethodName(selectStatement);


                TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
                this.tableList = tablesNamesFinder.getTableList(selectStatement);
                //ConsoleLog.print("Tables in the query: " + tableList);

                SelectBody selectBody = selectStatement.getSelectBody();

                if (selectBody instanceof PlainSelect) {
                    PlainSelect plainSelect = (PlainSelect) selectBody;

                    List<SelectItem> selectItems = plainSelect.getSelectItems();

                    this.selectColumnCount = selectItems.size();

                    makeDtoModel(selectItems);

                    Expression expression = plainSelect.getWhere();
                    if (expression != null) {
                        expression.accept(new ExpressionVisitorAdapter() {
                            @Override
                            public void visit(AndExpression expr) {

                                whereAndCount.incrementAndGet();
                                super.visit(expr);
                            }
                        });
                    }

                    //System.out.println(whereAndCount.get() + " <<<<<<<<<<<<<<<<<");

                    if (expression instanceof EqualsTo) {
                        // where
                        EqualsTo equalsTo = (EqualsTo) expression;
                        Expression e1 = equalsTo.getLeftExpression();
                        Expression e2 = equalsTo.getRightExpression();

                        System.err.println(e1 + " " + e2);
                        //Column a = (Column) equalsTo.getLeftExpression();
                        //Column b = (Column) equalsTo.getRightExpression();
                    } else if (expression instanceof AndExpression) {
                        // one more
                        AndExpression ae = (AndExpression) expression;


                        Expression e1 = ae.getLeftExpression();
                        Expression e2 = ae.getRightExpression();


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


    private boolean isPredefinedJpaMethodName = false;


    private boolean isJpaQueryAnnotationNeed = true;

    private boolean isEnableNativeJpaMethod = false;

    public void setRepositoryMethodName() {

        if (this.isNativeQuery || this.returnType !=null ) {
            this.jpaMethodName = this.repositoryContext.getString("id");
            return;
        }

        StringBuilder sb = new StringBuilder();

        if (isSelectQuery) {

            List<Field> fieldList = setVarFieldItems.getItemList();


            if (whereAndCount.get() == 0 || whereAndCount.get() == (fieldList.size() - 1)) {
                isJpaQueryAnnotationNeed = false;
            }




            if (isUniqueSearch()) {
                if (isJpaQueryAnnotationNeed)
                    sb.append("getBy");
                else
                    sb.append("findBy");
            } else {
                if (isJpaQueryAnnotationNeed)
                    sb.append("listBy");
                else
                    sb.append("findAllBy");
            }


            int sz = setVarFieldItems.size();
            for (int i = 0; i < sz; i++) {
                Field f = fieldList.get(i);
                if (f.isPrimaryKey())
                    sb.append(this.pkPrefix);
                String srchField = StringUtils.capitalize(f.getName());
                if (i > 0)
                    sb.append("And");
                sb.append(srchField);
            }


            int p = this.sqlTxt.lastIndexOf("order by");
            if (p > 0) {
                String orderTxt = this.sqlTxt.substring(p);
                String[] orderTxtItems = orderTxt.split(" ");

                for (String t : orderTxtItems) {
                    String findName = t;
                    if (t.equalsIgnoreCase("order") || t.equalsIgnoreCase("by")
                            || t.equalsIgnoreCase("asc") || t.equalsIgnoreCase("desc")) {

                    } else {
                        findName = this.targetEntity.findFieldName(t);
                        if( findName ==null)
                            throw new IllegalArgumentException("not found order field: "+t+" SQL: "+orderTxt);
                    }

                    if (findName != null) {
                        sb.append(StringUtils.capitalize(findName));
                    }

                }
            }


            this.jpaMethodName = sb.toString();


            if (Arrays.stream(JPA_PREDEFIEND_METHODS).anyMatch(this.jpaMethodName::equals))
                isPredefinedJpaMethodName = true;

            //this.isEnableNativeJpaMethod = true;


        }
    }

    public boolean isJpaQueryAnnotationNeed() {
        return isJpaQueryAnnotationNeed;
    }

    public boolean isPredefinedJpaMethodName() {
        return isPredefinedJpaMethodName;
    }

    public String getJpaMethodName() {
        return jpaMethodName;
    }

    /*
    public boolean isEnableNativeJpaMethod() {
        return isEnableNativeJpaMethod;
    }*/

    public Items<Field> getSetVarFieldItems() {
        return setVarFieldItems;
    }


    public Entity getTargetEntity() {
        return this.targetEntity;
    }


    private String pkPrefix = "";


    public boolean isUniqueSearch() {

        int pkCount = 0;

        PkField pk = this.targetEntity.findPkField(BaseECoreModelContext.getContext().getDomain());
        if (pk != null) {
            pkCount = pk.getKeyCount();
        }
        if (pkCount > 1) {
            //pkPrefix = this.targetEntity.getIdentity().getName()+"sId";
            pkPrefix = "Id";
        }

        int pkFoundCount = 0;
        boolean isUniqueSearch = false;
        for (Field f : this.setVarFieldItems.getItemList()) {
            if (f.isPrimaryKey() || f.isUnique()) {
                //isUniqueSearch = true;
                pkFoundCount++;
            }
        }

        if (pkFoundCount == pkCount) {
            isUniqueSearch = true;
        }
        return isUniqueSearch;
    }

    public String getDefaultReturnType() {

        String entityName = this.targetEntity.getIdentity().getName();
        if (isUniqueSearch())
            return "Optional<" + entityName + ">";
        else if(!this.isNativeQuery && this.pageable)
            return "Page<" + entityName + ">";
        else
            return "List<" + entityName + ">";
    }


    public String getSqlTxt() {
        return sqlTxt;
    }

    public boolean isNativeQuery() {
        return isNativeQuery;
    }


    private static Map getTypeMap(String sql) {

        Pattern pattern = Pattern.compile("(?<=\\W|^)(\\w+)\\s*/\\*\\s*type\\s*:\\s*(\\w+)\\s*\\*/");

        Map<String, String> typeMap = new HashMap<>();

        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            String columnName = matcher.group(1);
            String typeInfo = matcher.group(2);
            typeMap.put(columnName, typeInfo);
            ConsoleLog.print("Column Name: " + columnName + ", Type Info: " + typeInfo);
        }
        return typeMap;
    }

    public static void main(String[] args) {
        String sql = "SELECT test /* type:int */,  c.age /* type:int */,\n" +
                "      CASE WHEN C.CAR_SEQ IS NULL THEN -1 ELSE C.CAR_SEQ END AS CAR_SEQ  /* type:string */\n" +
                "FROM TEST";

        getTypeMap(sql);

    }
}

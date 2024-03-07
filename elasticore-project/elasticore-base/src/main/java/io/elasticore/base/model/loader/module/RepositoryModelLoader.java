package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.model.repo.Method;
import io.elasticore.base.model.repo.Repository;
import lombok.SneakyThrows;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositoryModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<Repository> {


    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {
        if (map.containsKey(ConstanParam.KEYNAME_REPOSITORY)) {
            loadModel(ctx.getRepositoryItems(), map.get(ConstanParam.KEYNAME_REPOSITORY));
            return true;
        }
        return false;
    }


    public void loadModel(Items<Repository> items, Map<String, LinkedHashMap> entityMap) {
        entityMap.forEach((repoName, value) -> {

            Repository repo = loadRepository(repoName, value);
            items.addItem(repo);
        });
    }


    protected Repository loadRepository(String entityNm, Map<String, Object> entityMap) {

        Map info = (Map) entityMap.get(PROPERTY_INFO);

        Map meta = (Map) entityMap.get(PROPERTY_META);


        List<Map> methods = (List) entityMap.get(PROPERTY_METHODS);

        Items<Method> methodItems = null;

        methodItems = Items.create(Method.class);

        for (Map<String, Object> method : methods) {
            Method methodInfo = loadMethodInfo(method);

            methodItems.addItem(methodInfo);
        }


        System.err.println(methods);

        //return Repository.create(entityNm, methodItems, null);
        return Repository.create(entityNm, methodItems, null);

    }


    /**
     *
     * @param map
     * @return
     */
    private Method loadMethodInfo(Map<String,Object> map) {
        String id = (String) map.get("id");
        String methodName = (String) map.get("name");
        String query = (String) map.get("query");
        parseSQL(query);

        String returnType = (String) map.get("return");



        Field returnField = this.parseFieldLine("return", returnType);
        Map params = (Map) map.get("params");

        Items<Field> fieldItems = this.parseField(params);


        return Method.builder()
                .name(methodName)
                .query(query)
                .returnType(returnType)
                .params(fieldItems)
                .build();
    }


    private String getMethodName(Select selectQuery) {
        StringBuilder sb = new StringBuilder();

        SelectBody selectBody = selectQuery.getSelectBody();
        if (selectBody instanceof PlainSelect) {
            PlainSelect plainSelect = (PlainSelect) selectBody;

            List<SelectItem> selectItems = plainSelect.getSelectItems();
            if(selectItems.size()==1) {
                SelectItem selectItem = selectItems.get(0);
                if(selectItem instanceof AllColumns) {
                    sb.append("findByAll");


                    Expression expression = plainSelect.getWhere();
                    sb.append("test");

                }
            }



        }
        if(sb.length()==0)
            return null;

        return sb.toString();

    }

    @SneakyThrows
    public void parseSQL(String sql) {

        try {

            Statement statement = CCJSqlParserUtil.parse(sql);
            if (statement instanceof Select) {
                Select selectStatement = (Select) statement;

                String methodNm = getMethodName(selectStatement);
                if(methodNm!=null)
                    System.err.println(methodNm);

                TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
                List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
                System.out.println("Tables in the query: " + tableList);

                SelectBody selectBody = selectStatement.getSelectBody();

                if (selectBody instanceof PlainSelect) {
                    PlainSelect plainSelect = (PlainSelect) selectBody;

                    List<SelectItem> selectItems = plainSelect.getSelectItems();

                    // AllColumns

                    Expression expression = plainSelect.getWhere();
                    if (expression instanceof EqualsTo) {
                        EqualsTo equalsTo = (EqualsTo) expression;
                        Expression e1 = equalsTo.getLeftExpression();
                        Expression e2 = equalsTo.getRightExpression();

                        System.err.println(e1 + " " + e2);
                        //Column a = (Column) equalsTo.getLeftExpression();
                        //Column b = (Column) equalsTo.getRightExpression();
                    }
                    else if(expression instanceof AndExpression) {
                        AndExpression ae = (AndExpression) expression;
                        Expression e1 = ae.getLeftExpression();
                        Expression e2 = ae.getRightExpression();

                        System.err.println(e1 + " " + e2);
                    }
                    else {
                        System.err.println(expression);
                    }


                }


            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}

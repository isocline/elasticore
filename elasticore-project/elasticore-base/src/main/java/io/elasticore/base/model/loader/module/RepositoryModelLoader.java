package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.model.repo.Method;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.SqlQueryInfo;
import io.elasticore.base.util.MapWrapper;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositoryModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<Repository> {


    private ModelLoaderContext ctx;

    private List<SqlQueryInfo> sqlQueryInfoList = new ArrayList<>();


    public boolean loadModel(ModelLoaderContext ctx, FileSource source) {
        return loadModel(ctx, source.getInfoMap());
    }

    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {
        this.ctx = ctx;
        if (map.containsKey(ConstanParam.KEYNAME_REPOSITORY)) {
            loadModel(ctx.getRepositoryItems(), map.get(ConstanParam.KEYNAME_REPOSITORY));
            return true;
        }
        return false;
    }


    public void loadModel(Items<Repository> items, Map<String, LinkedHashMap> entityMap) {
        entityMap.forEach((repoName, value) -> {

            Repository repo = loadRepository(repoName, value);
            if(repo!=null)
                items.addItem(repo);
        });
    }


    @Override
    public void completeLoad() {
        for (Entity entity : this.ctx.getEntityItems().getItemList()) {
            String entityNm = entity.getIdentity().getName();
            BaseComponentIdentity identity = BaseComponentIdentity.create(ComponentType.REPOSITORY, ctx.getDomainId(), entityNm);
            if (this.ctx.getRepositoryItems().find(identity) == null) {

                Repository repo = Repository.create(ctx.getDomainId(), entityNm, null, null);
                this.ctx.getRepositoryItems().addItem(repo);
            }
        }

        for (SqlQueryInfo sqlQueryInfo : this.sqlQueryInfoList) {
            sqlQueryInfo.initialize(this.ctx);
        }


    }

    protected Repository loadRepository(String entityNm, Map<String, Object> entityMap) {

        MetaInfo metaInfo = parseMetaInfoObject(entityMap);

        List<Map> methods = (List) entityMap.get(PROPERTY_METHODS);

        if(methods==null)
            return null;

        Items<Method> methodItems = null;

        methodItems = Items.create(Method.class);

        for (Map<String, Object> method : methods) {
            Method methodInfo = loadMethodInfo(method);

            methodItems.addItem(methodInfo);
        }

        //return Repository.create(entityNm, methodItems, null);
        return Repository.create(ctx.getDomainId(), entityNm, methodItems, metaInfo);

    }

    /**
     * @param map
     * @return
     */
    private Method loadMethodInfo(Map<String, Object> map) {
        MapWrapper mapWrapper = new MapWrapper(map);
        String id = mapWrapper.getString("id");
        String methodName = mapWrapper.getString("name");
        boolean isNativeQuery = mapWrapper.getBoolean("nativeQuery", false);
        boolean pageable = mapWrapper.getBoolean("pageable", false);
        String query = mapWrapper.getString("query");
        String returnType = mapWrapper.getString("return");

        SqlQueryInfo queryInfo = null;
        if(query !=null && query.length()>0) {
            queryInfo = SqlQueryInfo.creat(ctx.getDomainId(), query, isNativeQuery , pageable, mapWrapper ,returnType);
            this.sqlQueryInfoList.add(queryInfo);
        }




        /*
        if (returnType != null) {
            Field returnField = this.parseFieldLine("return", returnType);
        }

         */

        Items<Field> fieldItems = null;
        Map params = (Map) map.get("params");
        if (params != null) {
            fieldItems = this.parseField(params);
        }

        return Method.builder()
                .identity(BaseComponentIdentity.create(ComponentType.METHOD,ctx.getDomainId(),id))
                .name(methodName)
                .query(query)
                .returnType(returnType)
                .params(fieldItems)
                .queryInfo(queryInfo)
                .pageable(pageable)
                .build();
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


}

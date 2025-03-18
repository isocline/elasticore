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
import io.elasticore.base.model.port.Port;
import io.elasticore.base.model.repo.Method;
import io.elasticore.base.model.repo.SqlQueryInfo;
import io.elasticore.base.util.MapWrapper;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PortModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<Port> {


    private ModelLoaderContext ctx;

    private List<SqlQueryInfo> sqlQueryInfoList = new ArrayList<>();


    public boolean loadModel(ModelLoaderContext ctx, FileSource source) {
        return loadModel(ctx, source.getInfoMap());
    }

    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {
        this.ctx = ctx;
        boolean isProcess = false;
        if (map.containsKey(ConstanParam.KEYNAME_PORT)) {
            loadModel(ctx.getPortItems(), map.get(ConstanParam.KEYNAME_PORT));
            isProcess = true;
        }

        return isProcess;
    }


    public void loadModel(Items<Port> items, Map<String, LinkedHashMap> entityMap) {
        entityMap.forEach((repoName, value) -> {

            Port repo = loadPort(repoName, value);
            if(repo!=null)
                items.addItem(repo);
        });
    }


    @Override
    public void completeLoad() {
        for (Entity entity : this.ctx.getEntityItems().getItemList()) {
            String entityNm = entity.getIdentity().getName();
            BaseComponentIdentity identity = BaseComponentIdentity.create(ComponentType.PORT, ctx.getDomainId(), entityNm);
            if (this.ctx.getPortItems().find(identity) == null) {

                Port repo = Port.create(ctx.getDomainId(), entityNm, null, null);
                this.ctx.getPortItems().addItem(repo);
            }
        }

        for (SqlQueryInfo sqlQueryInfo : this.sqlQueryInfoList) {
            sqlQueryInfo.initialize(this.ctx);
        }


    }

    protected Port loadPort(String portAdapterName, Map<String, Object> entityMap) {

        MetaInfo metaInfo = parseMetaInfoObject(entityMap, ConstanParam.KEYNAME_PORT, portAdapterName);

        Map<String, LinkedHashMap> methods = (Map<String, LinkedHashMap>) entityMap.get(PROPERTY_METHODS);

        if(methods==null)
            return null;

        Items<Method> methodItems = Items.create(Method.class);

        methods.forEach((methodName, value) -> {
            Method methodInfo = loadMethodInfo(methodName,  value);
            methodItems.addItem(methodInfo);
        });

        return Port.create(ctx.getDomainId(), portAdapterName, methodItems, metaInfo);

    }

    /**
     * @param map
     * @return
     */
    private Method loadMethodInfo(String rootObjName, Map<String, Object> map) {

        MetaInfo metaInfo = parseMetaInfoObject(map, ConstanParam.KEYNAME_METHOD, rootObjName);

        MapWrapper mapWrapper = new MapWrapper(map);
        String id = mapWrapper.getString("id");
        if(id ==null || id.isEmpty())
            id = rootObjName;
        String methodName = mapWrapper.getString("name");

        boolean isDefaultNativeQuery = false;
        if(rootObjName.indexOf("DTO")>0)
            isDefaultNativeQuery = true;

        boolean isNativeQuery = mapWrapper.getBoolean("nativeQuery", isDefaultNativeQuery);
        boolean pageable = mapWrapper.getBoolean("pageable", false);
        String query = mapWrapper.getString("query");
        String returnType = mapWrapper.getString("return");

        boolean isReturnTypeSet = true;
        if(returnType==null) {
            isReturnTypeSet = false;
            if(isNativeQuery)
                returnType = "List<"+rootObjName+">";
        }

        SqlQueryInfo queryInfo = null;
        if(query !=null && query.length()>0) {
            queryInfo = SqlQueryInfo.creat(ctx.getDomainId(), query, isNativeQuery , pageable, mapWrapper ,returnType ,isReturnTypeSet);
            this.sqlQueryInfoList.add(queryInfo);

            if(!queryInfo.isSelectQuery()) {
                returnType = queryInfo.getReturnType();
            }


        }


        String requestType = mapWrapper.getString("input");
        Items<Field> fieldItems = null;
        Object paramsObj = map.get("params");
        if(paramsObj instanceof Map) {
            Map params = (Map) paramsObj;
            if (params != null) {
                fieldItems = this.parseField(params);
            }
        }else if(paramsObj !=null) {
            requestType = paramsObj.toString();
        }


        return Method.builder()
                .identity(BaseComponentIdentity.create(ComponentType.METHOD,ctx.getDomainId(),id))
                .metaInfo(metaInfo)
                .name(methodName)
                .query(query)
                .inputType(requestType)
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

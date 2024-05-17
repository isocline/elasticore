package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataTransferModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<DataTransfer> {


    public boolean loadModel(ModelLoaderContext ctx, FileSource source) {
        return loadModel(ctx, source.getInfoMap());
    }

    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {
        if (map.containsKey(ConstanParam.KEYNAME_DTO)) {
            Map entityMap = map.get(ConstanParam.KEYNAME_DTO);
            loadModel(ctx, ctx.getDataTransferItems(), entityMap);
            return true;
        }
        return false;
    }

    public void loadModel(ModelLoaderContext ctx, Items<DataTransfer> items, Map<String, LinkedHashMap> entityMap) {
        entityMap.forEach((entityNm, value) -> {
            DataTransfer entity = loadDataTransfer(ctx, entityNm, value);
            items.addItem(entity);
        });
    }


    protected DataTransfer loadDataTransfer(ModelLoaderContext ctx, String entityNm, Map<String, Object> entityMap) {

        MetaInfo metaInfo = parseMetaInfoObject(entityMap);

        Map fields = (Map) entityMap.get(PROPERTY_FIELDS);
        Items<Field> fieldItems = null;
        if (fields != null)
            fieldItems = parseField(fields);

        return DataTransfer.create(ctx.getDomainId(), entityNm, fieldItems, metaInfo);

    }

}

package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataTransferModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<DataTransfer> {

    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {
        if (map.containsKey(ConstanParam.KEYNAME_DTO)) {
            Map entityMap = map.get(ConstanParam.KEYNAME_DTO);

            loadModel(ctx.getDataTransferItems(), entityMap);
            return true;
        }
        return false;
    }

    public void loadModel(Items<DataTransfer> items, Map<String, LinkedHashMap> entityMap) {
        entityMap.forEach((entityNm, value) -> {

            System.err.println(entityNm + " parsed[DTO]");
            
            DataTransfer entity = loadDataTransfer(entityNm, value);
            items.addItem(entity);
        });
    }


    protected DataTransfer loadDataTransfer(String entityNm, Map<String, LinkedHashMap> entityMap) {

        Map info = (Map) entityMap.get(PROPERTY_INFO);

        Map meta = (Map) entityMap.get(PROPERTY_META);

        Map fields = (Map) entityMap.get(PROPERTY_FIELDS);
        Items<Field> fieldItems = null;
        if (fields != null)
            fieldItems = parseField(fields);

        return DataTransfer.create(entityNm, fieldItems, null);

    }

}

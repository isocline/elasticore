package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EntityModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<Entity> {


    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {
        if (map.containsKey(ConstanParam.KEYNAME_ENTITY)) {
            Map entityMap = map.get(ConstanParam.KEYNAME_ENTITY);

            loadModel(ctx.getEntityItems(), entityMap);
            return true;
        }
        return false;
    }


    public void loadModel(Items<Entity> items, Map<String, LinkedHashMap> entityMap) {
        entityMap.forEach((entityNm, value) -> {

            //System.err.println(entityNm + " parsed");


            Entity entity = loadEntity(entityNm, value);
            items.addItem(entity);
        });
    }


    protected Entity loadEntity(String entityNm, Map<String, Object> entityMap) {

        MetaInfo metaInfo = parseMetaInfoObject(entityMap);

        Map fields = (Map) entityMap.get(PROPERTY_FIELDS);
        Items<Field> fieldItems = null;
        if (fields != null)
            fieldItems = parseField(fields);

        return Entity.create(entityNm, metaInfo, fieldItems);

    }

}

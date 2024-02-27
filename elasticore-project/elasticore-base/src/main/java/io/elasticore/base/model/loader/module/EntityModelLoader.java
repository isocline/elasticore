package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.loader.ModelLoader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EntityModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<Entity> {

    public void loadModel(Items<Entity> items, Map<String, LinkedHashMap> entityMap) {
        entityMap.forEach((entityNm, value) -> {

            System.err.println(entityNm + " parsed");


            Entity entity = loadEntity(entityNm, value);
            items.addItem(entity);
        });
    }


    protected Entity loadEntity(String entityNm, Map<String, LinkedHashMap> entityMap) {

        Map info = (Map) entityMap.get(PROPERTY_INFO);

        Map meta = (Map) entityMap.get(PROPERTY_META);

        Map fields = (Map) entityMap.get(PROPERTY_FIELDS);
        Items<Field> fieldItems = null;
        if (fields != null)
            fieldItems = parseField(fields);

        return Entity.create(entityNm, fieldItems, null);

    }

}

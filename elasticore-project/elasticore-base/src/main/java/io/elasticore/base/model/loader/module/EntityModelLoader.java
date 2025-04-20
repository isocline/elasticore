package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstantParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntityModelLoader extends AbstractModelLoader implements ConstantParam, ModelLoader<Entity> {

    public boolean loadModel(ModelLoaderContext ctx, FileSource source) {
        return loadModel(ctx, source.getInfoMap());
    }

    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {
        if (map.containsKey(ConstantParam.KEYNAME_ENTITY)) {
            Map entityMap = map.get(ConstantParam.KEYNAME_ENTITY);

            loadModel(ctx, ctx.getEntityItems(), entityMap);
            return true;
        }
        return false;
    }


    public void loadModel(ModelLoaderContext ctx, Items<Entity> items, Map<String, LinkedHashMap> entityMap) {

        this.setModelLoaderContext(ctx);

        entityMap.forEach((entityNm, value) -> {

            //System.err.println(entityNm + " parsed");


            Entity entity = loadEntity(ctx, entityNm, value);
            items.addItem(entity);
        });
    }


    protected Entity loadEntity(ModelLoaderContext ctx, String entityNm, Map<String, Object> entityMap) {

        MetaInfo metaInfo = parseMetaInfoObject(entityMap , ConstantParam.KEYNAME_ENTITY, entityNm);

        Map fields = (Map) entityMap.get(PROPERTY_FIELDS);
        Items<Field> fieldItems = null;
        if (fields != null)
            fieldItems = parseField(fields, entityNm ,metaInfo);

        return Entity.create(ctx.getDomainId(), entityNm, metaInfo, fieldItems);

    }

}

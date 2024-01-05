package io.elasticore.base.model.loader;

import io.elasticore.base.ModelLoader;
import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;

import java.util.ArrayList;
import java.util.List;

public class FileBasedModelLoader implements ModelLoader {


    @Override
    public List<String> getDomainNameList() {
        List<String> domainNmList = new ArrayList();
        domainNmList.add("default");
        return domainNmList;
    }

    @Override
    public ECoreModel load(String domainName) {

        Field f = Field.builder().name("name").type("String").build();
        Field f2 = Field.builder().name("age").type("int").build();

        Items<Field> items = Items.newInstance(Field.class);
        items.addItem(f);
        items.addItem(f2);

        ComponentIdentity identity = BaseComponentIdentity.newInstance(ComponentType.ENTITY, "default", "Person");
        Entity entity = Entity.newInstance(identity, items, null);


        Items<Entity> entityItems = Items.newInstance(Entity.class);
        entityItems.addItem(entity);


        ComponentIdentity id2 = BaseComponentIdentity.newInstance(ComponentType.ENTITY_GROUP, "default", "entity");
        EntityModels entityModels = EntityModels.newInstance(identity, entityItems, null);


        return ECoreModel.builder().entityModels(entityModels).build();
    }
}

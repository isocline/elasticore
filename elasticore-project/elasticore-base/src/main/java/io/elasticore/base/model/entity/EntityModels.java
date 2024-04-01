package io.elasticore.base.model.entity;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.model.core.BaseModelComponentItem;
import io.elasticore.base.model.core.Items;
import lombok.Getter;


@Getter
public class EntityModels {

    private final ComponentIdentity identity;
    private final MetaInfo meta;
    private final ModelComponentItems<Entity> items;

    private EntityModels(ComponentIdentity identity, MetaInfo meta, Items<Entity> items) {
        this.identity = identity;

        this.meta = meta;
        this.items = new BaseModelComponentItem(items);
    }


    public static EntityModels create(String name, MetaInfo meta, Items<Entity> items) {
        ComponentIdentity identity = BaseComponentIdentity.create(ComponentType.ENTITY_GROUP, name);
        return new EntityModels(identity, meta, items);

    }


    public Entity findByName(String name) {
        return this.items.find(BaseComponentIdentity.create(ComponentType.ENTITY, name));
    }


    public Entity findByTableName(String tblName) {
        int sz = this.items.size();
        for(int i=0;i<sz;i++) {
            Entity entity = this.items.get(i);
            if(entity.getTableName().equalsIgnoreCase(tblName)) {
                return entity;
            }
        }
        return null;
    }


}

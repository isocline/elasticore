package io.elasticore.base.model.entity;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.BaseModelComponentItem;
import io.elasticore.base.model.core.Items;

public class EntityModels {

    private final ComponentIdentity identity;
    private final MetaInfo meta;
    private final ModelComponentItems<Entity> items;

    private EntityModels(ComponentIdentity identity, Items<Entity> items, MetaInfo meta) {
        this.identity = identity;

        this.items = new BaseModelComponentItem(items);
        this.meta = meta;
    }

    public static EntityModels newInstance(ComponentIdentity identity, Items<Entity> items, MetaInfo meta) {
        return new EntityModels(identity, items, meta);
    }


}

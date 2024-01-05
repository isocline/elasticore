package io.elasticore.base.model.entity;


import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.AbstractReplaceableModel;
import io.elasticore.base.model.core.BaseModelComponentItem;
import io.elasticore.base.model.core.Items;

public class Entity extends AbstractReplaceableModel {


    private final MetaInfo metaInfo;
    private final ModelComponentItems<Field> items;


    private Entity(ComponentIdentity id, Items<Field> items, MetaInfo metaInfo) {
        super(id);

        this.items = new BaseModelComponentItem(items);
        this.metaInfo = metaInfo;
    }

    public static Entity newInstance(ComponentIdentity id, Items<Field> items, MetaInfo metaInfo) {
        return new Entity(id, items, metaInfo);
    }


    public ModelComponentItems<Field> getItems() {
        return this.items;
    }


    public MetaInfo getMeta() {
        return metaInfo;
    }


}

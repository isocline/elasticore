package io.elasticore.base.model.entity;


import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.AbstractReplaceableModel;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.model.core.BaseModelComponentItem;
import io.elasticore.base.model.core.Items;
import lombok.Getter;

@Getter
public class Entity extends AbstractReplaceableModel {

    private final MetaInfo metaInfo;
    private final ModelComponentItems<Field> items;

    private Entity(ComponentIdentity id, Items<Field> items, MetaInfo metaInfo) {
        super(id);

        this.items = new BaseModelComponentItem(items);
        this.metaInfo = metaInfo;
    }

    public static Entity create(String name, Items<Field> items, MetaInfo metaInfo) {
        BaseComponentIdentity identity = BaseComponentIdentity.create(ComponentType.ENTITY, name);
        return new Entity(identity, items, metaInfo);
    }


    public ModelComponentItems<Field> getItems() {
        return this.items;
    }


    public MetaInfo getMeta() {
        return metaInfo;
    }


}

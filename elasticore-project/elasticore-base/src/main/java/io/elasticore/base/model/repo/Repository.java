package io.elasticore.base.model.repo;


import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.AbstractReplaceableModel;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.model.core.BaseModelComponentItem;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Field;
import lombok.Getter;

@Getter
public class Repository extends AbstractReplaceableModel {

    private final MetaInfo metaInfo;
    private final ModelComponentItems<Method> items;

    private Repository(ComponentIdentity id, Items<Method> items, MetaInfo metaInfo) {
        super(id);

        if(items==null)
            items = Items.create(Method.class);
        this.items = new BaseModelComponentItem(items);
        this.metaInfo = metaInfo;
    }

    public static Repository create(String name, Items<Method> items, MetaInfo metaInfo) {
        BaseComponentIdentity identity = BaseComponentIdentity.create(ComponentType.REPOSITORY, name);
        return new Repository(identity, items, metaInfo);
    }

    public ModelComponentItems<Method> getItems() {
        return this.items;
    }

    public MetaInfo getMeta() {
        return metaInfo;
    }

}

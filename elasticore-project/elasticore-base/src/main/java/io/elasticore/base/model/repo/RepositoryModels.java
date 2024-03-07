package io.elasticore.base.model.repo;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.model.core.BaseModelComponentItem;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import lombok.Getter;


@Getter
public class RepositoryModels {

    private final ComponentIdentity identity;
    private final MetaInfo meta;
    private final ModelComponentItems<Repository> items;

    private RepositoryModels(ComponentIdentity identity, MetaInfo meta, Items<Repository> items) {
        this.identity = identity;

        this.meta = meta;
        this.items = new BaseModelComponentItem(items);
    }


    public static RepositoryModels create(String name, MetaInfo meta, Items<Repository> items) {
        ComponentIdentity identity = BaseComponentIdentity.create(ComponentType.ENTITY_GROUP, name);
        return new RepositoryModels(identity, meta, items);

    }


}

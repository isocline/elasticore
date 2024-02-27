package io.elasticore.base.model.dto;

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
public class DataTransferModels {

    private final ComponentIdentity identity;
    private final MetaInfo meta;
    private final ModelComponentItems<DataTransfer> items;

    private DataTransferModels(ComponentIdentity identity, MetaInfo meta, Items<DataTransfer> items) {
        this.identity = identity;

        this.meta = meta;
        this.items = new BaseModelComponentItem(items);
    }


    public static DataTransferModels create(String name, MetaInfo meta, Items<DataTransfer> items) {
        ComponentIdentity identity = BaseComponentIdentity.create(ComponentType.DTO_GROUP, name);
        return new DataTransferModels(identity, meta, items);

    }


}

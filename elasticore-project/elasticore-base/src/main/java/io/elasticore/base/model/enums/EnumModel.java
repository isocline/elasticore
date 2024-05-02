package io.elasticore.base.model.enums;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.AbstractReplaceableModel;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Field;
import lombok.Getter;


@Getter
public class EnumModel extends AbstractReplaceableModel {

    private final MetaInfo metaInfo;
    private final Items<Field> fieldItems;
    private final Items<EnumConstant> enumConstantItems;

    private EnumModel(ComponentIdentity id, MetaInfo metaInfo, Items<Field> fieldItems, Items<EnumConstant> enumConstantItems) {
        super(id);

        if(metaInfo==null)
            this.metaInfo = MetaInfo.createEmpty();
        else
            this.metaInfo = metaInfo;


        this.fieldItems = fieldItems;
        this.enumConstantItems = enumConstantItems;
    }

    public static EnumModel create(String name, MetaInfo meta, Items<Field> items, Items<EnumConstant> enumConstantItems) {
        ComponentIdentity identity = BaseComponentIdentity.create(ComponentType.ENUM, name);
        return new EnumModel(identity, meta, items, enumConstantItems);
    }

}

package io.elasticore.base.model.dto;


import io.elasticore.base.model.*;
import io.elasticore.base.model.core.*;
import io.elasticore.base.model.entity.Field;
import lombok.Getter;

@Getter
public class DataTransfer extends AbstractDataModel implements MetaInfoModel,DataModelComponent {

    private final MetaInfo metaInfo;
    private ModelComponentItems<Field> items;
    private final Items<Field> orgItems;

    private DataTransfer(ComponentIdentity id, Items<Field> items, MetaInfo metaInfo) {
        super(id);

        this.orgItems = items;
        this.items = new BaseModelComponentItem(items);

        if(metaInfo==null)
            this.metaInfo = MetaInfo.createEmpty();
        else
            this.metaInfo = metaInfo;


        this.setRelationModel();
    }

    public static DataTransfer create(String domainId, String name, Items<Field> items, MetaInfo metaInfo) {
        BaseComponentIdentity identity = BaseComponentIdentity.create(ComponentType.DTO, domainId, name);
        return new DataTransfer(identity, items, metaInfo);
    }


    public ModelComponentItems<Field> getItems() {
        if(!this.items.hasNext())
            this.items = new BaseModelComponentItem(orgItems);
        return this.items;
    }


    public MetaInfo getMeta() {
        return metaInfo;
    }


}

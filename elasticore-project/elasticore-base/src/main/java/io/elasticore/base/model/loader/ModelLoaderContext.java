package io.elasticore.base.model.loader;

import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.repo.Repository;

public class ModelLoaderContext {

    private Items<Entity> entityItems = Items.create(Entity.class);

    private Items<EnumModel> enumModelItems = Items.create(EnumModel.class);

    private Items<DataTransfer> dtoItems = Items.create(DataTransfer.class);

    private Items<Repository> repositoryItems = Items.create(Repository.class);


    ModelLoaderContext() {
    }


    public Items<Entity> getEntityItems() {
        return this.entityItems;
    }

    public Items<EnumModel> getEnumModelItems() {
        return this.enumModelItems;
    }

    public Items<DataTransfer> getDataTransferItems() {
        return this.dtoItems;
    }

    public Items<Repository> getRepositoryItems() {
        return this.repositoryItems;
    }

}

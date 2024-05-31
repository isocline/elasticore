package io.elasticore.base.model.pub.px;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;
import io.elasticore.base.model.pub.jpa.DtoSrcFilePublisher;
import io.elasticore.base.model.pub.jpa.EntitySrcFilePublisher;
import io.elasticore.base.model.pub.jpa.EnumFilePublisher;
import io.elasticore.base.model.pub.jpa.RepositoryFilePublisher;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;

public class PxCodePublihser implements CodePublisher {

    private ECoreModelContext ctx;

    private SourceFileAccessFactory sourceFileAccessFactory;

    @Override
    public void setSrcCodeWriterFactory(SourceFileAccessFactory sourceFileAccessFactory) {

        this.sourceFileAccessFactory = sourceFileAccessFactory;
    }

    @Override
    public SourceFileAccessFactory getSrcFileAccessFactory() {
        return this.sourceFileAccessFactory;
    }

    @Override
    public ECoreModelContext getECoreModelContext() {
        return this.ctx;
    }

    @Override
    public void publish(ECoreModelContext ctx, ModelDomain domain) {
        this.ctx = ctx;

        ECoreModel model = domain.getModel();

        EntityModels entityModels = model.getEntityModels();

        ModelComponentItems<Entity> items = entityModels.getItems();

        EntitySrcFilePublisher entityCodePublisher = new EntitySrcFilePublisher(this);
        for (int i = 0; i < items.size(); i++) {
            Entity entity = items.get(i);

            String name = entity.getIdentity().getName();

            entityCodePublisher.publish(domain, entity);
        }

        DtoSrcFilePublisher dtoSrcFilePublisher = new DtoSrcFilePublisher(this);
        DataTransferModels dataTransferModels = model.getDataTransferModels();
        ModelComponentItems<DataTransfer> dtoItems= dataTransferModels.getItems();
        for (int i = 0; i < dtoItems.size(); i++) {
            DataTransfer dto = dtoItems.get(i);
            dtoSrcFilePublisher.publish(domain, dto);
        }


        EnumFilePublisher enumCodePublisher = new EnumFilePublisher(this);
        EnumModels enumModels = model.getEnumModels();

        while (enumModels.getItems().hasNext()) {
            EnumModel enumModel = enumModels.getItems().next();

            enumCodePublisher.publish(domain, enumModel);
        }


        // Repository
        RepositoryFilePublisher repositoryCodePublisher = new RepositoryFilePublisher(this);
        RepositoryModels repositoryModels = model.getRepositoryModels();

        while (repositoryModels.getItems().hasNext()) {
            Repository repoModel = repositoryModels.getItems().next();

            repositoryCodePublisher.publish(domain, repoModel);
        }
    }

    @Override
    public void errorOnPublish(ModelComponent modelComponent, Throwable e) {

    }
}

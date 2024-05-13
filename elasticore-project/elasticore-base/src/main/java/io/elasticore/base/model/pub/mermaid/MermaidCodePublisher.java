package io.elasticore.base.model.pub.mermaid;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SrcCodeWriterFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;
import io.elasticore.base.model.pub.jpa.*;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;


public class MermaidCodePublisher implements CodePublisher {

    private ECoreModelContext ctx;

    private SrcCodeWriterFactory srcCodeWriterFactory;

    private MermaidCodePublisher() {

    }

    @Override
    public void setSrcCodeWriterFactory(SrcCodeWriterFactory srcCodeWriterFactory) {
        this.srcCodeWriterFactory = srcCodeWriterFactory;
    }

    @Override
    public SrcCodeWriterFactory getSrcCodeWriterFactory() {
        return this.srcCodeWriterFactory;
    }


    @Override
    public void errorOnPublish(ModelComponent modelComponent, Throwable e) {

        // TODO
        e.printStackTrace();
    }

    public static MermaidCodePublisher newInstance() {
        return new MermaidCodePublisher();
    }


    @Override
    public ECoreModelContext getECoreModelContext() {
        return this.ctx;
    }

    public void publish(ECoreModelContext ctx, ModelDomain domain) {

        this.ctx = ctx;

        ECoreModel model = domain.getModel();

        EntityModels entityModels = model.getEntityModels();

        ModelComponentItems<Entity> items = entityModels.getItems();

        // entity
        for (int i = 0; i < items.size(); i++) {
            Entity entity = items.get(i);

            String name = entity.getIdentity().getName();

            System.out.println("UML >> check >> "+name);
        }



    }
}

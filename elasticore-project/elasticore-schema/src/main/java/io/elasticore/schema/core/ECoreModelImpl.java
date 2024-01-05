package io.elasticore.schema.core;

import io.elasticore.schema.ECoreModel;
import io.elasticore.schema.ModelIndentity;
import io.elasticore.schema.Replaceable;
import io.elasticore.schema.entity.EntityModel;
import io.elasticore.schema.io.IoModel;
import io.elasticore.schema.repository.RepositoryModel;
import io.elasticore.schema.service.ServiceModel;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ECoreModelImpl implements ECoreModel {


    private final Map<ModelIndentity, Replaceable<EntityModel>> entityRepo = new LinkedHashMap<>();

    private final Map<ModelIndentity, Replaceable<IoModel>> ioModelRepo = new LinkedHashMap<>();

    private final Map<ModelIndentity, Replaceable<RepositoryModel>> repositoryModelRepo = new LinkedHashMap<>();

    private final Map<ModelIndentity, Replaceable<ServiceModel>> serviceModelRepo = new LinkedHashMap<>();


    void addEntityModel(EntityModel entityModel) {
        entityRepo.put(entityModel.getIdentity(), entityModel);
    }


    public EntityModel getEntityModel(String domain, String name) {
        ModelIndentity indentity = new ModelIndentityImpl(Replaceable.TYPE_ENTITY, domain, name);
        return entityRepo.get(indentity).getObject();
    }


    @Override
    public IoModel getIoModel(String domain, String name) {
        return null;
    }

    @Override
    public RepositoryModel getRepositoryModel(String domain, String name) {
        return null;
    }

    @Override
    public ServiceModel getServiceModel(String domain, String name) {
        return null;
    }

    @Override
    public EntityModel[] getAllEntityModel() {
        return this.entityRepo.values().toArray(new EntityModel[0]);
    }

    @Override
    public IoModel[] getAllIoModel() {
        return this.ioModelRepo.values().toArray(new IoModel[0]);
    }

    @Override
    public RepositoryModel[] getAllRepositoryModel() {
        return this.repositoryModelRepo.values().toArray(new RepositoryModel[0]);
    }

    @Override
    public ServiceModel[] getAllServiceModel() {
        return this.serviceModelRepo.values().toArray(new ServiceModel[0]);
    }
}

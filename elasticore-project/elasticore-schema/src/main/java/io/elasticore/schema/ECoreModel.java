package io.elasticore.schema;

import io.elasticore.schema.entity.EntityModel;
import io.elasticore.schema.io.IoModel;
import io.elasticore.schema.repository.RepositoryModel;
import io.elasticore.schema.service.ServiceModel;

public interface ECoreModel {

    EntityModel getEntityModel(String name);


    IoModel getIoModel(String name);


    RepositoryModel getRepositoryModel( String name);


    ServiceModel getServiceModel( String name);


    EntityModel[] getAllEntityModel();

    IoModel[] getAllIoModel();

    RepositoryModel[] getAllRepositoryModel();

    ServiceModel[] getAllServiceModel();

}

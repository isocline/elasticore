package io.elasticore.schema;

import io.elasticore.schema.entity.EntityModel;
import io.elasticore.schema.io.IoModel;
import io.elasticore.schema.repository.RepositoryModel;
import io.elasticore.schema.service.ServiceModel;

public interface ECoreModel {

    EntityModel getEntityModel(String domain, String name);


    IoModel getIoModel(String domain, String name);


    RepositoryModel getRepositoryModel(String domain, String name);


    ServiceModel getServiceModel(String domain, String name);


    EntityModel[] getAllEntityModel();

    IoModel[] getAllIoModel();

    RepositoryModel[] getAllRepositoryModel();

    ServiceModel[] getAllServiceModel();

}

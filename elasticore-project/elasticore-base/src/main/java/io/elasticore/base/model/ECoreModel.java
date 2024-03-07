package io.elasticore.base.model;

import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.enums.EnumModels;
import io.elasticore.base.model.repo.RepositoryModels;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ECoreModel {

    private EntityModels entityModels;

    private EnumModels enumModels;

    private RepositoryModels repositoryModels;


}

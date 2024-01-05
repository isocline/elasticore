package io.elasticore.schema.repository;

import io.elasticore.schema.core.AbstractReplaceableModel;
import io.elasticore.schema.io.IoModel;

public class RepositoryModel extends AbstractReplaceableModel<RepositoryModel> {


    RepositoryModel(String domain, String name) {
        super(TYPE_REPOSITORY, domain, name);
    }


}

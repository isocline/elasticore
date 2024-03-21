package io.elasticore.base.model;

import java.util.Map;
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

    private Map<String, String> configMap;

    private Map<String, String> namespaceMap;


    public String getConfig(String key) {
        if(configMap==null) return null;

        return configMap.get(key);
    }

    public String getNamespace(String key) {
        if(namespaceMap==null) return null;
        return namespaceMap.get(key);
    }


}

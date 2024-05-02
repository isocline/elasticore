package io.elasticore.base.model;

import java.util.Map;

import io.elasticore.base.model.dto.DataTransferModels;
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

    private DataTransferModels dataTransferModels;

    private RepositoryModels repositoryModels;

    private Map<String, Object> configMap;

    private Map<String, String> namespaceMap;

    public String getConfig(String key, String defualtValue) {
        String val = getConfig(key);
        if(val==null)
            val = defualtValue;

        return val;
    }


    public String getConfig(String key) {
        if (configMap == null) {
            return null;
        }
        String[] keys = key.split("\\.");
        Map<String, Object> currentMap = configMap;

        for (int i = 0; i < keys.length; i++) {
            Object value = currentMap.get(keys[i]);

            if (i < keys.length - 1) {
                if (value instanceof Map) {
                    currentMap = (Map<String, Object>) value;
                } else {
                    return null;
                }
            } else {
                return value instanceof String ? (String) value : null;
            }
        }
        return null;
    }

    public String getNamespace(String key) {
        if(namespaceMap==null) return null;
        return namespaceMap.get(key);
    }


}

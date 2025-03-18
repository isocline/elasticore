package io.elasticore.base.model.loader;

import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.port.Port;
import io.elasticore.base.model.repo.Repository;

import java.util.List;
import java.util.Map;

public class ModelLoaderContext {

    private String domainId;

    private Map<String, Object> configMap;
    private Map<String, String> nsMap;

    private Items<Entity> entityItems = Items.create(Entity.class);

    private Items<EnumModel> enumModelItems = Items.create(EnumModel.class);

    private Items<DataTransfer> dtoItems = Items.create(DataTransfer.class);

    private Items<Repository> repositoryItems = Items.create(Repository.class);

    private Items<Port> portItems = Items.create(Port.class);


    ModelLoaderContext(String domainId, Map<String, Object> configMap, Map<String, String> nsMap) {
        this.domainId = domainId;
        this.configMap = configMap;
        this.nsMap = nsMap;
    }

    public String getDomainId() {
        return this.domainId;
    }

    public String getConfig(String key, String defaultValue) {
        String val = getConfig(key);
        if(val==null)
            return defaultValue;

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

    public List<String> getConfigList(String key) {
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
                return value instanceof List ? (List<String>) value : null;
            }
        }
        return null;
    }

    public String getNamespace(String key) {
        if(nsMap==null) return null;
        return nsMap.get(key);
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

    public Items<Port> getPortItems() {
        return this.portItems;
    }


    public Map<String, Object> getConfigMap() {
        return configMap;
    }

    public Map<String, String> getNsMap() {
        return nsMap;
    }
}

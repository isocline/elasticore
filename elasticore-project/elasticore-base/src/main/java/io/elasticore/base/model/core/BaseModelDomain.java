package io.elasticore.base.model.core;

import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ECoreModel;

import java.util.HashMap;
import java.util.Map;

public final class BaseModelDomain implements ModelDomain {

    private final String name;
    private final ECoreModel model;

    private static Map<String, ModelDomain> modelDomainMap = new HashMap<>();


    private BaseModelDomain(String domainId, ECoreModel model) {
        this.name = domainId;
        this.model = model;

        modelDomainMap.put(name, this);
    }


    /**
     *
     * @param domainId
     * @return
     */
    public static ModelDomain getModelDomain(String domainId) {
        return modelDomainMap.get(domainId);
    }

    public static BaseModelDomain newInstance(String name, ECoreModel model) {
        return new BaseModelDomain(name, model);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ECoreModel getModel() {
        return model;
    }
}

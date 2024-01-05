package io.elasticore.base.model.core;

import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ECoreModel;

public final class BaseModelDomain implements ModelDomain {

    private final String name;
    private final ECoreModel model;

    private BaseModelDomain(String name, ECoreModel model) {
        this.name = name;
        this.model = model;
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

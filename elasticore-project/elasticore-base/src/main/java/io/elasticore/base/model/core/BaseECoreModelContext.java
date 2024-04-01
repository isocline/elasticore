package io.elasticore.base.model.core;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.ModelLoader;
import io.elasticore.base.exeption.ProcessException;
import io.elasticore.base.model.ECoreModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseECoreModelContext implements ECoreModelContext {


    private ModelDomain defaultModelDomain;
    private Map<String, ModelDomain> modelDomainMap = new HashMap();

    private final ModelLoader loader;

    private BaseECoreModelContext(ModelLoader loader) {
        this.loader = loader;
    }

    public void load() {

        RelationshipManager.getInstance().reset();

        modelDomainMap.clear();
        defaultModelDomain = null;

        List<String> domainNameList = this.loader.getDomainNameList();
        for (String domainNm : domainNameList) {
            ECoreModel model = this.loader.load(domainNm);

            ModelDomain modelDomain = BaseModelDomain.newInstance(domainNm, model);
            modelDomainMap.put(domainNm, modelDomain);

            if (defaultModelDomain == null) {
                defaultModelDomain = modelDomain;
            }
        }
    }

    public synchronized static ECoreModelContext getContext(ModelLoader loader) {


        BaseECoreModelContext context = new BaseECoreModelContext(loader);
        context.load();
        return context;


    }


    @Override
    public String[] getDomanNames() {
        return modelDomainMap.keySet().toArray(new String[0]);
    }

    @Override
    public ModelDomain getDomain() {
        return defaultModelDomain;
    }

    @Override
    public ModelDomain getDomain(String name) {
        return modelDomainMap.get(name);
    }

    @Override
    public boolean publish(CodePublisher publisher) throws ProcessException {
        return publisher.publish(this);
    }
}

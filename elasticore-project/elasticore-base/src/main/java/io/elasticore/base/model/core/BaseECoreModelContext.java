package io.elasticore.base.model.core;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.ModelLoader;
import io.elasticore.base.exeption.ProcessException;
import io.elasticore.base.model.DataModelComponent;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.listener.ModelObjectListener;
import io.elasticore.base.util.ConsoleLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseECoreModelContext implements ECoreModelContext {


    private ModelDomain defaultModelDomain;
    private Map<String, ModelDomain> modelDomainMap = new HashMap();


    private static Map<String, ModelDomain> STATIC_DOMAIN_MAP = new HashMap();

    private final ModelLoader loader;

    private static BaseECoreModelContext context;

    private BaseECoreModelContext(ModelLoader loader) {
        this.loader = loader;
    }

    public void load() {

        modelDomainMap.clear();
        defaultModelDomain = null;

        List<String> domainNameList = this.loader.getDomainNameList();
        for (String domainNm : domainNameList) {

            ModelObjectListener.getInstance().clear();

            ECoreModel model = this.loader.load(domainNm);

            ModelDomain modelDomain = BaseModelDomain.newInstance(domainNm, model);
            modelDomainMap.put(domainNm, modelDomain);

            STATIC_DOMAIN_MAP.put(domainNm, modelDomain);

            if (defaultModelDomain == null) {
                defaultModelDomain = modelDomain;
            }
        }
    }

    public synchronized static ECoreModelContext getContext(ModelLoader loader) {
        loader.getDomainNameList().forEach(name -> {
            RelationshipManager.clear(name);
            //ConsoleLog.print("clear releation: "+name);
        });

        context = new BaseECoreModelContext(loader);
        context.load();
        return context;
    }

    public synchronized static ECoreModelContext getContext() {
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
        return STATIC_DOMAIN_MAP.get(name);
    }


    /**
     *
     * @param domainId
     * @param modelName
     * @return
     */
    public DataModelComponent findModelComponent(String domainId, String modelName) {
        try {
            return getDomain(domainId).getModel().findModelByName(modelName);
        }catch (NullPointerException npe) {}
        return null;
    }


    /**
     *
     * @param modelName
     * @return
     */
    public DataModelComponent findModelComponent(String modelName) {
        // ex)  common:Autye
        String[] items = modelName.split(":");
        if(items.length==2) {
            return findModelComponent(items[0], items[1]);
        }
        else if(items.length==1) {
            return getDomain().getModel().findModelByName(modelName);
        }
        return null;
    }
}

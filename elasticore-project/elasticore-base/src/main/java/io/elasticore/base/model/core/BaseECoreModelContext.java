package io.elasticore.base.model.core;

import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.ModelLoader;
import io.elasticore.base.model.DataModelComponent;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ShadowModel;
import io.elasticore.base.model.listener.ModelObjectListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public String[] getInternalDomainNames() {
        return modelDomainMap.keySet().toArray(new String[0]);
    }

    public String[] getAllDomainNames() {
        return STATIC_DOMAIN_MAP.keySet().toArray(new String[0]);
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


    @Override
    public DataModelComponent findModelComponent(String domainId, String modelName, boolean isSearchOtherDdomain) {
        if(isSearchOtherDdomain) {
            ECoreModelContext context = BaseECoreModelContext.getContext();

            String[] allDomainNames = context.getAllDomainNames();

            List<String> sortedDomainNames = Arrays.stream(allDomainNames)
                    .sorted((d1, d2) -> {
                        if (d1.equals(domainId)) return -1; // domainId comes first
                        if (d2.equals(domainId)) return 1;
                        return 0;
                    }).collect(Collectors.toList());

            for(String domainName: sortedDomainNames) {
                DataModelComponent modelComponent = findModelComponent(domainName, modelName);
                if(modelComponent!=null)
                    return modelComponent;
            }

        }else {
            return  findModelComponent(domainId, modelName);
        }
        return null;
    }

    public static ShadowModel findShadowModelByName(String modelName) {
        for(String domainNm : getContext().getAllDomainNames()) {
            ShadowModel shadowModel = getContext().getDomain(domainNm).getModel().getShadowModel(modelName);
            if(shadowModel!=null) {
                return shadowModel;
            }
        }
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
            try {
                DataModelComponent modelByName = null;
                try {
                    ModelDomain domain = getDomain();
                    ECoreModel model = domain.getModel();
                    modelByName = model.findModelByName(modelName);
                }catch (NullPointerException npe) {
                    npe.printStackTrace();
                }

                if (modelByName == null) {
                    String[] domainNames = getAllDomainNames();
                    for (String domainName : domainNames) {
                        DataModelComponent modelByName1 = getDomain(domainName).getModel().findModelByName(modelName);
                        if (modelByName1 != null)
                            return modelByName1;
                    }
                }
            }catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        return null;
    }
}

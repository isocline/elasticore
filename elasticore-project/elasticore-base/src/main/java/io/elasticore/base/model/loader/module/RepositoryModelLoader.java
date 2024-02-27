package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.model.repo.Method;
import io.elasticore.base.model.repo.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<Repository> {


    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {
        if (map.containsKey(ConstanParam.KEYNAME_REPOSITORY)) {
            loadModel(ctx.getRepositoryItems(), map.get(ConstanParam.KEYNAME_REPOSITORY));
            return true;
        }
        return false;
    }


    public void loadModel(Items<Repository> items, Map<String, LinkedHashMap> entityMap) {
        entityMap.forEach((repoName, value) -> {
            Repository repo = loadRepository(repoName, value);
            items.addItem(repo);
        });
    }


    protected Repository loadRepository(String entityNm, Map<String, LinkedHashMap> entityMap) {

        Map info = (Map) entityMap.get(PROPERTY_INFO);

        Map meta = (Map) entityMap.get(PROPERTY_META);


        Items<Method> methodItems = null;


        return Repository.create(entityNm, methodItems, null);

    }

}

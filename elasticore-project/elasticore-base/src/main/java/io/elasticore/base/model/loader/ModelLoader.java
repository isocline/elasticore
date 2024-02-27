package io.elasticore.base.model.loader;

import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.core.Items;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ModelLoader<T extends ModelComponent> {

    void loadModel(Items<T> items, Map<String, LinkedHashMap> enumMap);
}

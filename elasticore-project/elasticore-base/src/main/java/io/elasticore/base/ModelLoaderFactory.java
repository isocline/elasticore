package io.elasticore.base;

import io.elasticore.base.model.loader.FileBasedModelLoader;

public class ModelLoaderFactory {


    public static ModelLoader getLoader(Type type) {
        if (type == Type.DEFAULT) {
            return new FileBasedModelLoader();
        }
        return null;
    }


    public static enum Type {
        DEFAULT,
        DATABASE;
    }

}

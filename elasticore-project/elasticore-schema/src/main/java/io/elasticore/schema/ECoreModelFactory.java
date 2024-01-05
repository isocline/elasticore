package io.elasticore.schema;

public class ECoreModelFactory {

    private static ECoreModelFactory instance;

    public static ECoreModelFactory newInstance() {
        if(instance ==null) {
            instance = new ECoreModelFactory();
        }

        return instance;
    }

    public ECoreModel getECoreMode(){

        return null;
    }
}

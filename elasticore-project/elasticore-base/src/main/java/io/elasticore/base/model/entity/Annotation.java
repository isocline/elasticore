package io.elasticore.base.model.entity;

import lombok.Getter;

import java.util.Properties;

@Getter
public class Annotation {

    public static int TYPE_NO_VALUE = 0;
    public static int TYPE_SINGLE_VALUE = 1;
    public static int TYPE_KEY_VALUE = 2;

    private String name;

    private int type = TYPE_NO_VALUE;


    public String value;

    private Properties properties;

    private Annotation(String name) {
        this.name = name.toLowerCase();
        this.type = TYPE_NO_VALUE;
    }

    private Annotation(String name, String value) {
        this.name = name.toLowerCase();
        this.value = value;
        this.type = TYPE_SINGLE_VALUE;
    }

    private Annotation(String name, Properties properties) {
        this.name = name.toLowerCase();
        this.properties = properties;
        this.type = TYPE_KEY_VALUE;
    }

    public static Annotation create(String name) {
        return new Annotation(name);
    }

    public static Annotation create(String name, String value, Properties properties) {
        if(properties!=null && properties.size()>0)
            return new Annotation(name, properties);
        if(value !=null)
            return new Annotation(name, value);

        return new Annotation(name);
    }

    public String getValue(){
        return this.value;
    }

    public Properties getProperties() {
        return this.properties;
    }
}

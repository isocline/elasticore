package io.isocline.elasticore.schema.service;

public enum ScriptType {


    JAVASCRIPT("js"),
    GROOVY("grooby");

    private final String name;

    ScriptType(String name) {
        this.name = name;
    }
}

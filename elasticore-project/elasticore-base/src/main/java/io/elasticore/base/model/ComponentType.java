package io.elasticore.base.model;

public enum ComponentType {

    ENTITY("entity"),
    ENTITY_GROUP("entityGrp"),


    IO("io"), //dto
    SERVICE("service");

    private final String name;

    ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

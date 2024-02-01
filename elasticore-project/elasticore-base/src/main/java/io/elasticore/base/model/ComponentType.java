package io.elasticore.base.model;

public enum ComponentType {

    ENTITY("entity"),
    FIELD("field"),
    ENTITY_GROUP("entityGrp"),

    ENUM("enumeration"),
    ENUM_GROUP("enumGrp"),


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

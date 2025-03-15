package io.elasticore.base.model;

public enum ComponentType {

    ENTITY("entity"),
    DTO("dto"),
    REPOSITORY("repository"),


    FIELD("field"),
    METHOD("method"),

    ENTITY_GROUP("entityGrp"),
    DTO_GROUP("dtoGrp"),

    ENUM("enumeration"),
    ENUM_GROUP("enumGrp"),

    PORT("port"),
    PORT_GROUP("portGrp"),


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

package io.elasticore.base.model;

public enum ComponentType {

    ENTITY("entity",""),
    DTO("dto","DTO"),
    REPOSITORY("repository","Repository"),


    FIELD("field",""),
    METHOD("method",""),

    ENTITY_GROUP("entityGrp",""),
    DTO_GROUP("dtoGrp",""),

    ENUM("enumeration",""),
    ENUM_GROUP("enumGrp",""),

    PORT("port","PortAdapter"),
    PORT_GROUP("portGrp",""),


    IO("io",""), //dto
    SERVICE("service","CoreService");

    private final String name;

    private final String suffix;

    ComponentType(String name,String suffix) {
        this.name = name;
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public String getSuffix() {
        return suffix;
    }
}

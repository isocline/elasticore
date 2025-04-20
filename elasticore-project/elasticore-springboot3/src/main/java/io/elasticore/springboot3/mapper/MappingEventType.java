package io.elasticore.springboot3.mapper;

public enum MappingEventType {

    BEFORE("BEFORE",""),
    AFTER_ADD_LIST("AFTER_ADD_LIST","");



    private final String name;

    private final String desc;

    MappingEventType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}

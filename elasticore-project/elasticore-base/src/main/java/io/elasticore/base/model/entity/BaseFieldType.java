package io.elasticore.base.model.entity;


import lombok.Getter;

import java.util.stream.Stream;


/**
 *
 */

@Getter
public enum BaseFieldType {


    STRING("String", null, "String"),
    P_INT("int", "int", "Integer"),
    P_LONG("long", "long", "Long"),
    P_FLOAT("float", "float", "Float"),
    P_DOUBLE("double", "double", "Double"),
    P_BOOLEAN("boolean", "boolean", "Boolean"),

    DATETIME("DateTime", null, "java.util.Date"),
    TIME("Time", null, "java.util.Date"),
    DATE("Date", null, "java.util.Date"),

    LIST("List", null, "java.util.List"),

    INTEGER("Integer", "int", "Integer"),
    LONG("Long", "long", "Long"),
    FLOAT("Float", "float", "Float"),
    DOUBLE("Double", "double", "Double"),
    BOOLEAN("boolean", "boolean", "Boolean"),

    LocalDateTime("LocalDateTime", null, "LocalDateTime"),


    UNKNOWN("unknown", null, null);


    private final String name;

    private final String primitiveTypeName;
    private final String wrapperClassName;


    BaseFieldType(String name, String primitiveTypeName, String wrapperClassName) {
        this.name = name;
        this.primitiveTypeName = primitiveTypeName;
        this.wrapperClassName = wrapperClassName;
    }


    public String getName() {
        return name;
    }

    public String getPrimitiveTypeName() {
        return primitiveTypeName;
    }

    public String getWarpperClassName() {
        return wrapperClassName;
    }

    public static BaseFieldType findByName(String name) {
        BaseFieldType findType = null;
        Stream<BaseFieldType> stream = java.util.Arrays.stream(BaseFieldType.values());


        try {
            findType = stream.filter(s -> s.name.equals(name)).findFirst().get();
        } catch (RuntimeException re) {

        }

        if (findType == null) {
            try {
                findType = java.util.Arrays.stream(BaseFieldType.values()).filter(s -> s.name.equalsIgnoreCase(name)).findFirst().get();
            } catch (RuntimeException re) {

            }
        }

        if (findType == null) {
            return UNKNOWN;
        }

        return findType;
    }
}

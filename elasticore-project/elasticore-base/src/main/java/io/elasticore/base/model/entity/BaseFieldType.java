package io.elasticore.base.model.entity;


import lombok.Getter;

import java.util.stream.Stream;


/**
 *
 */

@Getter
public enum BaseFieldType {


    OBJECT("Object", null, "Object" , null),
    STRING("String", null, "String" ,"string"),
    STRINGARRAY("String[]", null, "String[]" ,null),
    INTEGERARRAY("int[]", null, "Integer[]",null),
    P_INT("int", "int", "Integer" ,"int64"),
    P_LONG("long", "long", "Long" ,"uint64"),
    P_FLOAT("float", "float", "Float" ,"float"),
    P_DOUBLE("double", "double", "Double" ,"double"),
    P_BOOLEAN("boolean", "boolean", "Boolean" ,"bool"),

    DATETIME("DateTime", null, "java.time.LocalDateTime" , null),
    TIME("Time", null, "java.time.LocalTime", null),
    DATE("Date", null, "java.time.LocalDate", null),

    LIST("List", null, "java.util.List", null),
    //MAP("Map", null, "java.util.Map"),

    INTEGER("Integer", "int", "Integer", null),
    LONG("Long", "long", "Long", "long"),
    FLOAT("Float", "float", "Float", "float"),
    DOUBLE("Double", "double", "Double", "double"),
    BOOLEAN("boolean", "boolean", "Boolean", "bool"),

    LocalDateTime("LocalDateTime", null, "java.time.LocalDateTime", null),
    LocalDate("LocalDate", null, "java.time.LocalDate", null),
    LocalTime("LocalTime", null, "java.time.LocalTime", null),


    UNKNOWN("unknown", null, null, null);


    private final String name;

    private final String primitiveTypeName;
    private final String wrapperClassName;

    private final String protoType;


    BaseFieldType(String name, String primitiveTypeName, String wrapperClassName, String protoType) {
        this.name = name;
        this.primitiveTypeName = primitiveTypeName;
        this.wrapperClassName = wrapperClassName;
        this.protoType = protoType;
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

package io.elasticore.schema.io;

public enum FieldType {

    STRING("string"),
    INT("int");

    private final String name;

    FieldType(String name) {
        this.name = name;
    }


}

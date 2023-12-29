package io.isocline.elasticore.schema.entity;

public enum ColumnType {

    STRING("string"),
    INT("int"),
    LONG("long"),
    FLOAT("float"),
    DOBLE("double"),
    ;

    private final String name;

    ColumnType(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }
}

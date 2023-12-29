package io.isocline.elasticore.schema.entity;


import lombok.Getter;

@Getter
public final class Column {

    Column(String name, ColumnType type, ColumnConfig config, ColumnInfo info) {
        this.name = name;
        this.type = type;
        this.config = config;
        this.info = info;
    }


    private final String name;

    private final ColumnType type;

    private final ColumnConfig config;

    private final ColumnInfo info;


}

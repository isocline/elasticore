package io.elasticore.schema.entity;

import lombok.Getter;

@Getter
public final class ColumnInfo {

    private final String label;

    private final String description;

    ColumnInfo(String label, String description) {
        this.label = label;
        this.description = description;
    }

}

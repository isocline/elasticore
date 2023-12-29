package io.isocline.elasticore.schema.io;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Field {

    private final String name;

    private final FieldType type;

    private final FieldInfo info;
}

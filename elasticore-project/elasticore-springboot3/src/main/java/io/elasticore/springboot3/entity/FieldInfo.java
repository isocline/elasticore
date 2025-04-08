package io.elasticore.springboot3.entity;

import org.springframework.data.jpa.domain.Specification;

public final class FieldInfo {
    private final String name;
    private final Class<?> type;
    private final Class<?> baseType;

    public FieldInfo(String name, Class<?> type, Class<?> baseType) {
        this.name = name;
        this.type = type;
        this.baseType = baseType;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public Class<?> getBaseType() {
        return baseType;
    }

    public String ASC() {
        return name + "+";
    }

    public String DESC() {
        return name + "-";
    }


    public Specification<Object> where(Op op, Object value) {
        return (root, query, cb) -> op.toPredicate(cb, root.get(name), value);
    }

}
package io.elasticore.springboot3.entity;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public final class FieldInfo<T> {
    private final Class<T> entityType;
    private final String name;
    private final Class<?> type;
    private final Class<?> baseType;

    public FieldInfo(Class<T> entityType, String name, Class<?> type, Class<?> baseType) {
        this.entityType = entityType;
        this.name = name;
        this.type = type;
        this.baseType = baseType;
    }

    public Class<T> getEntityType() {
        return entityType;
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

    /**
     * Returns the ascending sort expression for this field (e.g. "name+").
     */
    public String ASC() {
        return name + "+";
    }

    /**
     * Returns the descending sort expression for this field (e.g. "name-").
     */
    public String DESC() {
        return name + "-";
    }


    /**
     * Builds a Specification for the given operation and value.
     * Empty or null values are not filtered unless checkEmpty is true.
     *
     * @param op    the operation to apply (e.g. equals, like, etc.)
     * @param value the value to filter with
     * @return a Specification for this field
     */
    public Specification<T> where(Op op, Object value) {
        return where(op,value,false);
    }

    /**
     * Builds a Specification for the given operation and value, optionally checking for empty input.
     * If checkEmpty is true and value is null, an empty string, or an empty collection,
     * it returns a Specification that always evaluates to true (cb.conjunction()).
     *
     * @param op         the filtering operation
     * @param value      the value to compare
     * @param checkEmpty whether to ignore empty/null values
     * @return a Specification to be used in a JPA query
     */
    public Specification<T> where(Op op, Object value, boolean checkEmpty) {
        if (checkEmpty && (value == null ||
                (value instanceof String && ((String) value).trim().isEmpty()) ||
                (value instanceof Collection<?> && ((Collection<?>) value).isEmpty()))) {
            return (root, query, cb) -> cb.conjunction();
        }
        return (root, query, cb) -> op.toPredicate(cb, root.get(name), value);
    }

    public Sort.Order getAscOrder() {
        return Sort.Order.asc(this.name);
    }

    public Sort.Order getDescOrder() {
        return Sort.Order.desc(this.name);
    }


}
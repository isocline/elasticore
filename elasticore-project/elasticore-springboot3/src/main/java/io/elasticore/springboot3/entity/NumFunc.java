package io.elasticore.springboot3.entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;

/**
 * Enum representing numeric functions used in JPA Criteria queries.
 * <p>
 * Each enum value maps to a numeric aggregation or transformation function such as
 * MAX, MIN, AVG, COUNT, FLOOR, etc. The enum provides a functional wrapper and the
 * corresponding return type for safe and type-aware usage.
 */
public enum NumFunc {
    MAX(Number.class, (cb, path) -> cb.max((Path<Number>) path)),
    MIN(Number.class, (cb, path) -> cb.min((Path<Number>) path)),
    SUM(Number.class, (cb, path) -> cb.sum((Path<Number>) path)),
    AVG(Double.class, (cb, path) -> cb.avg((Path<Number>) path)),
    ABS(Number.class, (cb, path) -> cb.abs((Path<Number>) path)),
    FLOOR(Number.class, (cb, path) -> cb.floor((Path<Number>) path)),
    CEIL(Number.class, (cb, path) -> cb.ceiling((Path<Number>) path)),
    COUNT(Long.class, (cb, path) -> cb.count(path)),
    COUNT_DISTINCT(Long.class, (cb, path) -> cb.countDistinct(path));

    private final Class<?> resultType;
    private final ExpressionFunction fn;

    NumFunc(Class<?> resultType, ExpressionFunction fn) {
        this.resultType = resultType;
        this.fn = fn;
    }

    /**
     * Returns the expression corresponding to this numeric function applied to a field.
     *
     * @param cb   the CriteriaBuilder instance
     * @param path the field path to apply the function on
     * @param <T>  the expected result type
     * @return the expression to use in the criteria query
     */
    @SuppressWarnings("unchecked")
    public <T> Expression<T> apply(CriteriaBuilder cb, Path<?> path) {
        return (Expression<T>) fn.apply(cb, path);
    }

    /**
     * Gets the expected result type for this numeric function.
     *
     * @param <T> the type parameter
     * @return the result type class
     */
    @SuppressWarnings("unchecked")
    public <T> Class<T> getResultType() {
        return (Class<T>) resultType;
    }
}

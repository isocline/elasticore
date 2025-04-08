package io.elasticore.springboot3.entity;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

import java.util.Collection;
import java.util.List;

public enum Op {
    EQ((cb, path, value) -> cb.equal(path, value)),
    NEQ((cb, path, value) -> cb.notEqual(path, value)),
    LIKE((cb, path, value) -> cb.like((Path<String>) path, "%" + value + "%")),
    NOT_LIKE((cb, path, value) -> cb.notLike((Path<String>) path, "%" + value + "%")),
    GT((cb, path, value) -> {
        Path<Comparable> p = (Path<Comparable>) path;
        return cb.greaterThan(p, (Comparable) value);
    }),
    GTE((cb, path, value) -> {
        Path<? extends Comparable> p = (Path<? extends Comparable>) path;
        return cb.greaterThanOrEqualTo(p, (Comparable) value);
    }),
    LTE((cb, path, value) -> {
        Path<? extends Comparable> p = (Path<? extends Comparable>) path;
        return cb.lessThanOrEqualTo(p, (Comparable) value);
    }),

    LT((cb, path, value) -> {
        Path<? extends Comparable> p = (Path<? extends Comparable>) path;
        return cb.lessThan(p, (Comparable) value);
    }),
    IN((cb, path, value) -> path.in((Collection<?>) value)),
    NOT_IN((cb, path, value) -> cb.not(path.in((Collection<?>) value))),
    IS_NULL((cb, path, value) -> cb.isNull(path)),
    IS_NOT_NULL((cb, path, value) -> cb.isNotNull(path)),
    BETWEEN((cb, path, value) -> {
        List<?> list = (List<?>) value;
        Path<? extends Comparable> compPath = (Path<? extends Comparable>) path;
        return cb.between(compPath, (Comparable) list.get(0), (Comparable) list.get(1));
    });

    private final TriFunction<CriteriaBuilder, Path<?>, Object, Predicate> expr;

    Op(TriFunction<CriteriaBuilder, Path<?>, Object, Predicate> expr) {
        this.expr = expr;
    }

    public Predicate toPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
        return expr.apply(cb, path, value);
    }

    @FunctionalInterface
    public interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
    }
}

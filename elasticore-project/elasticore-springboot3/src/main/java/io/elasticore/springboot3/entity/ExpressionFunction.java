package io.elasticore.springboot3.entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Expression;

/**
 * Functional interface representing a function that creates a JPA Criteria Expression
 * based on a given CriteriaBuilder and Path.
 * <p>
 * This abstraction allows encapsulating functions such as MAX, COUNT, LOWER, etc.
 * that operate on entity fields during query construction.
 */
@FunctionalInterface
public interface ExpressionFunction {
    Expression<?> apply(CriteriaBuilder cb, Path<?> path);
}

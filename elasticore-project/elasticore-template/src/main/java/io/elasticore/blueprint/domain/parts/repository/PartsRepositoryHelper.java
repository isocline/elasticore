//ecd:-1781541365H20250416200627_V1.0
package io.elasticore.blueprint.domain.parts.repository;

import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;


import io.elasticore.springboot3.entity.FieldInfo;
import io.elasticore.springboot3.entity.NumFunc;
import io.elasticore.springboot3.entity.StrFunc;

import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.dto.*;


/**
 * Helper class for managing multiple JPA repositories and providing
 * support functions such as paginated and sorted query creation.
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Getter
@Service
@AllArgsConstructor
public class PartsRepositoryHelper {

    private final EntityManager entityManager;

    private final CatalogRepository catalog;
    
    private final CarModelRepository carModel;
    
    private final CarInfoRepository carInfo;
    
    private final ParamInfoRepository paramInfo;
    
    private final PartGroupRepository partGroup;
    
    private final CarProfileRepository carProfile;
    



    /**
     * Finds the first result of a single field with the given specification and sort.
     *
     * @param field the field to select
     * @param spec the filtering condition
     * @param sort the sort order
     * @return an optional result
     */
    public <T, TResult> Optional<TResult> findFirstValueBy(io.elasticore.springboot3.entity.FieldInfo<T> field, Specification<T> spec, Sort sort) {
        @SuppressWarnings("unchecked")
        List<TResult> result = (List<TResult>) findFieldValueBy(field, spec, sort, 1);
        return result.stream().findFirst();
    }


    /**
     * Finds all values of a single field with the given specification (no sort or limit).
     *
     * @param field the field to select
     * @param spec the filtering condition
     * @return a list of matching values
     */
    public <T> List<?> findFieldValueBy(io.elasticore.springboot3.entity.FieldInfo<T> field, Specification<T> spec) {
        return findFieldValueBy(field,spec,null,Integer.MAX_VALUE);
    }

    /**
     * Finds values of a single field with filtering, optional sorting, and result limit.
     *
     * @param field the field to select
     * @param spec the filtering condition
     * @param sort optional sort
     * @param maxCount maximum number of results to return
     * @return a list of matching values
     */
    public <T> List<?> findFieldValueBy(io.elasticore.springboot3.entity.FieldInfo<T> field, Specification<T> spec, Sort sort, int maxCount) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<?> query = cb.createQuery(field.getType());
        Root<T> root = query.from(field.getEntityType());
        query.select(root.get(field.getName()))
                .where(spec.toPredicate(root, query, cb));
        if (sort != null && sort.isSorted()) {
            query.orderBy(sort.stream()
                    .map(o -> o.isAscending() ? cb.asc(root.get(o.getProperty())) : cb.desc(root.get(o.getProperty())))
                    .toList());
        }
        return entityManager.createQuery(query)
                .setMaxResults(maxCount)
                .getResultList();
    }

    /**
     * Executes a string function (e.g. LOWER, GREATEST) on a field of an entity using JPA Criteria API.
     *
     * @param func  the string function to apply
     * @param field metadata describing the entity field
     * @param spec  optional filter specification
     * @param <E>   the entity type
     * @return the result of the function, or null if no result
     */
    public <E> String findStrFuncValue(StrFunc func, FieldInfo<E> field, Specification<E> spec) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Class<?> resultType = func.getResultType();

        CriteriaQuery<?> cq = cb.createQuery(resultType);
        Root<E> root = cq.from(field.getEntityType());

        if (spec != null) {
            Predicate predicate = spec.toPredicate(root, cq, cb);
            cq.where(predicate);
        }

        cq.select(func.apply(cb, root.get(field.getName())));
        TypedQuery<?> query = entityManager.createQuery(cq);
        List<?> result = query.getResultList();
        return result.isEmpty() ? null : (String) result.get(0);
    }


    /**
     * Executes a numeric function (e.g. MAX, COUNT) on a field of an entity using JPA Criteria API.
     *
     * @param func  the numeric function to apply
     * @param field metadata describing the entity field
     * @param spec  optional filter specification
     * @param <E>   the entity type
     * @return the result of the function, or null if no result
     */
    public <E> Number findNumFuncValue(NumFunc func, FieldInfo<E> field, Specification<E> spec) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Class<?> resultType = func.getResultType();

        CriteriaQuery<?> cq = cb.createQuery(resultType);
        Root<E> root = cq.from(field.getEntityType());

        if (spec != null) {
            Predicate predicate = spec.toPredicate(root, cq, cb);
            cq.where(predicate);
        }

        cq.select(func.apply(cb, root.get(field.getName())));
        TypedQuery<?> query = entityManager.createQuery(cq);
        List<?> result = query.getResultList();
        return result.isEmpty() ? null : (Number) result.get(0);
    }

    protected Query createQuery(String sql, boolean isNativeQuery, Class clz, Pageable pageable) {
        if (pageable!=null && pageable.getSort()!=null && pageable.getSort().isSorted()) {
            StringBuilder orderBy = new StringBuilder(" ORDER BY ");
            Iterator<Sort.Order> iterator = pageable.getSort().stream().iterator();
            while (iterator.hasNext()) {
                Sort.Order order = iterator.next();
                String properyNm = order.getProperty();
                if(isNativeQuery)
                    properyNm = io.elasticore.base.util.StringUtils.camelToSnake(properyNm);
                orderBy.append(properyNm)
                        .append(" ")
                        .append(order.getDirection().isAscending() ? "ASC" : "DESC");
                if (iterator.hasNext()) {
                    orderBy.append(", ");
                }
            }
            sql = sql+" "+orderBy.toString();
        }
        Query query = null;
        if(isNativeQuery) {
            query = entityManager.createNativeQuery(sql);
        }else{
            query = entityManager.createQuery(sql, clz);
        }
        if(pageable!=null) {
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }
        return query;
    }
}




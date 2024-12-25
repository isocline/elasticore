//ecd:-138801240H20241224183121_V1.0
package com.test.service;

import com.test.entity.*;
import com.test.dto.*;
import com.test.repository.*;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleCoreService {

    protected final A0RepositoryHelper helper;


    /**
     * Retrieves all tArticle entities, converts them to ArticleDTO objects, and returns them as a list.
     *
     * @return a list of ArticleDTO objects
     */
    public List<ArticleDTO> findAll() {
        return helper.getArticle().findAll().stream()
                 
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tArticle entities, converts them to ArticleDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of ArticleDTO objects
     */
    public List<ArticleDTO> findAll(Sort sort) {
        return helper.getArticle().findAll(sort).stream()
                 
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes Article entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(ArticleSrchDTO searchDTO) {
        Specification<Article> specification = A0Mapper.toSpec(searchDTO);
        return helper.getArticle().delete(specification);
    }

    @Transactional
    public List<ArticleDTO> findBySearch(ArticleSrchDTO searchDTO) {
        Specification<Article> specification = A0Mapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getArticle().findAll(specification).stream()
                        
                        .map(A0Mapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getArticle().findAll(specification, sort).stream()
                
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a Article entity by its ID and converts it to a ArticleDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the ArticleDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<ArticleDTO> findById(String id) {
        return helper.getArticle().findById(id).map(A0Mapper::toDTO);
    }


    /**
     * Saves a new Article entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the ArticleDTO to save
     * @return the saved ArticleDTO
     */
    public ArticleDTO save(ArticleDTO dto) {
        Article entity = A0Mapper.toEntity(dto);
        
    

        Article result = helper.getArticle().save(entity);
        return A0Mapper.toDTO(result);
    }


    /**
     * Updates an existing Article entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the ArticleDTO with updated information
     * @return the updated ArticleDTO
     */
    public ArticleDTO update(ArticleDTO dto) {
        Article entity = helper.getArticle().findById(dto.getId()).orElse(null);
        if(entity==null)
            throw new IllegalArgumentException("Invalid ArticleDTO ID");
        A0Mapper.mapping(dto, entity, true);
        
    


        Article result = helper.getArticle().save(entity);
        return A0Mapper.toDTO(result);
    }

    /**
     * Deletes a list of Article entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of Article representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<ArticleDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (ArticleDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a Article entity by its ID.
     *
     * @param id the ID of the Article entity to delete
     * @return true if the Article entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getArticle().deleteById(id);
        return helper.getArticle().existsById(id);
    }




    /**
     * Finds the greatest string value of the specified field in Article entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(ArticleSrchDTO dto, String fieldName) {
        return findStringValue("greatest", fieldName, A0Mapper.toSpec(dto));
    }


    /**
     * Finds a string value from the specified field of the `BaseCarInfo` entity based on the given function name and criteria.
     * <p>
     * This method dynamically constructs a JPA Criteria API query to apply functions like `least`, `greatest`, or `lower`
     * on a specified field. The query results are filtered using a `Specification`.
     * </p>
     *
     * @param funcName  the name of the function to apply on the field. Supported values are:
     *                  <ul>
     *                      <li>`least` - Returns the lexicographically smallest value in the field.</li>
     *                      <li>`greatest` - Returns the lexicographically largest value in the field.</li>
     *                      <li>`lower` - Converts the field value to lowercase.</li>
     *                  </ul>
     * @param fieldName the name of the field to apply the function on. Must be a valid string field in the `BaseCarInfo` entity.
     * @param spec      the {@link Specification} used to filter the query results. Defines the `WHERE` clause conditions.
     * @return the resulting string value after applying the specified function. Returns `null` if no results are found.
     * @throws IllegalArgumentException if the provided `funcName` is not recognized.
     *
     * @see CriteriaBuilder
     * @see CriteriaQuery
     * @see EntityManager
     */
    public String findStringValue(String funcName, String fieldName, Specification spec) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Article> root = cq.from(Article.class);

        Predicate predicate = spec.toPredicate(root, cq, cb);
        cq.where(predicate);

        if("least".equals(funcName))
            cq.select(cb.least(root.get(fieldName).as(String.class)));
        else if("greatest".equals(funcName))
            cq.select(cb.greatest(root.get(fieldName).as(String.class)));
        else if("lower".equals(funcName))
            cq.select(cb.lower(root.get(fieldName).as(String.class)));
        else
            throw new IllegalArgumentException("funcName not recognized");

        TypedQuery<String> query = em.createQuery(cq);
        List<String> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * This method finds a numeric value based on a given function name and field name,
     * utilizing a DTO to create a Specification.
     *
     * @param <T>       the type of the numeric value to return
     * @param funcName  the name of the function to apply (e.g., "max", "min", "sum")
     * @param fieldName the name of the field to apply the function to
     * @param typeClass the class type of the numeric value
     * @param dto       the DTO used to create the Specification
     * @return the result of the function applied to the specified field, or null if no result is found
     * @throws IllegalArgumentException if the function name is not recognized
     */
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,ArticleSrchDTO dto) {
        Specification<Article> spec = A0Mapper.toSpec(dto);
        return findValue(funcName, fieldName, typeClass, spec);
    }


    /**
     * This method finds a numeric value based on a given function name and field name,
     * utilizing a Specification to filter the data.
     *
     * @param <T>       the type of the numeric value to return
     * @param funcName  the name of the function to apply (e.g., "max", "min", "sum")
     * @param fieldName the name of the field to apply the function to
     * @param typeClass the class type of the numeric value
     * @param spec      the Specification used to filter the data
     * @return the result of the function applied to the specified field, or null if no result is found
     * @throws IllegalArgumentException if the function name is not recognized
     */
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<Article> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<Article> root = cq.from(Article.class);

        Predicate predicate = spec.toPredicate(root, cq, cb);
        cq.where(predicate);

        if("max".equals(funcName))
            cq.select(cb.max(root.get(fieldName).as(typeClass)));
        else if("min".equals(funcName))
            cq.select(cb.min(root.get(fieldName).as(typeClass)));
        else if("abs".equals(funcName))
            cq.select(cb.abs(root.get(fieldName).as(typeClass)));
        else if("ceiling".equals(funcName))
            cq.select(cb.ceiling(root.get(fieldName).as(typeClass)));
        else if("floor".equals(funcName))
            cq.select(cb.floor(root.get(fieldName).as(typeClass)));
        else if("sum".equals(funcName))
            cq.select(cb.sum(root.get(fieldName).as(typeClass)));
        else
            throw new IllegalArgumentException("funcName not recognized");

        TypedQuery<T> query = em.createQuery(cq);
        List<T> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}

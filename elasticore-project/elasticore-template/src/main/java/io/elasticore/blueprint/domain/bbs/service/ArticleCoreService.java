//ecd:18509068H20250520114849_V1.0
package io.elasticore.blueprint.domain.bbs.service;

import io.elasticore.blueprint.domain.bbs.entity.*;
import io.elasticore.blueprint.domain.bbs.dto.*;
import io.elasticore.blueprint.domain.bbs.repository.*;

import jakarta.persistence.EntityNotFoundException;
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
import java.lang.reflect.Field;

import io.elasticore.blueprint.domain.bbs.entity.Board;

/**
 * Comprehensive service layer for managing Article entities.
 *
 * Provides full CRUD operations, dynamic search with specification support,
 * pagination, sorting, and advanced query functions such as min/max/sum.
 * Delegates repository operations via BbsRepositoryHelper.
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Service
@AllArgsConstructor
public class ArticleCoreService {

    protected final BbsRepositoryHelper helper;


    /**
     * Retrieves all Article entities, converts them to ArticleDTO objects, and returns them as a list.
     *
     * @return a list of ArticleDTO objects
     */
    public List<ArticleDTO> findAll() {
        return helper.getArticle().findAll().stream()
                 
                .map(BbsMapper::toDTO)
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
                 
                .map(BbsMapper::toDTO)
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
        Specification<Article> specification = BbsMapper.toSpec(searchDTO);
        return helper.getArticle().delete(specification);
    }




    /**
     * Finds a list of Article entities that match the given search criteria
     * and returns them as a list of ArticleDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching ArticleDTO objects
     */
    @Transactional
    public List<ArticleDTO> listBySearch(ArticleSrchDTO searchDTO) {
        Specification<Article> specification = BbsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getArticle().findAll(specification).stream()
                        
                        .map(BbsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getArticle().findAll(specification, sort).stream()
                
                .map(BbsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds Article entities that match the given search criteria and returns them as a paginated list of ArticleDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of ArticleDTO objects
     */
    @Transactional
    public Page<ArticleDTO> findBySearch(ArticleSrchDTO searchDTO) {
        Specification<Article> specification = BbsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<Article> result = helper.getArticle().findAll(specification, pageable);
        return result.map(BbsMapper::toDTO);
    }

    /**
     * Finds the first Article entity that matches the given search criteria
     * and returns it as a ArticleDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching ArticleDTO object, or {@code null} if no match is found
     */
    public ArticleDTO findFirstBySearch(ArticleSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<ArticleDTO> pages= findBySearch(searchDTO);
        List<ArticleDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of Article entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(ArticleSrchDTO searchDTO) {
        Specification<Article> specification = BbsMapper.toSpec(searchDTO);
        return helper.getArticle().count(specification);
    }
    /**
     * Finds a Article entity by its ID and converts it to a ArticleDTO.
     *
     * @param aid the ID of the FaxResult entity
     * @return an Optional containing the ArticleDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<ArticleDTO> findById(String aid) {
        return helper.getArticle().findById(aid).map(BbsMapper::toDTO);
    }


    /**
     * Saves a new Article entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the ArticleDTO to save
     * @return the saved ArticleDTO
     */
    public ArticleDTO save(ArticleDTO dto) {
        Article entity = BbsMapper.toEntity(dto);
        
    

        Article result = helper.getArticle().save(entity);
        return BbsMapper.toDTO(result);
    }


    /**
     * Updates an existing Article entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the ArticleDTO with updated information
     * @return the updated ArticleDTO
     */
    public ArticleDTO update(ArticleDTO dto) {
        Article entity = helper.getArticle().findById(dto.getAid()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid ArticleDTO ID");
        BbsMapper.mapping(dto, entity, true);
        
    


        Article result = helper.getArticle().save(entity);
        return BbsMapper.toDTO(result);
    }

    /**
     * Deletes a list of Article entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of Article representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<ArticleKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (ArticleKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getAid()));
        }
        return result;
    }


    /**
     * Deletes a Article entity by its ID.
     *
     * @param aid the ID of the Article entity to delete
     * @return true if the Article entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String aid) {
        helper.getArticle().deleteById(aid);
        return !helper.getArticle().existsById(aid);
    }





    /**
     * Updates Message entities that match the given search criteria,
     * using non-null fields from the update DTO.
     *
     * @param dto     DTO containing the fields to update (only non-null fields will be applied)
     * @param srchDTO DTO containing search conditions for filtering target entities
     * @return number of records updated
     */
    @Transactional
    public int updateBySpecification(ArticleDTO dto, ArticleSrchDTO srchDTO) {
        return updateBySpecification(dto, BbsMapper.toSpec(srchDTO));
    }

    /**
     * Performs a conditional update using the provided Specification and non-null fields from the DTO.
     * Prevents full table updates by requiring at least one condition.
     *
     * @param dto  DTO containing the fields to update (only non-null fields will be applied)
     * @param spec JPA Specification defining update conditions
     * @return number of records updated
     * @throws IllegalArgumentException if no condition is provided
     */
    @Transactional
    public int updateBySpecification(ArticleDTO dto, Specification<Article> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<Article> update = cb.createCriteriaUpdate(Article.class);
        Root<Article> root = update.from(Article.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(Article.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

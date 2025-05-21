//ecd:435851226H20250520141405_V1.0
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

import io.elasticore.blueprint.domain.bbs.entity.Article;

/**
 * Comprehensive service layer for managing ArticlePart entities.
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
public class ArticlePartCoreService {

    protected final BbsRepositoryHelper helper;


    /**
     * Retrieves all ArticlePart entities, converts them to ArticlePartDTO objects, and returns them as a list.
     *
     * @return a list of ArticlePartDTO objects
     */
    public List<ArticlePartDTO> findAll() {
        return helper.getArticlePart().findAll().stream()
                 
                .map(BbsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tArticlePart entities, converts them to ArticlePartDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of ArticlePartDTO objects
     */
    public List<ArticlePartDTO> findAll(Sort sort) {
        return helper.getArticlePart().findAll(sort).stream()
                 
                .map(BbsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes ArticlePart entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(ArticlePartSrchDTO searchDTO) {
        Specification<ArticlePart> specification = BbsMapper.toSpec(searchDTO);
        return helper.getArticlePart().delete(specification);
    }




    /**
     * Finds a list of ArticlePart entities that match the given search criteria
     * and returns them as a list of ArticlePartDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching ArticlePartDTO objects
     */
    @Transactional
    public List<ArticlePartDTO> listBySearch(ArticlePartSrchDTO searchDTO) {
        Specification<ArticlePart> specification = BbsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getArticlePart().findAll(specification).stream()
                        
                        .map(BbsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getArticlePart().findAll(specification, sort).stream()
                
                .map(BbsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds ArticlePart entities that match the given search criteria and returns them as a paginated list of ArticlePartDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of ArticlePartDTO objects
     */
    @Transactional
    public Page<ArticlePartDTO> findBySearch(ArticlePartSrchDTO searchDTO) {
        Specification<ArticlePart> specification = BbsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<ArticlePart> result = helper.getArticlePart().findAll(specification, pageable);
        return result.map(BbsMapper::toDTO);
    }

    /**
     * Finds the first ArticlePart entity that matches the given search criteria
     * and returns it as a ArticlePartDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching ArticlePartDTO object, or {@code null} if no match is found
     */
    public ArticlePartDTO findFirstBySearch(ArticlePartSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<ArticlePartDTO> pages= findBySearch(searchDTO);
        List<ArticlePartDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of ArticlePart entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(ArticlePartSrchDTO searchDTO) {
        Specification<ArticlePart> specification = BbsMapper.toSpec(searchDTO);
        return helper.getArticlePart().count(specification);
    }
    /**
     * Finds a ArticlePart entity by its ID and converts it to a ArticlePartDTO.
     *
     * @param new ArticlePartIdentity(article ,partnerArticle) the ID of the FaxResult entity
     * @return an Optional containing the ArticlePartDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<ArticlePartDTO> findById(Article article ,Article partnerArticle) {
        return helper.getArticlePart().findById(new ArticlePartIdentity(article ,partnerArticle)).map(BbsMapper::toDTO);
    }


    /**
     * Saves a new ArticlePart entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the ArticlePartDTO to save
     * @return the saved ArticlePartDTO
     */
    public ArticlePartDTO save(ArticlePartDTO dto) {
        ArticlePart entity = BbsMapper.toEntity(dto);
        
        if(dto.getArticleAid()!=null){
            Article item = helper.getArticle().findById(dto.getArticleAid()).orElse(null);
            if(item!=null) entity.setArticle(item);
        }
        if(dto.getPartnerArticleAid()!=null){
            Article item = helper.getArticle().findById(dto.getPartnerArticleAid()).orElse(null);
            if(item!=null) entity.setPartnerArticle(item);
        }
    

        ArticlePart result = helper.getArticlePart().save(entity);
        return BbsMapper.toDTO(result);
    }


    /**
     * Updates an existing ArticlePart entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the ArticlePartDTO with updated information
     * @return the updated ArticlePartDTO
     */
    public ArticlePartDTO update(ArticlePartDTO dto) {
        ArticlePart entity = helper.getArticlePart().findById(new ArticlePartIdentity(dto.getArticle() ,dto.getPartnerArticle())).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid ArticlePartDTO ID");
        BbsMapper.mapping(dto, entity, true);
        
        if(dto.getArticleAid()!=null){
            Article item = helper.getArticle().findById(dto.getArticleAid()).orElse(null);
            if(item!=null) entity.setArticle(item);
        }
        if(dto.getPartnerArticleAid()!=null){
            Article item = helper.getArticle().findById(dto.getPartnerArticleAid()).orElse(null);
            if(item!=null) entity.setPartnerArticle(item);
        }
    


        ArticlePart result = helper.getArticlePart().save(entity);
        return BbsMapper.toDTO(result);
    }

    /**
     * Deletes a list of ArticlePart entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of ArticlePart representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<ArticlePartKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (ArticlePartKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getArticle() ,dto.getPartnerArticle()));
        }
        return result;
    }


    /**
     * Deletes a ArticlePart entity by its ID.
     *
     * @param new ArticlePartIdentity(article ,partnerArticle) the ID of the ArticlePart entity to delete
     * @return true if the ArticlePart entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(Article article ,Article partnerArticle) {
        helper.getArticlePart().deleteById(new ArticlePartIdentity(article ,partnerArticle));
        return !helper.getArticlePart().existsById(new ArticlePartIdentity(article ,partnerArticle));
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
    public int updateBySpecification(ArticlePartDTO dto, ArticlePartSrchDTO srchDTO) {
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
    public int updateBySpecification(ArticlePartDTO dto, Specification<ArticlePart> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<ArticlePart> update = cb.createCriteriaUpdate(ArticlePart.class);
        Root<ArticlePart> root = update.from(ArticlePart.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(ArticlePart.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

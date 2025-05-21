//ecd:1273495379H20250425111000_V1.0
package io.elasticore.blueprint.domain.parts.service;

import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.dto.*;
import io.elasticore.blueprint.domain.parts.repository.*;

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



/**
 * Comprehensive service layer for managing SuggestGroup entities.
 *
 * Provides full CRUD operations, dynamic search with specification support,
 * pagination, sorting, and advanced query functions such as min/max/sum.
 * Delegates repository operations via PartsRepositoryHelper.
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Service
@AllArgsConstructor
public class SuggestGroupCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all SuggestGroup entities, converts them to SuggestGroupDTO objects, and returns them as a list.
     *
     * @return a list of SuggestGroupDTO objects
     */
    public List<SuggestGroupDTO> findAll() {
        return helper.getSuggestGroup().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tSuggestGroup entities, converts them to SuggestGroupDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of SuggestGroupDTO objects
     */
    public List<SuggestGroupDTO> findAll(Sort sort) {
        return helper.getSuggestGroup().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes SuggestGroup entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(SuggestGroupSrchDTO searchDTO) {
        Specification<SuggestGroup> specification = PartsMapper.toSpec(searchDTO);
        return helper.getSuggestGroup().delete(specification);
    }




    /**
     * Finds a list of SuggestGroup entities that match the given search criteria
     * and returns them as a list of SuggestGroupDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching SuggestGroupDTO objects
     */
    @Transactional
    public List<SuggestGroupDTO> listBySearch(SuggestGroupSrchDTO searchDTO) {
        Specification<SuggestGroup> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getSuggestGroup().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getSuggestGroup().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds SuggestGroup entities that match the given search criteria and returns them as a paginated list of SuggestGroupDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of SuggestGroupDTO objects
     */
    @Transactional
    public Page<SuggestGroupDTO> findBySearch(SuggestGroupSrchDTO searchDTO) {
        Specification<SuggestGroup> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<SuggestGroup> result = helper.getSuggestGroup().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first SuggestGroup entity that matches the given search criteria
     * and returns it as a SuggestGroupDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching SuggestGroupDTO object, or {@code null} if no match is found
     */
    public SuggestGroupDTO findFirstBySearch(SuggestGroupSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<SuggestGroupDTO> pages= findBySearch(searchDTO);
        List<SuggestGroupDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of SuggestGroup entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(SuggestGroupSrchDTO searchDTO) {
        Specification<SuggestGroup> specification = PartsMapper.toSpec(searchDTO);
        return helper.getSuggestGroup().count(specification);
    }
    /**
     * Finds a SuggestGroup entity by its ID and converts it to a SuggestGroupDTO.
     *
     * @param new SuggestGroupIdentity(catalogId ,sid) the ID of the FaxResult entity
     * @return an Optional containing the SuggestGroupDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<SuggestGroupDTO> findById(String catalogId ,String sid) {
        return helper.getSuggestGroup().findById(new SuggestGroupIdentity(catalogId ,sid)).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new SuggestGroup entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the SuggestGroupDTO to save
     * @return the saved SuggestGroupDTO
     */
    public SuggestGroupDTO save(SuggestGroupDTO dto) {
        SuggestGroup entity = PartsMapper.toEntity(dto);
        
    

        SuggestGroup result = helper.getSuggestGroup().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing SuggestGroup entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the SuggestGroupDTO with updated information
     * @return the updated SuggestGroupDTO
     */
    public SuggestGroupDTO update(SuggestGroupDTO dto) {
        SuggestGroup entity = helper.getSuggestGroup().findById(new SuggestGroupIdentity(dto.getCatalogId() ,dto.getSid())).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid SuggestGroupDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
    


        SuggestGroup result = helper.getSuggestGroup().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of SuggestGroup entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of SuggestGroup representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<SuggestGroupKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (SuggestGroupKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getCatalogId() ,dto.getSid()));
        }
        return result;
    }


    /**
     * Deletes a SuggestGroup entity by its ID.
     *
     * @param new SuggestGroupIdentity(catalogId ,sid) the ID of the SuggestGroup entity to delete
     * @return true if the SuggestGroup entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String catalogId ,String sid) {
        helper.getSuggestGroup().deleteById(new SuggestGroupIdentity(catalogId ,sid));
        return !helper.getSuggestGroup().existsById(new SuggestGroupIdentity(catalogId ,sid));
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
    public int updateBySpecification(SuggestGroupDTO dto, SuggestGroupSrchDTO srchDTO) {
        return updateBySpecification(dto, PartsMapper.toSpec(srchDTO));
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
    public int updateBySpecification(SuggestGroupDTO dto, Specification<SuggestGroup> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<SuggestGroup> update = cb.createCriteriaUpdate(SuggestGroup.class);
        Root<SuggestGroup> root = update.from(SuggestGroup.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(SuggestGroup.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

//ecd:1262592534H20250416200627_V1.0
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
 * Comprehensive service layer for managing PartGroup entities.
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
public class PartGroupCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all PartGroup entities, converts them to PartGroupDTO objects, and returns them as a list.
     *
     * @return a list of PartGroupDTO objects
     */
    public List<PartGroupDTO> findAll() {
        return helper.getPartGroup().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tPartGroup entities, converts them to PartGroupDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of PartGroupDTO objects
     */
    public List<PartGroupDTO> findAll(Sort sort) {
        return helper.getPartGroup().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes PartGroup entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(PartGroupSrchDTO searchDTO) {
        Specification<PartGroup> specification = PartsMapper.toSpec(searchDTO);
        return helper.getPartGroup().delete(specification);
    }




    /**
     * Finds a list of PartGroup entities that match the given search criteria
     * and returns them as a list of PartGroupDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching PartGroupDTO objects
     */
    @Transactional
    public List<PartGroupDTO> listBySearch(PartGroupSrchDTO searchDTO) {
        Specification<PartGroup> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getPartGroup().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getPartGroup().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds PartGroup entities that match the given search criteria and returns them as a paginated list of PartGroupDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of PartGroupDTO objects
     */
    @Transactional
    public Page<PartGroupDTO> findBySearch(PartGroupSrchDTO searchDTO) {
        Specification<PartGroup> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<PartGroup> result = helper.getPartGroup().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first PartGroup entity that matches the given search criteria
     * and returns it as a PartGroupDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching PartGroupDTO object, or {@code null} if no match is found
     */
    public PartGroupDTO findFirstBySearch(PartGroupSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<PartGroupDTO> pages= findBySearch(searchDTO);
        List<PartGroupDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of PartGroup entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(PartGroupSrchDTO searchDTO) {
        Specification<PartGroup> specification = PartsMapper.toSpec(searchDTO);
        return helper.getPartGroup().count(specification);
    }
    /**
     * Finds a PartGroup entity by its ID and converts it to a PartGroupDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the PartGroupDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<PartGroupDTO> findById(String id) {
        return helper.getPartGroup().findById(id).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new PartGroup entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the PartGroupDTO to save
     * @return the saved PartGroupDTO
     */
    public PartGroupDTO save(PartGroupDTO dto) {
        PartGroup entity = PartsMapper.toEntity(dto);
        
    

        PartGroup result = helper.getPartGroup().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing PartGroup entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the PartGroupDTO with updated information
     * @return the updated PartGroupDTO
     */
    public PartGroupDTO update(PartGroupDTO dto) {
        PartGroup entity = helper.getPartGroup().findById(dto.getId()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid PartGroupDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
    


        PartGroup result = helper.getPartGroup().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of PartGroup entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of PartGroup representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<PartGroupKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (PartGroupKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a PartGroup entity by its ID.
     *
     * @param id the ID of the PartGroup entity to delete
     * @return true if the PartGroup entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getPartGroup().deleteById(id);
        return !helper.getPartGroup().existsById(id);
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
    public int updateBySpecification(PartGroupDTO dto, PartGroupSrchDTO srchDTO) {
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
    public int updateBySpecification(PartGroupDTO dto, Specification<PartGroup> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<PartGroup> update = cb.createCriteriaUpdate(PartGroup.class);
        Root<PartGroup> root = update.from(PartGroup.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(PartGroup.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

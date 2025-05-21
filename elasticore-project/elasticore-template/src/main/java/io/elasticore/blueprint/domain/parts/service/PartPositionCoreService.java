//ecd:-1830869679H20250425111000_V1.0
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
 * Comprehensive service layer for managing PartPosition entities.
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
public class PartPositionCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all PartPosition entities, converts them to PartPositionDTO objects, and returns them as a list.
     *
     * @return a list of PartPositionDTO objects
     */
    public List<PartPositionDTO> findAll() {
        return helper.getPartPosition().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tPartPosition entities, converts them to PartPositionDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of PartPositionDTO objects
     */
    public List<PartPositionDTO> findAll(Sort sort) {
        return helper.getPartPosition().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes PartPosition entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(PartPositionSrchDTO searchDTO) {
        Specification<PartPosition> specification = PartsMapper.toSpec(searchDTO);
        return helper.getPartPosition().delete(specification);
    }




    /**
     * Finds a list of PartPosition entities that match the given search criteria
     * and returns them as a list of PartPositionDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching PartPositionDTO objects
     */
    @Transactional
    public List<PartPositionDTO> listBySearch(PartPositionSrchDTO searchDTO) {
        Specification<PartPosition> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getPartPosition().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getPartPosition().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds PartPosition entities that match the given search criteria and returns them as a paginated list of PartPositionDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of PartPositionDTO objects
     */
    @Transactional
    public Page<PartPositionDTO> findBySearch(PartPositionSrchDTO searchDTO) {
        Specification<PartPosition> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<PartPosition> result = helper.getPartPosition().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first PartPosition entity that matches the given search criteria
     * and returns it as a PartPositionDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching PartPositionDTO object, or {@code null} if no match is found
     */
    public PartPositionDTO findFirstBySearch(PartPositionSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<PartPositionDTO> pages= findBySearch(searchDTO);
        List<PartPositionDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of PartPosition entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(PartPositionSrchDTO searchDTO) {
        Specification<PartPosition> specification = PartsMapper.toSpec(searchDTO);
        return helper.getPartPosition().count(specification);
    }
    /**
     * Finds a PartPosition entity by its ID and converts it to a PartPositionDTO.
     *
     * @param new PartPositionIdentity(id ,number) the ID of the FaxResult entity
     * @return an Optional containing the PartPositionDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<PartPositionDTO> findById(String id ,String number) {
        return helper.getPartPosition().findById(new PartPositionIdentity(id ,number)).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new PartPosition entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the PartPositionDTO to save
     * @return the saved PartPositionDTO
     */
    public PartPositionDTO save(PartPositionDTO dto) {
        PartPosition entity = PartsMapper.toEntity(dto);
        
    

        PartPosition result = helper.getPartPosition().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing PartPosition entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the PartPositionDTO with updated information
     * @return the updated PartPositionDTO
     */
    public PartPositionDTO update(PartPositionDTO dto) {
        PartPosition entity = helper.getPartPosition().findById(new PartPositionIdentity(dto.getId() ,dto.getNumber())).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid PartPositionDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
    


        PartPosition result = helper.getPartPosition().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of PartPosition entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of PartPosition representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<PartPositionKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (PartPositionKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId() ,dto.getNumber()));
        }
        return result;
    }


    /**
     * Deletes a PartPosition entity by its ID.
     *
     * @param new PartPositionIdentity(id ,number) the ID of the PartPosition entity to delete
     * @return true if the PartPosition entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id ,String number) {
        helper.getPartPosition().deleteById(new PartPositionIdentity(id ,number));
        return !helper.getPartPosition().existsById(new PartPositionIdentity(id ,number));
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
    public int updateBySpecification(PartPositionDTO dto, PartPositionSrchDTO srchDTO) {
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
    public int updateBySpecification(PartPositionDTO dto, Specification<PartPosition> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<PartPosition> update = cb.createCriteriaUpdate(PartPosition.class);
        Root<PartPosition> root = update.from(PartPosition.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(PartPosition.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

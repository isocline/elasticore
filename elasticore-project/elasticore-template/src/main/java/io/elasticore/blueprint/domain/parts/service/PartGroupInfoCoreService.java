//ecd:755462306H20250425111000_V1.0
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
 * Comprehensive service layer for managing PartGroupInfo entities.
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
public class PartGroupInfoCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all PartGroupInfo entities, converts them to PartGroupInfoDTO objects, and returns them as a list.
     *
     * @return a list of PartGroupInfoDTO objects
     */
    public List<PartGroupInfoDTO> findAll() {
        return helper.getPartGroupInfo().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tPartGroupInfo entities, converts them to PartGroupInfoDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of PartGroupInfoDTO objects
     */
    public List<PartGroupInfoDTO> findAll(Sort sort) {
        return helper.getPartGroupInfo().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes PartGroupInfo entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(PartGroupInfoSrchDTO searchDTO) {
        Specification<PartGroupInfo> specification = PartsMapper.toSpec(searchDTO);
        return helper.getPartGroupInfo().delete(specification);
    }




    /**
     * Finds a list of PartGroupInfo entities that match the given search criteria
     * and returns them as a list of PartGroupInfoDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching PartGroupInfoDTO objects
     */
    @Transactional
    public List<PartGroupInfoDTO> listBySearch(PartGroupInfoSrchDTO searchDTO) {
        Specification<PartGroupInfo> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getPartGroupInfo().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getPartGroupInfo().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds PartGroupInfo entities that match the given search criteria and returns them as a paginated list of PartGroupInfoDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of PartGroupInfoDTO objects
     */
    @Transactional
    public Page<PartGroupInfoDTO> findBySearch(PartGroupInfoSrchDTO searchDTO) {
        Specification<PartGroupInfo> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<PartGroupInfo> result = helper.getPartGroupInfo().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first PartGroupInfo entity that matches the given search criteria
     * and returns it as a PartGroupInfoDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching PartGroupInfoDTO object, or {@code null} if no match is found
     */
    public PartGroupInfoDTO findFirstBySearch(PartGroupInfoSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<PartGroupInfoDTO> pages= findBySearch(searchDTO);
        List<PartGroupInfoDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of PartGroupInfo entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(PartGroupInfoSrchDTO searchDTO) {
        Specification<PartGroupInfo> specification = PartsMapper.toSpec(searchDTO);
        return helper.getPartGroupInfo().count(specification);
    }
    /**
     * Finds a PartGroupInfo entity by its ID and converts it to a PartGroupInfoDTO.
     *
     * @param new PartGroupInfoIdentity(carId ,groupId) the ID of the FaxResult entity
     * @return an Optional containing the PartGroupInfoDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<PartGroupInfoDTO> findById(String carId ,String groupId) {
        return helper.getPartGroupInfo().findById(new PartGroupInfoIdentity(carId ,groupId)).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new PartGroupInfo entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the PartGroupInfoDTO to save
     * @return the saved PartGroupInfoDTO
     */
    public PartGroupInfoDTO save(PartGroupInfoDTO dto) {
        PartGroupInfo entity = PartsMapper.toEntity(dto);
        
    

        PartGroupInfo result = helper.getPartGroupInfo().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing PartGroupInfo entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the PartGroupInfoDTO with updated information
     * @return the updated PartGroupInfoDTO
     */
    public PartGroupInfoDTO update(PartGroupInfoDTO dto) {
        PartGroupInfo entity = helper.getPartGroupInfo().findById(new PartGroupInfoIdentity(dto.getCarId() ,dto.getGroupId())).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid PartGroupInfoDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
    


        PartGroupInfo result = helper.getPartGroupInfo().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of PartGroupInfo entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of PartGroupInfo representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<PartGroupInfoKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (PartGroupInfoKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getCarId() ,dto.getGroupId()));
        }
        return result;
    }


    /**
     * Deletes a PartGroupInfo entity by its ID.
     *
     * @param new PartGroupInfoIdentity(carId ,groupId) the ID of the PartGroupInfo entity to delete
     * @return true if the PartGroupInfo entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String carId ,String groupId) {
        helper.getPartGroupInfo().deleteById(new PartGroupInfoIdentity(carId ,groupId));
        return !helper.getPartGroupInfo().existsById(new PartGroupInfoIdentity(carId ,groupId));
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
    public int updateBySpecification(PartGroupInfoDTO dto, PartGroupInfoSrchDTO srchDTO) {
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
    public int updateBySpecification(PartGroupInfoDTO dto, Specification<PartGroupInfo> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<PartGroupInfo> update = cb.createCriteriaUpdate(PartGroupInfo.class);
        Root<PartGroupInfo> root = update.from(PartGroupInfo.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(PartGroupInfo.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

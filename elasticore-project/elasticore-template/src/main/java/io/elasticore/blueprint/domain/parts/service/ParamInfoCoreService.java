//ecd:-861813314H20250425111000_V1.0
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
 * Comprehensive service layer for managing ParamInfo entities.
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
public class ParamInfoCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all ParamInfo entities, converts them to ParamInfoDTO objects, and returns them as a list.
     *
     * @return a list of ParamInfoDTO objects
     */
    public List<ParamInfoDTO> findAll() {
        return helper.getParamInfo().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tParamInfo entities, converts them to ParamInfoDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of ParamInfoDTO objects
     */
    public List<ParamInfoDTO> findAll(Sort sort) {
        return helper.getParamInfo().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes ParamInfo entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(ParamInfoSrchDTO searchDTO) {
        Specification<ParamInfo> specification = PartsMapper.toSpec(searchDTO);
        return helper.getParamInfo().delete(specification);
    }




    /**
     * Finds a list of ParamInfo entities that match the given search criteria
     * and returns them as a list of ParamInfoDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching ParamInfoDTO objects
     */
    @Transactional
    public List<ParamInfoDTO> listBySearch(ParamInfoSrchDTO searchDTO) {
        Specification<ParamInfo> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getParamInfo().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getParamInfo().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds ParamInfo entities that match the given search criteria and returns them as a paginated list of ParamInfoDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of ParamInfoDTO objects
     */
    @Transactional
    public Page<ParamInfoDTO> findBySearch(ParamInfoSrchDTO searchDTO) {
        Specification<ParamInfo> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<ParamInfo> result = helper.getParamInfo().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first ParamInfo entity that matches the given search criteria
     * and returns it as a ParamInfoDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching ParamInfoDTO object, or {@code null} if no match is found
     */
    public ParamInfoDTO findFirstBySearch(ParamInfoSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<ParamInfoDTO> pages= findBySearch(searchDTO);
        List<ParamInfoDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of ParamInfo entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(ParamInfoSrchDTO searchDTO) {
        Specification<ParamInfo> specification = PartsMapper.toSpec(searchDTO);
        return helper.getParamInfo().count(specification);
    }
    /**
     * Finds a ParamInfo entity by its ID and converts it to a ParamInfoDTO.
     *
     * @param idx the ID of the FaxResult entity
     * @return an Optional containing the ParamInfoDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<ParamInfoDTO> findById(String idx) {
        return helper.getParamInfo().findById(idx).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new ParamInfo entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the ParamInfoDTO to save
     * @return the saved ParamInfoDTO
     */
    public ParamInfoDTO save(ParamInfoDTO dto) {
        ParamInfo entity = PartsMapper.toEntity(dto);
        
    

        ParamInfo result = helper.getParamInfo().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing ParamInfo entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the ParamInfoDTO with updated information
     * @return the updated ParamInfoDTO
     */
    public ParamInfoDTO update(ParamInfoDTO dto) {
        ParamInfo entity = helper.getParamInfo().findById(dto.getIdx()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid ParamInfoDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
    


        ParamInfo result = helper.getParamInfo().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of ParamInfo entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of ParamInfo representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<ParamInfoKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (ParamInfoKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getIdx()));
        }
        return result;
    }


    /**
     * Deletes a ParamInfo entity by its ID.
     *
     * @param idx the ID of the ParamInfo entity to delete
     * @return true if the ParamInfo entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String idx) {
        helper.getParamInfo().deleteById(idx);
        return !helper.getParamInfo().existsById(idx);
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
    public int updateBySpecification(ParamInfoDTO dto, ParamInfoSrchDTO srchDTO) {
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
    public int updateBySpecification(ParamInfoDTO dto, Specification<ParamInfo> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<ParamInfo> update = cb.createCriteriaUpdate(ParamInfo.class);
        Root<ParamInfo> root = update.from(ParamInfo.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(ParamInfo.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

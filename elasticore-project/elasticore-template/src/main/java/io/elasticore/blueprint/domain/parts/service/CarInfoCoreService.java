//ecd:-1187224574H20250425111000_V1.0
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
 * Comprehensive service layer for managing CarInfo entities.
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
public class CarInfoCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all CarInfo entities, converts them to CarInfoDTO objects, and returns them as a list.
     *
     * @return a list of CarInfoDTO objects
     */
    public List<CarInfoDTO> findAll() {
        return helper.getCarInfo().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tCarInfo entities, converts them to CarInfoDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of CarInfoDTO objects
     */
    public List<CarInfoDTO> findAll(Sort sort) {
        return helper.getCarInfo().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes CarInfo entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(CarInfoSrchDTO searchDTO) {
        Specification<CarInfo> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCarInfo().delete(specification);
    }




    /**
     * Finds a list of CarInfo entities that match the given search criteria
     * and returns them as a list of CarInfoDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching CarInfoDTO objects
     */
    @Transactional
    public List<CarInfoDTO> listBySearch(CarInfoSrchDTO searchDTO) {
        Specification<CarInfo> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getCarInfo().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getCarInfo().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds CarInfo entities that match the given search criteria and returns them as a paginated list of CarInfoDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of CarInfoDTO objects
     */
    @Transactional
    public Page<CarInfoDTO> findBySearch(CarInfoSrchDTO searchDTO) {
        Specification<CarInfo> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<CarInfo> result = helper.getCarInfo().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first CarInfo entity that matches the given search criteria
     * and returns it as a CarInfoDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching CarInfoDTO object, or {@code null} if no match is found
     */
    public CarInfoDTO findFirstBySearch(CarInfoSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<CarInfoDTO> pages= findBySearch(searchDTO);
        List<CarInfoDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of CarInfo entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(CarInfoSrchDTO searchDTO) {
        Specification<CarInfo> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCarInfo().count(specification);
    }
    /**
     * Finds a CarInfo entity by its ID and converts it to a CarInfoDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the CarInfoDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<CarInfoDTO> findById(String id) {
        return helper.getCarInfo().findById(id).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new CarInfo entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the CarInfoDTO to save
     * @return the saved CarInfoDTO
     */
    public CarInfoDTO save(CarInfoDTO dto) {
        CarInfo entity = PartsMapper.toEntity(dto);
        
    

        CarInfo result = helper.getCarInfo().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing CarInfo entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the CarInfoDTO with updated information
     * @return the updated CarInfoDTO
     */
    public CarInfoDTO update(CarInfoDTO dto) {
        CarInfo entity = helper.getCarInfo().findById(dto.getId()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid CarInfoDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
    


        CarInfo result = helper.getCarInfo().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of CarInfo entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of CarInfo representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<CarInfoKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (CarInfoKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a CarInfo entity by its ID.
     *
     * @param id the ID of the CarInfo entity to delete
     * @return true if the CarInfo entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getCarInfo().deleteById(id);
        return !helper.getCarInfo().existsById(id);
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
    public int updateBySpecification(CarInfoDTO dto, CarInfoSrchDTO srchDTO) {
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
    public int updateBySpecification(CarInfoDTO dto, Specification<CarInfo> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<CarInfo> update = cb.createCriteriaUpdate(CarInfo.class);
        Root<CarInfo> root = update.from(CarInfo.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(CarInfo.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

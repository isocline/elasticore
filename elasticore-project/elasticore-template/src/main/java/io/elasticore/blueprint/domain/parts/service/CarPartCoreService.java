//ecd:-1866355860H20250425111000_V1.0
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
 * Comprehensive service layer for managing CarPart entities.
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
public class CarPartCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all CarPart entities, converts them to CarPartDTO objects, and returns them as a list.
     *
     * @return a list of CarPartDTO objects
     */
    public List<CarPartDTO> findAll() {
        return helper.getCarPart().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tCarPart entities, converts them to CarPartDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of CarPartDTO objects
     */
    public List<CarPartDTO> findAll(Sort sort) {
        return helper.getCarPart().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes CarPart entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(CarPartSrchDTO searchDTO) {
        Specification<CarPart> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCarPart().delete(specification);
    }




    /**
     * Finds a list of CarPart entities that match the given search criteria
     * and returns them as a list of CarPartDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching CarPartDTO objects
     */
    @Transactional
    public List<CarPartDTO> listBySearch(CarPartSrchDTO searchDTO) {
        Specification<CarPart> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getCarPart().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getCarPart().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds CarPart entities that match the given search criteria and returns them as a paginated list of CarPartDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of CarPartDTO objects
     */
    @Transactional
    public Page<CarPartDTO> findBySearch(CarPartSrchDTO searchDTO) {
        Specification<CarPart> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<CarPart> result = helper.getCarPart().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first CarPart entity that matches the given search criteria
     * and returns it as a CarPartDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching CarPartDTO object, or {@code null} if no match is found
     */
    public CarPartDTO findFirstBySearch(CarPartSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<CarPartDTO> pages= findBySearch(searchDTO);
        List<CarPartDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of CarPart entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(CarPartSrchDTO searchDTO) {
        Specification<CarPart> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCarPart().count(specification);
    }
    /**
     * Finds a CarPart entity by its ID and converts it to a CarPartDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the CarPartDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<CarPartDTO> findById(String id) {
        return helper.getCarPart().findById(id).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new CarPart entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the CarPartDTO to save
     * @return the saved CarPartDTO
     */
    public CarPartDTO save(CarPartDTO dto) {
        CarPart entity = PartsMapper.toEntity(dto);
        
    

        CarPart result = helper.getCarPart().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing CarPart entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the CarPartDTO with updated information
     * @return the updated CarPartDTO
     */
    public CarPartDTO update(CarPartDTO dto) {
        CarPart entity = helper.getCarPart().findById(dto.getId()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid CarPartDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
    


        CarPart result = helper.getCarPart().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of CarPart entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of CarPart representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<CarPartKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (CarPartKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a CarPart entity by its ID.
     *
     * @param id the ID of the CarPart entity to delete
     * @return true if the CarPart entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getCarPart().deleteById(id);
        return !helper.getCarPart().existsById(id);
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
    public int updateBySpecification(CarPartDTO dto, CarPartSrchDTO srchDTO) {
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
    public int updateBySpecification(CarPartDTO dto, Specification<CarPart> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<CarPart> update = cb.createCriteriaUpdate(CarPart.class);
        Root<CarPart> root = update.from(CarPart.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(CarPart.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

//ecd:-1228670547H20250417104236_V1.0
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

import io.elasticore.blueprint.domain.parts.entity.CarInfo;

/**
 * Comprehensive service layer for managing CarProfile entities.
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
public class CarProfileCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all CarProfile entities, converts them to CarProfileDTO objects, and returns them as a list.
     *
     * @return a list of CarProfileDTO objects
     */
    public List<CarProfileDTO> findAll() {
        return helper.getCarProfile().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tCarProfile entities, converts them to CarProfileDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of CarProfileDTO objects
     */
    public List<CarProfileDTO> findAll(Sort sort) {
        return helper.getCarProfile().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes CarProfile entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(CarProfileSrchDTO searchDTO) {
        Specification<CarProfile> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCarProfile().delete(specification);
    }




    /**
     * Finds a list of CarProfile entities that match the given search criteria
     * and returns them as a list of CarProfileDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching CarProfileDTO objects
     */
    @Transactional
    public List<CarProfileDTO> listBySearch(CarProfileSrchDTO searchDTO) {
        Specification<CarProfile> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getCarProfile().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getCarProfile().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds CarProfile entities that match the given search criteria and returns them as a paginated list of CarProfileDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of CarProfileDTO objects
     */
    @Transactional
    public Page<CarProfileDTO> findBySearch(CarProfileSrchDTO searchDTO) {
        Specification<CarProfile> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<CarProfile> result = helper.getCarProfile().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first CarProfile entity that matches the given search criteria
     * and returns it as a CarProfileDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching CarProfileDTO object, or {@code null} if no match is found
     */
    public CarProfileDTO findFirstBySearch(CarProfileSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<CarProfileDTO> pages= findBySearch(searchDTO);
        List<CarProfileDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of CarProfile entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(CarProfileSrchDTO searchDTO) {
        Specification<CarProfile> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCarProfile().count(specification);
    }
    /**
     * Finds a CarProfile entity by its ID and converts it to a CarProfileDTO.
     *
     * @param vin the ID of the FaxResult entity
     * @return an Optional containing the CarProfileDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<CarProfileDTO> findById(String vin) {
        return helper.getCarProfile().findById(vin).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new CarProfile entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the CarProfileDTO to save
     * @return the saved CarProfileDTO
     */
    public CarProfileDTO save(CarProfileDTO dto) {
        CarProfile entity = PartsMapper.toEntity(dto);
        
        if(dto.getCarInfoId()!=null){
            CarInfo item = helper.getCarInfo().findById(dto.getCarInfoId()).orElse(null);
            if(item!=null) entity.setCarInfo(item);
        }
    

        CarProfile result = helper.getCarProfile().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing CarProfile entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the CarProfileDTO with updated information
     * @return the updated CarProfileDTO
     */
    public CarProfileDTO update(CarProfileDTO dto) {
        CarProfile entity = helper.getCarProfile().findById(dto.getVin()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid CarProfileDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
        if(dto.getCarInfoId()!=null){
            CarInfo item = helper.getCarInfo().findById(dto.getCarInfoId()).orElse(null);
            if(item!=null) entity.setCarInfo(item);
        }
    


        CarProfile result = helper.getCarProfile().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of CarProfile entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of CarProfile representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<CarProfileKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (CarProfileKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getVin()));
        }
        return result;
    }


    /**
     * Deletes a CarProfile entity by its ID.
     *
     * @param vin the ID of the CarProfile entity to delete
     * @return true if the CarProfile entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String vin) {
        helper.getCarProfile().deleteById(vin);
        return !helper.getCarProfile().existsById(vin);
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
    public int updateBySpecification(CarProfileDTO dto, CarProfileSrchDTO srchDTO) {
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
    public int updateBySpecification(CarProfileDTO dto, Specification<CarProfile> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<CarProfile> update = cb.createCriteriaUpdate(CarProfile.class);
        Root<CarProfile> root = update.from(CarProfile.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(CarProfile.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

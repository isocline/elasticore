//ecd:-446380186H20250425111000_V1.0
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

import io.elasticore.blueprint.domain.parts.entity.Catalog;

/**
 * Comprehensive service layer for managing CarModel entities.
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
public class CarModelCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all CarModel entities, converts them to CarModelDTO objects, and returns them as a list.
     *
     * @return a list of CarModelDTO objects
     */
    public List<CarModelDTO> findAll() {
        return helper.getCarModel().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tCarModel entities, converts them to CarModelDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of CarModelDTO objects
     */
    public List<CarModelDTO> findAll(Sort sort) {
        return helper.getCarModel().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes CarModel entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(CarModelSrchDTO searchDTO) {
        Specification<CarModel> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCarModel().delete(specification);
    }




    /**
     * Finds a list of CarModel entities that match the given search criteria
     * and returns them as a list of CarModelDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching CarModelDTO objects
     */
    @Transactional
    public List<CarModelDTO> listBySearch(CarModelSrchDTO searchDTO) {
        Specification<CarModel> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getCarModel().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getCarModel().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds CarModel entities that match the given search criteria and returns them as a paginated list of CarModelDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of CarModelDTO objects
     */
    @Transactional
    public Page<CarModelDTO> findBySearch(CarModelSrchDTO searchDTO) {
        Specification<CarModel> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<CarModel> result = helper.getCarModel().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first CarModel entity that matches the given search criteria
     * and returns it as a CarModelDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching CarModelDTO object, or {@code null} if no match is found
     */
    public CarModelDTO findFirstBySearch(CarModelSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<CarModelDTO> pages= findBySearch(searchDTO);
        List<CarModelDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of CarModel entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(CarModelSrchDTO searchDTO) {
        Specification<CarModel> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCarModel().count(specification);
    }
    /**
     * Finds a CarModel entity by its ID and converts it to a CarModelDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the CarModelDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<CarModelDTO> findById(String id) {
        return helper.getCarModel().findById(id).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new CarModel entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the CarModelDTO to save
     * @return the saved CarModelDTO
     */
    public CarModelDTO save(CarModelDTO dto) {
        CarModel entity = PartsMapper.toEntity(dto);
        
        if(dto.getCatalogCatalogId()!=null){
            Catalog item = helper.getCatalog().findById(dto.getCatalogCatalogId()).orElse(null);
            if(item!=null) entity.setCatalog(item);
        }
    

        CarModel result = helper.getCarModel().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing CarModel entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the CarModelDTO with updated information
     * @return the updated CarModelDTO
     */
    public CarModelDTO update(CarModelDTO dto) {
        CarModel entity = helper.getCarModel().findById(dto.getId()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid CarModelDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
        if(dto.getCatalogCatalogId()!=null){
            Catalog item = helper.getCatalog().findById(dto.getCatalogCatalogId()).orElse(null);
            if(item!=null) entity.setCatalog(item);
        }
    


        CarModel result = helper.getCarModel().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of CarModel entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of CarModel representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<CarModelKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (CarModelKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a CarModel entity by its ID.
     *
     * @param id the ID of the CarModel entity to delete
     * @return true if the CarModel entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getCarModel().deleteById(id);
        return !helper.getCarModel().existsById(id);
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
    public int updateBySpecification(CarModelDTO dto, CarModelSrchDTO srchDTO) {
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
    public int updateBySpecification(CarModelDTO dto, Specification<CarModel> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<CarModel> update = cb.createCriteriaUpdate(CarModel.class);
        Root<CarModel> root = update.from(CarModel.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(CarModel.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

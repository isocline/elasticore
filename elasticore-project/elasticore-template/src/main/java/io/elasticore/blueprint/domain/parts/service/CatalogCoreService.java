//ecd:-1126756463H20250411111444_V1.0
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
 * Comprehensive service layer for managing Catalog entities.
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
public class CatalogCoreService {

    protected final PartsRepositoryHelper helper;


    /**
     * Retrieves all Catalog entities, converts them to CatalogDTO objects, and returns them as a list.
     *
     * @return a list of CatalogDTO objects
     */
    public List<CatalogDTO> findAll() {
        return helper.getCatalog().findAll().stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tCatalog entities, converts them to CatalogDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of CatalogDTO objects
     */
    public List<CatalogDTO> findAll(Sort sort) {
        return helper.getCatalog().findAll(sort).stream()
                 
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes Catalog entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(CatalogSrchDTO searchDTO) {
        Specification<Catalog> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCatalog().delete(specification);
    }




    /**
     * Finds a list of Catalog entities that match the given search criteria
     * and returns them as a list of CatalogDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching CatalogDTO objects
     */
    @Transactional
    public List<CatalogDTO> listBySearch(CatalogSrchDTO searchDTO) {
        Specification<Catalog> specification = PartsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getCatalog().findAll(specification).stream()
                        
                        .map(PartsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getCatalog().findAll(specification, sort).stream()
                
                .map(PartsMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds Catalog entities that match the given search criteria and returns them as a paginated list of CatalogDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of CatalogDTO objects
     */
    @Transactional
    public Page<CatalogDTO> findBySearch(CatalogSrchDTO searchDTO) {
        Specification<Catalog> specification = PartsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<Catalog> result = helper.getCatalog().findAll(specification, pageable);
        return result.map(PartsMapper::toDTO);
    }

    /**
     * Finds the first Catalog entity that matches the given search criteria
     * and returns it as a CatalogDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching CatalogDTO object, or {@code null} if no match is found
     */
    public CatalogDTO findFirstBySearch(CatalogSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<CatalogDTO> pages= findBySearch(searchDTO);
        List<CatalogDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of Catalog entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(CatalogSrchDTO searchDTO) {
        Specification<Catalog> specification = PartsMapper.toSpec(searchDTO);
        return helper.getCatalog().count(specification);
    }
    /**
     * Finds a Catalog entity by its ID and converts it to a CatalogDTO.
     *
     * @param catalogId the ID of the FaxResult entity
     * @return an Optional containing the CatalogDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<CatalogDTO> findById(String catalogId) {
        return helper.getCatalog().findById(catalogId).map(PartsMapper::toDTO);
    }


    /**
     * Saves a new Catalog entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the CatalogDTO to save
     * @return the saved CatalogDTO
     */
    public CatalogDTO save(CatalogDTO dto) {
        Catalog entity = PartsMapper.toEntity(dto);
        
    

        Catalog result = helper.getCatalog().save(entity);
        return PartsMapper.toDTO(result);
    }


    /**
     * Updates an existing Catalog entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the CatalogDTO with updated information
     * @return the updated CatalogDTO
     */
    public CatalogDTO update(CatalogDTO dto) {
        Catalog entity = helper.getCatalog().findById(dto.getCatalogId()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid CatalogDTO ID");
        PartsMapper.mapping(dto, entity, true);
        
    


        Catalog result = helper.getCatalog().save(entity);
        return PartsMapper.toDTO(result);
    }

    /**
     * Deletes a list of Catalog entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of Catalog representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<CatalogKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (CatalogKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getCatalogId()));
        }
        return result;
    }


    /**
     * Deletes a Catalog entity by its ID.
     *
     * @param catalogId the ID of the Catalog entity to delete
     * @return true if the Catalog entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String catalogId) {
        helper.getCatalog().deleteById(catalogId);
        return !helper.getCatalog().existsById(catalogId);
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
    public int updateBySpecification(CatalogDTO dto, CatalogSrchDTO srchDTO) {
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
    public int updateBySpecification(CatalogDTO dto, Specification<Catalog> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<Catalog> update = cb.createCriteriaUpdate(Catalog.class);
        Root<Catalog> root = update.from(Catalog.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(Catalog.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

//ecd:1099751234H20241219144053_V1.0
package com.test.service;

import com.test.entity.*;
import com.test.dto.*;
import com.test.repository.*;

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

@Service
@AllArgsConstructor
public class BaseEntityCoreService {

    protected final A0RepositoryHelper helper;


    /**
     * Retrieves all tBaseEntity entities, converts them to BaseEntityDTO objects, and returns them as a list.
     *
     * @return a list of BaseEntityDTO objects
     */
    public List<BaseEntityDTO> findAll() {
        return helper.getBaseEntity().findAll().stream()
                 
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tBaseEntity entities, converts them to BaseEntityDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of BaseEntityDTO objects
     */
    public List<BaseEntityDTO> findAll(Sort sort) {
        return helper.getBaseEntity().findAll(sort).stream()
                 
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes BaseEntity entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(BaseEntitySrchDTO searchDTO) {
            Specification<BaseEntity> specification = A0Mapper.toSpec(searchDTO);
            return helper.getBaseEntity().delete(specification);
    }

    @Transactional
    public List<BaseEntityDTO> findBySearch(BaseEntitySrchDTO searchDTO) {
        Specification<BaseEntity> specification = A0Mapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getBaseEntity().findAll(specification).stream()
                        
                        .map(A0Mapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getBaseEntity().findAll(specification, sort).stream()
                
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a BaseEntity entity by its ID and converts it to a BaseEntityDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the BaseEntityDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<BaseEntityDTO> findById(String id) {
        return helper.getBaseEntity().findById(id).map(A0Mapper::toDTO);
    }


    /**
     * Saves a new BaseEntity entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the BaseEntityDTO to save
     * @return the saved BaseEntityDTO
     */
    public BaseEntityDTO save(BaseEntityDTO dto) {
        BaseEntity entity = A0Mapper.toEntity(dto);
        
    

        BaseEntity result = helper.getBaseEntity().save(entity);
        return A0Mapper.toDTO(result);
    }


    /**
     * Updates an existing BaseEntity entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the BaseEntityDTO with updated information
     * @return the updated BaseEntityDTO
     */
    public BaseEntityDTO update(BaseEntityDTO dto) {
        BaseEntity entity = helper.getBaseEntity().findById(dto.getId()).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid BaseEntityDTO ID");
        A0Mapper.mapping(dto, entity, true);
        
    


        BaseEntity result = helper.getBaseEntity().save(entity);
        return A0Mapper.toDTO(result);
    }

    /**
     * Deletes a list of BaseEntity entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of BaseEntity representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<BaseEntityDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (BaseEntityDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a BaseEntity entity by its ID.
     *
     * @param id the ID of the BaseEntity entity to delete
     * @return true if the BaseEntity entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getBaseEntity().deleteById(id);
        return helper.getBaseEntity().existsById(id);
    }




    /**
     * Finds the greatest string value of the specified field in BaseEntity entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(BaseEntitySrchDTO dto, String fieldName) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<BaseEntity> root = cq.from(BaseEntity.class);

        Specification<BaseEntity> spec = A0Mapper.toSpec(dto);
        Predicate predicate = spec.toPredicate(root, cq, cb);
        cq.where(predicate);

        cq.select(cb.greatest(root.get(fieldName).as(String.class)));

        TypedQuery<String> query = em.createQuery(cq);
        List<String> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }



    /**
     * This method finds a numeric value based on a given function name and field name,
     * utilizing a DTO to create a Specification.
     *
     * @param <T>       the type of the numeric value to return
     * @param funcName  the name of the function to apply (e.g., "max", "min", "sum")
     * @param fieldName the name of the field to apply the function to
     * @param typeClass the class type of the numeric value
     * @param dto       the DTO used to create the Specification
     * @return the result of the function applied to the specified field, or null if no result is found
     * @throws IllegalArgumentException if the function name is not recognized
     */
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,BaseEntitySrchDTO dto) {
        Specification<BaseEntity> spec = A0Mapper.toSpec(dto);
        return findValue(funcName, fieldName, typeClass, spec);
    }


    /**
     * This method finds a numeric value based on a given function name and field name,
     * utilizing a Specification to filter the data.
     *
     * @param <T>       the type of the numeric value to return
     * @param funcName  the name of the function to apply (e.g., "max", "min", "sum")
     * @param fieldName the name of the field to apply the function to
     * @param typeClass the class type of the numeric value
     * @param spec      the Specification used to filter the data
     * @return the result of the function applied to the specified field, or null if no result is found
     * @throws IllegalArgumentException if the function name is not recognized
     */
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<BaseEntity> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<BaseEntity> root = cq.from(BaseEntity.class);

        Predicate predicate = spec.toPredicate(root, cq, cb);
        cq.where(predicate);

        if("max".equals(funcName))
            cq.select(cb.max(root.get(fieldName).as(typeClass)));
        else if("min".equals(funcName))
            cq.select(cb.min(root.get(fieldName).as(typeClass)));
        else if("abs".equals(funcName))
            cq.select(cb.abs(root.get(fieldName).as(typeClass)));
        else if("ceiling".equals(funcName))
            cq.select(cb.ceiling(root.get(fieldName).as(typeClass)));
        else if("floor".equals(funcName))
            cq.select(cb.floor(root.get(fieldName).as(typeClass)));
        else if("sum".equals(funcName))
            cq.select(cb.sum(root.get(fieldName).as(typeClass)));
        else
            throw new IllegalArgumentException("funcName not recognized");

        TypedQuery<T> query = em.createQuery(cq);
        List<T> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}

//ecd:-78823439H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.service;

import com.mobillug.leaserent.estimator.biz.entity.*;
import com.mobillug.leaserent.estimator.biz.dto.*;
import com.mobillug.leaserent.estimator.biz.repository.*;

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
public class UploadFileCoreService {

    protected final EstimateRepositoryHelper helper;


    /**
     * Retrieves all tUploadFile entities, converts them to UploadFileDTO objects, and returns them as a list.
     *
     * @return a list of UploadFileDTO objects
     */
    public List<UploadFileDTO> findAll() {
        return helper.getUploadFile().findAll().stream()
                 
                .map(EstimateMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tUploadFile entities, converts them to UploadFileDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of UploadFileDTO objects
     */
    public List<UploadFileDTO> findAll(Sort sort) {
        return helper.getUploadFile().findAll(sort).stream()
                 
                .map(EstimateMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes UploadFile entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(UploadFileSrchDTO searchDTO) {
            Specification<UploadFile> specification = EstimateMapper.toSpec(searchDTO);
            return helper.getUploadFile().delete(specification);
    }

    @Transactional
    public List<UploadFileDTO> findBySearch(UploadFileSrchDTO searchDTO) {
        Specification<UploadFile> specification = EstimateMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getUploadFile().findAll(specification).stream()
                        
                        .map(EstimateMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getUploadFile().findAll(specification, sort).stream()
                
                .map(EstimateMapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a UploadFile entity by its ID and converts it to a UploadFileDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the UploadFileDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<UploadFileDTO> findById(String id) {
        return helper.getUploadFile().findById(id).map(EstimateMapper::toDTO);
    }


    /**
     * Saves a new UploadFile entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the UploadFileDTO to save
     * @return the saved UploadFileDTO
     */
    public UploadFileDTO save(UploadFileDTO dto) {
        UploadFile entity = EstimateMapper.toEntity(dto);
        
    

        UploadFile result = helper.getUploadFile().save(entity);
        return EstimateMapper.toDTO(result);
    }


    /**
     * Updates an existing UploadFile entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the UploadFileDTO with updated information
     * @return the updated UploadFileDTO
     */
    public UploadFileDTO update(UploadFileDTO dto) {
        UploadFile entity = helper.getUploadFile().findById(dto.getId()).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid UploadFileDTO ID");
        EstimateMapper.mapping(dto, entity, true);
        
    


        UploadFile result = helper.getUploadFile().save(entity);
        return EstimateMapper.toDTO(result);
    }

    /**
     * Deletes a list of UploadFile entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of UploadFile representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<UploadFileDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (UploadFileDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a UploadFile entity by its ID.
     *
     * @param id the ID of the UploadFile entity to delete
     * @return true if the UploadFile entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getUploadFile().deleteById(id);
        return helper.getUploadFile().existsById(id);
    }




    /**
     * Finds the greatest string value of the specified field in UploadFile entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(UploadFileSrchDTO dto, String fieldName) {
        return findStringValue("greatest", fieldName, EstimateMapper.toSpec(dto));
    }


    /**
     * Finds a string value from the specified field of the `BaseCarInfo` entity based on the given function name and criteria.
     * <p>
     * This method dynamically constructs a JPA Criteria API query to apply functions like `least`, `greatest`, or `lower`
     * on a specified field. The query results are filtered using a `Specification`.
     * </p>
     *
     * @param funcName  the name of the function to apply on the field. Supported values are:
     *                  <ul>
     *                      <li>`least` - Returns the lexicographically smallest value in the field.</li>
     *                      <li>`greatest` - Returns the lexicographically largest value in the field.</li>
     *                      <li>`lower` - Converts the field value to lowercase.</li>
     *                  </ul>
     * @param fieldName the name of the field to apply the function on. Must be a valid string field in the `BaseCarInfo` entity.
     * @param spec      the {@link Specification} used to filter the query results. Defines the `WHERE` clause conditions.
     * @return the resulting string value after applying the specified function. Returns `null` if no results are found.
     * @throws IllegalArgumentException if the provided `funcName` is not recognized.
     *
     * @see CriteriaBuilder
     * @see CriteriaQuery
     * @see EntityManager
     */
    public String findStringValue(String funcName, String fieldName, Specification spec) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<UploadFile> root = cq.from(UploadFile.class);

        Predicate predicate = spec.toPredicate(root, cq, cb);
        cq.where(predicate);

        if("least".equals(funcName))
            cq.select(cb.least(root.get(fieldName).as(String.class)));
        else if("greatest".equals(funcName))
            cq.select(cb.greatest(root.get(fieldName).as(String.class)));
        else if("lower".equals(funcName))
            cq.select(cb.lower(root.get(fieldName).as(String.class)));
        else
            throw new IllegalArgumentException("funcName not recognized");

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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,UploadFileSrchDTO dto) {
        Specification<UploadFile> spec = EstimateMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<UploadFile> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<UploadFile> root = cq.from(UploadFile.class);

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

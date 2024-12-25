//ecd:-1470984158H20241223210702_V1.0
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
public class TxLogCoreService {

    protected final EstimateRepositoryHelper helper;


    /**
     * Retrieves all tTxLog entities, converts them to TxLogDTO objects, and returns them as a list.
     *
     * @return a list of TxLogDTO objects
     */
    public List<TxLogDTO> findAll() {
        return helper.getTxLog().findAll().stream()
                 
                .map(EstimateMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tTxLog entities, converts them to TxLogDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of TxLogDTO objects
     */
    public List<TxLogDTO> findAll(Sort sort) {
        return helper.getTxLog().findAll(sort).stream()
                 
                .map(EstimateMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes TxLog entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(TxLogSrchDTO searchDTO) {
            Specification<TxLog> specification = EstimateMapper.toSpec(searchDTO);
            return helper.getTxLog().delete(specification);
    }

    @Transactional
    public List<TxLogDTO> findBySearch(TxLogSrchDTO searchDTO) {
        Specification<TxLog> specification = EstimateMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getTxLog().findAll(specification).stream()
                        
                        .map(EstimateMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getTxLog().findAll(specification, sort).stream()
                
                .map(EstimateMapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a TxLog entity by its ID and converts it to a TxLogDTO.
     *
     * @param txId the ID of the FaxResult entity
     * @return an Optional containing the TxLogDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<TxLogDTO> findById(String txId) {
        return helper.getTxLog().findById(txId).map(EstimateMapper::toDTO);
    }


    /**
     * Saves a new TxLog entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the TxLogDTO to save
     * @return the saved TxLogDTO
     */
    public TxLogDTO save(TxLogDTO dto) {
        TxLog entity = EstimateMapper.toEntity(dto);
        
    

        TxLog result = helper.getTxLog().save(entity);
        return EstimateMapper.toDTO(result);
    }


    /**
     * Updates an existing TxLog entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the TxLogDTO with updated information
     * @return the updated TxLogDTO
     */
    public TxLogDTO update(TxLogDTO dto) {
        TxLog entity = helper.getTxLog().findById(dto.getTxId()).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid TxLogDTO ID");
        EstimateMapper.mapping(dto, entity, true);
        
    


        TxLog result = helper.getTxLog().save(entity);
        return EstimateMapper.toDTO(result);
    }

    /**
     * Deletes a list of TxLog entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of TxLog representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<TxLogDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (TxLogDTO dto : dtoList) {
            result.add(this.deleteById(dto.getTxId()));
        }
        return result;
    }


    /**
     * Deletes a TxLog entity by its ID.
     *
     * @param txId the ID of the TxLog entity to delete
     * @return true if the TxLog entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String txId) {
        helper.getTxLog().deleteById(txId);
        return helper.getTxLog().existsById(txId);
    }




    /**
     * Finds the greatest string value of the specified field in TxLog entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(TxLogSrchDTO dto, String fieldName) {
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
        Root<TxLog> root = cq.from(TxLog.class);

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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,TxLogSrchDTO dto) {
        Specification<TxLog> spec = EstimateMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<TxLog> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<TxLog> root = cq.from(TxLog.class);

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

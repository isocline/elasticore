//ecd:-954577362H20250204014854_V1.0
package com.test.service;

import com.test.entity.*;
import com.test.dto.*;
import com.test.repository.*;

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

@Service
@AllArgsConstructor
public class CompanyCoreService {

    protected final A0RepositoryHelper helper;


    /**
     * Retrieves all tCompany entities, converts them to CompanyDTO objects, and returns them as a list.
     *
     * @return a list of CompanyDTO objects
     */
    public List<CompanyDTO> findAll() {
        return helper.getCompany().findAll().stream()
                 
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tCompany entities, converts them to CompanyDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of CompanyDTO objects
     */
    public List<CompanyDTO> findAll(Sort sort) {
        return helper.getCompany().findAll(sort).stream()
                 
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes Company entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(CompanySrchDTO searchDTO) {
        Specification<Company> specification = A0Mapper.toSpec(searchDTO);
        return helper.getCompany().delete(specification);
    }

    @Transactional
    public List<CompanyDTO> findBySearch(CompanySrchDTO searchDTO) {
        Specification<Company> specification = A0Mapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getCompany().findAll(specification).stream()
                        
                        .map(A0Mapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getCompany().findAll(specification, sort).stream()
                
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a Company entity by its ID and converts it to a CompanyDTO.
     *
     * @param cid the ID of the FaxResult entity
     * @return an Optional containing the CompanyDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<CompanyDTO> findById(String cid) {
        return helper.getCompany().findById(cid).map(A0Mapper::toDTO);
    }


    /**
     * Saves a new Company entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the CompanyDTO to save
     * @return the saved CompanyDTO
     */
    public CompanyDTO save(CompanyDTO dto) {
        Company entity = A0Mapper.toEntity(dto);
        
    

        Company result = helper.getCompany().save(entity);
        return A0Mapper.toDTO(result);
    }


    /**
     * Updates an existing Company entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the CompanyDTO with updated information
     * @return the updated CompanyDTO
     */
    public CompanyDTO update(CompanyDTO dto) {
        Company entity = helper.getCompany().findById(dto.getCid()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid CompanyDTO ID");
        A0Mapper.mapping(dto, entity, true);
        
    


        Company result = helper.getCompany().save(entity);
        return A0Mapper.toDTO(result);
    }

    /**
     * Deletes a list of Company entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of Company representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<CompanyKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (CompanyKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getCid()));
        }
        return result;
    }


    /**
     * Deletes a Company entity by its ID.
     *
     * @param cid the ID of the Company entity to delete
     * @return true if the Company entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String cid) {
        helper.getCompany().deleteById(cid);
        return !helper.getCompany().existsById(cid);
    }




    /**
     * Finds the greatest string value of the specified field in Company entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(CompanySrchDTO dto, String fieldName) {
        return findStringValue("greatest", fieldName, A0Mapper.toSpec(dto));
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
        Root<Company> root = cq.from(Company.class);

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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,CompanySrchDTO dto) {
        Specification<Company> spec = A0Mapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<Company> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<Company> root = cq.from(Company.class);

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

//ecd:-1202133090H20250204014854_V1.0
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
public class EmployeeCoreService {

    protected final A0RepositoryHelper helper;


    /**
     * Retrieves all tEmployee entities, converts them to EmployeeDTO objects, and returns them as a list.
     *
     * @return a list of EmployeeDTO objects
     */
    public List<EmployeeDTO> findAll() {
        return helper.getEmployee().findAll().stream()
                 
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tEmployee entities, converts them to EmployeeDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of EmployeeDTO objects
     */
    public List<EmployeeDTO> findAll(Sort sort) {
        return helper.getEmployee().findAll(sort).stream()
                 
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes Employee entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(EmployeeSrchDTO searchDTO) {
        Specification<Employee> specification = A0Mapper.toSpec(searchDTO);
        return helper.getEmployee().delete(specification);
    }

    @Transactional
    public List<EmployeeDTO> findBySearch(EmployeeSrchDTO searchDTO) {
        Specification<Employee> specification = A0Mapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getEmployee().findAll(specification).stream()
                        
                        .map(A0Mapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getEmployee().findAll(specification, sort).stream()
                
                .map(A0Mapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a Employee entity by its ID and converts it to a EmployeeDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the EmployeeDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<EmployeeDTO> findById(String id) {
        return helper.getEmployee().findById(id).map(A0Mapper::toDTO);
    }


    /**
     * Saves a new Employee entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the EmployeeDTO to save
     * @return the saved EmployeeDTO
     */
    public EmployeeDTO save(EmployeeDTO dto) {
        Employee entity = A0Mapper.toEntity(dto);
        
        if(dto.getCompanyCid()!=null){
            Company item = helper.getCompany().findById(dto.getCompanyCid()).orElse(null);
            if(item!=null) entity.setCompany(item);
        }
    

        Employee result = helper.getEmployee().save(entity);
        return A0Mapper.toDTO(result);
    }


    /**
     * Updates an existing Employee entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the EmployeeDTO with updated information
     * @return the updated EmployeeDTO
     */
    public EmployeeDTO update(EmployeeDTO dto) {
        Employee entity = helper.getEmployee().findById(dto.getId()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid EmployeeDTO ID");
        A0Mapper.mapping(dto, entity, true);
        
        if(dto.getCompanyCid()!=null){
            Company item = helper.getCompany().findById(dto.getCompanyCid()).orElse(null);
            if(item!=null) entity.setCompany(item);
        }
    


        Employee result = helper.getEmployee().save(entity);
        return A0Mapper.toDTO(result);
    }

    /**
     * Deletes a list of Employee entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of Employee representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<EmployeeKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (EmployeeKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a Employee entity by its ID.
     *
     * @param id the ID of the Employee entity to delete
     * @return true if the Employee entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getEmployee().deleteById(id);
        return !helper.getEmployee().existsById(id);
    }




    /**
     * Finds the greatest string value of the specified field in Employee entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(EmployeeSrchDTO dto, String fieldName) {
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
        Root<Employee> root = cq.from(Employee.class);

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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,EmployeeSrchDTO dto) {
        Specification<Employee> spec = A0Mapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<Employee> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<Employee> root = cq.from(Employee.class);

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

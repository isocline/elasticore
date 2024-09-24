//ecd:-57956060H20240924235118_V1.0
package com.xsolcorpkorea.elasticore.test.dto.service;

import com.xsolcorpkorea.elasticore.test.dto.entity.*;
import com.xsolcorpkorea.elasticore.test.dto.dto.*;
import com.xsolcorpkorea.elasticore.test.dto.repository.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerCoreService {

    protected final DtoRepositoryHelper helper;


    /**
     * Retrieves all tCustomer entities, converts them to CustomerDTO objects, and returns them as a list.
     *
     * @return a list of CustomerDTO objects
     */
    public List<CustomerDTO> findAll() {
        return helper.getCustomer().findAll().stream()
                 
                .map(DtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Deletes Customer entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @javax.transaction.Transactional
    public long delete(CustomerSrchDTO searchDTO) {
            Specification<Customer> specification = DtoMapper.toSpec(searchDTO);
            return helper.getCustomer().delete(specification);
    }

    @Transactional
    public List<CustomerDTO> findBySearch(CustomerSrchDTO searchDTO) {
        Specification<Customer> specification = DtoMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getCustomer().findAll(specification).stream()
                        
                        .map(DtoMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getCustomer().findAll(specification, sort).stream()
                
                .map(DtoMapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a Customer entity by its ID and converts it to a CustomerDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the CustomerDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<CustomerDTO> findById(Long id) {
        return helper.getCustomer().findById(id).map(DtoMapper::toDTO);
    }


    /**
     * Saves a new Customer entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the CustomerDTO to save
     * @return the saved CustomerDTO
     */
    public CustomerDTO save(CustomerDTO dto) {
        Customer entity = DtoMapper.toEntity(dto);
        
    

        Customer result = helper.getCustomer().save(entity);
        return DtoMapper.toDTO(result);
    }


    /**
     * Updates an existing Customer entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the CustomerDTO with updated information
     * @return the updated CustomerDTO
     */
    public CustomerDTO update(CustomerDTO dto) {
        Customer entity = helper.getCustomer().findById(dto.getId()).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid CustomerDTO ID");
        DtoMapper.mapping(dto, entity, true);
        
    


        Customer result = helper.getCustomer().save(entity);
        return DtoMapper.toDTO(result);
    }


    /**
     * Deletes a Customer entity by its ID.
     *
     * @param id the ID of the Customer entity to delete
     */
    public void deleteById(Long id) {
        helper.getCustomer().deleteById(id);
    }




    /**
     * Finds the greatest string value of the specified field in Customer entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(CustomerSrchDTO dto, String fieldName) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Customer> root = cq.from(Customer.class);

        Specification<Customer> spec = DtoMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,CustomerSrchDTO dto) {
        Specification<Customer> spec = DtoMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<Customer> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<Customer> root = cq.from(Customer.class);

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

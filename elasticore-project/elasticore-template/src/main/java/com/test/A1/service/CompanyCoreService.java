//ecd:-281489310H20241212190121_V1.0
package com.test.A1.service;

import com.test.A1.entity.*;
import com.test.A1.dto.*;
import com.test.A1.repository.*;

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

    protected final A1RepositoryHelper helper;


    /**
     * Retrieves all tCompany entities, converts them to CompanyDTO objects, and returns them as a list.
     *
     * @return a list of CompanyDTO objects
     */
    public List<CompanyDTO> findAll() {
        return helper.getCompany().findAll().stream()
                 
                .map(A1Mapper::toDTO)
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
                 
                .map(A1Mapper::toDTO)
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
            Specification<Company> specification = A1Mapper.toSpec(searchDTO);
            return helper.getCompany().delete(specification);
    }

    @Transactional
    public List<CompanyDTO> findBySearch(CompanySrchDTO searchDTO) {
        Specification<Company> specification = A1Mapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getCompany().findAll(specification).stream()
                        
                        .map(A1Mapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getCompany().findAll(specification, sort).stream()
                
                .map(A1Mapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a Company entity by its ID and converts it to a CompanyDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the CompanyDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<CompanyDTO> findById(String id) {
        return helper.getCompany().findById(id).map(A1Mapper::toDTO);
    }


    /**
     * Saves a new Company entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the CompanyDTO to save
     * @return the saved CompanyDTO
     */
    public CompanyDTO save(CompanyDTO dto) {
        Company entity = A1Mapper.toEntity(dto);
        
    

        Company result = helper.getCompany().save(entity);
        return A1Mapper.toDTO(result);
    }


    /**
     * Updates an existing Company entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the CompanyDTO with updated information
     * @return the updated CompanyDTO
     */
    public CompanyDTO update(CompanyDTO dto) {
        Company entity = helper.getCompany().findById(dto.getId()).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid CompanyDTO ID");
        A1Mapper.mapping(dto, entity, true);
        
    


        Company result = helper.getCompany().save(entity);
        return A1Mapper.toDTO(result);
    }

    /**
     * Deletes a list of Company entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of Company representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<CompanyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (CompanyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a Company entity by its ID.
     *
     * @param id the ID of the Company entity to delete
     * @return true if the Company entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getCompany().deleteById(id);
        return helper.getCompany().existsById(id);
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
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Company> root = cq.from(Company.class);

        Specification<Company> spec = A1Mapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,CompanySrchDTO dto) {
        Specification<Company> spec = A1Mapper.toSpec(dto);
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

//ecd:762554349H20241207204630_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.service;

import com.xsolcorpkorea.elasticore.test.rollup.entity.*;
import com.xsolcorpkorea.elasticore.test.rollup.dto.*;
import com.xsolcorpkorea.elasticore.test.rollup.repository.*;

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
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InsuranceCompanyCodeCoreService {

    protected final SearchResultRepositoryHelper helper;


    /**
     * Retrieves all tInsuranceCompanyCode entities, converts them to InsuranceCompanyCodeDTO objects, and returns them as a list.
     *
     * @return a list of InsuranceCompanyCodeDTO objects
     */
    public List<InsuranceCompanyCodeDTO> findAll() {
        return helper.getInsuranceCompanyCode().findAll().stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tInsuranceCompanyCode entities, converts them to InsuranceCompanyCodeDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of InsuranceCompanyCodeDTO objects
     */
    public List<InsuranceCompanyCodeDTO> findAll(Sort sort) {
        return helper.getInsuranceCompanyCode().findAll(sort).stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes InsuranceCompanyCode entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @javax.transaction.Transactional
    public long delete(InsuranceCompanyCodeSrchDTO searchDTO) {
            Specification<InsuranceCompanyCode> specification = SearchResultMapper.toSpec(searchDTO);
            return helper.getInsuranceCompanyCode().delete(specification);
    }




    /**
     * Finds InsuranceCompanyCode entities that match the given search criteria and returns them as a paginated list of InsuranceCompanyCodeDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of InsuranceCompanyCodeDTO objects
     */
    @Transactional
    public Page<InsuranceCompanyCodeDTO> findBySearch(InsuranceCompanyCodeSrchDTO searchDTO) {
        Specification<InsuranceCompanyCode> specification = SearchResultMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<InsuranceCompanyCode> result = helper.getInsuranceCompanyCode().findAll(specification, pageable);
        return result.map(SearchResultMapper::toDTO);
    }


    /**
     * Counts the number of InsuranceCompanyCode entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(InsuranceCompanyCodeSrchDTO searchDTO) {
        Specification<InsuranceCompanyCode> specification = SearchResultMapper.toSpec(searchDTO);
        return helper.getInsuranceCompanyCode().count(specification);
    }

    /**
     * Finds a InsuranceCompanyCode entity by its ID and converts it to a InsuranceCompanyCodeDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the InsuranceCompanyCodeDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<InsuranceCompanyCodeDTO> findById(String insCpCode ,String insCpTypeCd) {
        return helper.getInsuranceCompanyCode().findById(new InsuranceCompanyCodeIdentity(insCpCode ,insCpTypeCd)).map(SearchResultMapper::toDTO);
    }


    /**
     * Saves a new InsuranceCompanyCode entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the InsuranceCompanyCodeDTO to save
     * @return the saved InsuranceCompanyCodeDTO
     */
    public InsuranceCompanyCodeDTO save(InsuranceCompanyCodeDTO dto) {
        InsuranceCompanyCode entity = SearchResultMapper.toEntity(dto);
        
    

        InsuranceCompanyCode result = helper.getInsuranceCompanyCode().save(entity);
        return SearchResultMapper.toDTO(result);
    }


    /**
     * Updates an existing InsuranceCompanyCode entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the InsuranceCompanyCodeDTO with updated information
     * @return the updated InsuranceCompanyCodeDTO
     */
    public InsuranceCompanyCodeDTO update(InsuranceCompanyCodeDTO dto) {
        InsuranceCompanyCode entity = helper.getInsuranceCompanyCode().findById(new InsuranceCompanyCodeIdentity(dto.getInsCpCode() ,dto.getInsCpTypeCd())).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid InsuranceCompanyCodeDTO ID");
        SearchResultMapper.mapping(dto, entity, true);
        
    


        InsuranceCompanyCode result = helper.getInsuranceCompanyCode().save(entity);
        return SearchResultMapper.toDTO(result);
    }

    /**
     * Deletes a list of InsuranceCompanyCode entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of InsuranceCompanyCode representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<InsuranceCompanyCodeDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (InsuranceCompanyCodeDTO dto : dtoList) {
            result.add(this.deleteById(dto.getInsCpCode() ,dto.getInsCpTypeCd()));
        }
        return result;
    }


    /**
     * Deletes a InsuranceCompanyCode entity by its ID.
     *
     * @param id the ID of the InsuranceCompanyCode entity to delete
     * @return true if the InsuranceCompanyCode entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String insCpCode ,String insCpTypeCd) {
        helper.getInsuranceCompanyCode().deleteById(new InsuranceCompanyCodeIdentity(insCpCode ,insCpTypeCd));
        return helper.getInsuranceCompanyCode().existsById(new InsuranceCompanyCodeIdentity(insCpCode ,insCpTypeCd));
    }




    /**
     * Finds the greatest string value of the specified field in InsuranceCompanyCode entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(InsuranceCompanyCodeSrchDTO dto, String fieldName) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<InsuranceCompanyCode> root = cq.from(InsuranceCompanyCode.class);

        Specification<InsuranceCompanyCode> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,InsuranceCompanyCodeSrchDTO dto) {
        Specification<InsuranceCompanyCode> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<InsuranceCompanyCode> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<InsuranceCompanyCode> root = cq.from(InsuranceCompanyCode.class);

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

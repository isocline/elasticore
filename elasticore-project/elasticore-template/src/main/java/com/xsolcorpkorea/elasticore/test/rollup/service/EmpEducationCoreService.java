//ecd:-24018880H20241207204630_V1.0
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
public class EmpEducationCoreService {

    protected final SearchResultRepositoryHelper helper;


    /**
     * Retrieves all tEmpEducation entities, converts them to EmpEducationDTO objects, and returns them as a list.
     *
     * @return a list of EmpEducationDTO objects
     */
    public List<EmpEducationDTO> findAll() {
        return helper.getEmpEducation().findAll().stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tEmpEducation entities, converts them to EmpEducationDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of EmpEducationDTO objects
     */
    public List<EmpEducationDTO> findAll(Sort sort) {
        return helper.getEmpEducation().findAll(sort).stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes EmpEducation entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @javax.transaction.Transactional
    public long delete(EmpEducationSrchDTO searchDTO) {
            Specification<EmpEducation> specification = SearchResultMapper.toSpec(searchDTO);
            return helper.getEmpEducation().delete(specification);
    }




    /**
     * Finds EmpEducation entities that match the given search criteria and returns them as a paginated list of EmpEducationDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of EmpEducationDTO objects
     */
    @Transactional
    public Page<EmpEducationDTO> findBySearch(EmpEducationSrchDTO searchDTO) {
        Specification<EmpEducation> specification = SearchResultMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<EmpEducation> result = helper.getEmpEducation().findAll(specification, pageable);
        return result.map(SearchResultMapper::toDTO);
    }


    /**
     * Counts the number of EmpEducation entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(EmpEducationSrchDTO searchDTO) {
        Specification<EmpEducation> specification = SearchResultMapper.toSpec(searchDTO);
        return helper.getEmpEducation().count(specification);
    }

    /**
     * Finds a EmpEducation entity by its ID and converts it to a EmpEducationDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the EmpEducationDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<EmpEducationDTO> findById(String empNo ,String enterYmd) {
        return helper.getEmpEducation().findById(new EmpEducationIdentity(empNo ,enterYmd)).map(SearchResultMapper::toDTO);
    }


    /**
     * Saves a new EmpEducation entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the EmpEducationDTO to save
     * @return the saved EmpEducationDTO
     */
    public EmpEducationDTO save(EmpEducationDTO dto) {
        EmpEducation entity = SearchResultMapper.toEntity(dto);
        
    

        EmpEducation result = helper.getEmpEducation().save(entity);
        return SearchResultMapper.toDTO(result);
    }


    /**
     * Updates an existing EmpEducation entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the EmpEducationDTO with updated information
     * @return the updated EmpEducationDTO
     */
    public EmpEducationDTO update(EmpEducationDTO dto) {
        EmpEducation entity = helper.getEmpEducation().findById(new EmpEducationIdentity(dto.getEmpNo() ,dto.getEnterYmd())).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid EmpEducationDTO ID");
        SearchResultMapper.mapping(dto, entity, true);
        
    


        EmpEducation result = helper.getEmpEducation().save(entity);
        return SearchResultMapper.toDTO(result);
    }

    /**
     * Deletes a list of EmpEducation entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of EmpEducation representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<EmpEducationDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (EmpEducationDTO dto : dtoList) {
            result.add(this.deleteById(dto.getEmpNo() ,dto.getEnterYmd()));
        }
        return result;
    }


    /**
     * Deletes a EmpEducation entity by its ID.
     *
     * @param id the ID of the EmpEducation entity to delete
     * @return true if the EmpEducation entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String empNo ,String enterYmd) {
        helper.getEmpEducation().deleteById(new EmpEducationIdentity(empNo ,enterYmd));
        return helper.getEmpEducation().existsById(new EmpEducationIdentity(empNo ,enterYmd));
    }




    /**
     * Finds the greatest string value of the specified field in EmpEducation entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(EmpEducationSrchDTO dto, String fieldName) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<EmpEducation> root = cq.from(EmpEducation.class);

        Specification<EmpEducation> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,EmpEducationSrchDTO dto) {
        Specification<EmpEducation> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<EmpEducation> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<EmpEducation> root = cq.from(EmpEducation.class);

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

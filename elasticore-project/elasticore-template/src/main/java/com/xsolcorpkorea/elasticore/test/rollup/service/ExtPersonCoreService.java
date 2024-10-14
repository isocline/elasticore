//ecd:-443070485H20241014191354_V1.0
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExtPersonCoreService {

    protected final Rollup2RepositoryHelper helper;


    /**
     * Retrieves all tExtPerson entities, converts them to ExtPersonDTO objects, and returns them as a list.
     *
     * @return a list of ExtPersonDTO objects
     */
    public List<ExtPersonDTO> findAll() {
        return helper.getExtPerson().findAll().stream()
                 
                .map(Rollup2Mapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Deletes ExtPerson entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @javax.transaction.Transactional
    public long delete(ExtPersonSrchDTO searchDTO) {
            Specification<ExtPerson> specification = Rollup2Mapper.toSpec(searchDTO);
            return helper.getExtPerson().delete(specification);
    }

    @Transactional
    public List<ExtPersonDTO> findBySearch(ExtPersonSrchDTO searchDTO) {
        Specification<ExtPerson> specification = Rollup2Mapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getExtPerson().findAll(specification).stream()
                        
                        .map(Rollup2Mapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getExtPerson().findAll(specification, sort).stream()
                
                .map(Rollup2Mapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a ExtPerson entity by its ID and converts it to a ExtPersonDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the ExtPersonDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<ExtPersonDTO> findById(String id) {
        return helper.getExtPerson().findById(id).map(Rollup2Mapper::toDTO);
    }


    /**
     * Saves a new ExtPerson entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the ExtPersonDTO to save
     * @return the saved ExtPersonDTO
     */
    public ExtPersonDTO save(ExtPersonDTO dto) {
        ExtPerson entity = Rollup2Mapper.toEntity(dto);
        
    

        ExtPerson result = helper.getExtPerson().save(entity);
        return Rollup2Mapper.toDTO(result);
    }


    /**
     * Updates an existing ExtPerson entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the ExtPersonDTO with updated information
     * @return the updated ExtPersonDTO
     */
    public ExtPersonDTO update(ExtPersonDTO dto) {
        ExtPerson entity = helper.getExtPerson().findById(dto.getId()).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid ExtPersonDTO ID");
        Rollup2Mapper.mapping(dto, entity, true);
        
    


        ExtPerson result = helper.getExtPerson().save(entity);
        return Rollup2Mapper.toDTO(result);
    }


    /**
     * Deletes a ExtPerson entity by its ID.
     *
     * @param id the ID of the ExtPerson entity to delete
     */
    public void deleteById(String id) {
        helper.getExtPerson().deleteById(id);
    }




    /**
     * Finds the greatest string value of the specified field in ExtPerson entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(ExtPersonSrchDTO dto, String fieldName) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<ExtPerson> root = cq.from(ExtPerson.class);

        Specification<ExtPerson> spec = Rollup2Mapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,ExtPersonSrchDTO dto) {
        Specification<ExtPerson> spec = Rollup2Mapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<ExtPerson> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<ExtPerson> root = cq.from(ExtPerson.class);

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

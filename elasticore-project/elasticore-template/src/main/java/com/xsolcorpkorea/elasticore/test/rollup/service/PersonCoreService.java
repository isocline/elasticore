//ecd:19570064H20241128111456_V1.0
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
public class PersonCoreService {

    protected final SearchResultRepositoryHelper helper;


    /**
     * Retrieves all tPerson entities, converts them to PersonDTO objects, and returns them as a list.
     *
     * @return a list of PersonDTO objects
     */
    public List<PersonDTO> findAll() {
        return helper.getPerson().findAll().stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tPerson entities, converts them to PersonDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of PersonDTO objects
     */
    public List<PersonDTO> findAll(Sort sort) {
        return helper.getPerson().findAll(sort).stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes Person entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @javax.transaction.Transactional
    public long delete(PersonSrchDTO searchDTO) {
            Specification<Person> specification = SearchResultMapper.toSpec(searchDTO);
            return helper.getPerson().delete(specification);
    }

    @Transactional
    public List<PersonDTO> findBySearch(PersonSrchDTO searchDTO) {
        Specification<Person> specification = SearchResultMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getPerson().findAll(specification).stream()
                        
                        .map(SearchResultMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getPerson().findAll(specification, sort).stream()
                
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }



    /**
     * Finds a Person entity by its ID and converts it to a PersonDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the PersonDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<PersonDTO> findById(String id) {
        return helper.getPerson().findById(id).map(SearchResultMapper::toDTO);
    }


    /**
     * Saves a new Person entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the PersonDTO to save
     * @return the saved PersonDTO
     */
    public PersonDTO save(PersonDTO dto) {
        Person entity = SearchResultMapper.toEntity(dto);
        
        if(dto.getPersonGrpId()!=null){
            PersonGroup item = helper.getPersonGroup().findById(dto.getPersonGrpId()).orElse(null);
            if(item!=null) entity.setPersonGrp(item);
        }
    

        Person result = helper.getPerson().save(entity);
        return SearchResultMapper.toDTO(result);
    }


    /**
     * Updates an existing Person entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the PersonDTO with updated information
     * @return the updated PersonDTO
     */
    public PersonDTO update(PersonDTO dto) {
        Person entity = helper.getPerson().findById(dto.getId()).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid PersonDTO ID");
        SearchResultMapper.mapping(dto, entity, true);
        
        if(dto.getPersonGrpId()!=null){
            PersonGroup item = helper.getPersonGroup().findById(dto.getPersonGrpId()).orElse(null);
            if(item!=null) entity.setPersonGrp(item);
        }
    


        Person result = helper.getPerson().save(entity);
        return SearchResultMapper.toDTO(result);
    }

    /**
     * Deletes a list of Person entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of Person representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<PersonDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (PersonDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a Person entity by its ID.
     *
     * @param id the ID of the Person entity to delete
     * @return true if the Person entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getPerson().deleteById(id);
        return helper.getPerson().existsById(id);
    }




    /**
     * Finds the greatest string value of the specified field in Person entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(PersonSrchDTO dto, String fieldName) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Person> root = cq.from(Person.class);

        Specification<Person> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,PersonSrchDTO dto) {
        Specification<Person> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<Person> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<Person> root = cq.from(Person.class);

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

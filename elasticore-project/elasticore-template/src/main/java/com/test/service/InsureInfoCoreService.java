//ecd:175936732H20250313130133_V1.0
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
public class InsureInfoCoreService {

    protected final Chk_specRepositoryHelper helper;


    /**
     * Retrieves all tInsureInfo entities, converts them to InsureInfoDTO objects, and returns them as a list.
     *
     * @return a list of InsureInfoDTO objects
     */
    public List<InsureInfoDTO> findAll() {
        return helper.getInsureInfo().findAll().stream()
                 
                .map(Chk_specMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tInsureInfo entities, converts them to InsureInfoDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of InsureInfoDTO objects
     */
    public List<InsureInfoDTO> findAll(Sort sort) {
        return helper.getInsureInfo().findAll(sort).stream()
                 
                .map(Chk_specMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes InsureInfo entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(InsureInfoSrchDTO searchDTO) {
        Specification<InsureInfo> specification = Chk_specMapper.toSpec(searchDTO);
        return helper.getInsureInfo().delete(specification);
    }


    /**
     * Finds a list of InsureInfo entities that match the given search criteria
     * and returns them as a list of InsureInfoDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching InsureInfoDTO objects
     */
    @Transactional
    public List<InsureInfoDTO> findBySearch(InsureInfoSrchDTO searchDTO) {
        Specification<InsureInfo> specification = Chk_specMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getInsureInfo().findAll(specification).stream()
                        
                        .map(Chk_specMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getInsureInfo().findAll(specification, sort).stream()
                
                .map(Chk_specMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds the first InsureInfo entity that matches the given search criteria
     * and returns it as a InsureInfoDTO object.
     * If no matching entity is found, this method returns {@code null}.
     *
     * @param searchDTO the search criteria
     * @return the first matching InsureInfoDTO object, or {@code null} if no match is found
     */
    public InsureInfoDTO findFirstBySearch(InsureInfoSrchDTO searchDTO) {
        List<InsureInfoDTO> list=findBySearch(searchDTO);
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }



    /**
     * Finds a InsureInfo entity by its ID and converts it to a InsureInfoDTO.
     *
     * @param new InsureInfoIdentity(id ,id2) the ID of the FaxResult entity
     * @return an Optional containing the InsureInfoDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<InsureInfoDTO> findById(String id ,Long id2) {
        return helper.getInsureInfo().findById(new InsureInfoIdentity(id ,id2)).map(Chk_specMapper::toDTO);
    }


    /**
     * Saves a new InsureInfo entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the InsureInfoDTO to save
     * @return the saved InsureInfoDTO
     */
    public InsureInfoDTO save(InsureInfoDTO dto) {
        InsureInfo entity = Chk_specMapper.toEntity(dto);
        
    

        InsureInfo result = helper.getInsureInfo().save(entity);
        return Chk_specMapper.toDTO(result);
    }


    /**
     * Updates an existing InsureInfo entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the InsureInfoDTO with updated information
     * @return the updated InsureInfoDTO
     */
    public InsureInfoDTO update(InsureInfoDTO dto) {
        InsureInfo entity = helper.getInsureInfo().findById(new InsureInfoIdentity(dto.getId() ,dto.getId2())).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid InsureInfoDTO ID");
        Chk_specMapper.mapping(dto, entity, true);
        
    


        InsureInfo result = helper.getInsureInfo().save(entity);
        return Chk_specMapper.toDTO(result);
    }

    /**
     * Deletes a list of InsureInfo entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of InsureInfo representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<InsureInfoKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (InsureInfoKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId() ,dto.getId2()));
        }
        return result;
    }


    /**
     * Deletes a InsureInfo entity by its ID.
     *
     * @param new InsureInfoIdentity(id ,id2) the ID of the InsureInfo entity to delete
     * @return true if the InsureInfo entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id ,Long id2) {
        helper.getInsureInfo().deleteById(new InsureInfoIdentity(id ,id2));
        return !helper.getInsureInfo().existsById(new InsureInfoIdentity(id ,id2));
    }




    /**
     * Finds the greatest string value of the specified field in InsureInfo entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(InsureInfoSrchDTO dto, String fieldName) {
        return findStringValue("greatest", fieldName, Chk_specMapper.toSpec(dto));
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
        Root<InsureInfo> root = cq.from(InsureInfo.class);

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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,InsureInfoSrchDTO dto) {
        Specification<InsureInfo> spec = Chk_specMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<InsureInfo> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<InsureInfo> root = cq.from(InsureInfo.class);

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

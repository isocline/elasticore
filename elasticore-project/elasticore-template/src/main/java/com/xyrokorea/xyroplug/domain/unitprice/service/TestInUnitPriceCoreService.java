//ecd:-311795245H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.unitprice.service;

import com.xyrokorea.xyroplug.domain.unitprice.entity.*;
import com.xyrokorea.xyroplug.domain.unitprice.dto.*;
import com.xyrokorea.xyroplug.domain.unitprice.repository.*;

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
public class TestInUnitPriceCoreService {

    protected final UnitpriceRepositoryHelper helper;


    /**
     * Retrieves all tTestInUnitPrice entities, converts them to TestInUnitPriceDTO objects, and returns them as a list.
     *
     * @return a list of TestInUnitPriceDTO objects
     */
    public List<TestInUnitPriceDTO> findAll() {
        return helper.getTestInUnitPrice().findAll().stream()
                 
                .map(UnitpriceMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tTestInUnitPrice entities, converts them to TestInUnitPriceDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of TestInUnitPriceDTO objects
     */
    public List<TestInUnitPriceDTO> findAll(Sort sort) {
        return helper.getTestInUnitPrice().findAll(sort).stream()
                 
                .map(UnitpriceMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes TestInUnitPrice entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(TestInUnitPriceSrchDTO searchDTO) {
        Specification<TestInUnitPrice> specification = UnitpriceMapper.toSpec(searchDTO);
        return helper.getTestInUnitPrice().delete(specification);
    }


    /**
     * Finds a list of TestInUnitPrice entities that match the given search criteria
     * and returns them as a list of TestInUnitPriceDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching TestInUnitPriceDTO objects
     */
    @Transactional
    public List<TestInUnitPriceDTO> findBySearch(TestInUnitPriceSrchDTO searchDTO) {
        Specification<TestInUnitPrice> specification = UnitpriceMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getTestInUnitPrice().findAll(specification).stream()
                        
                        .map(UnitpriceMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getTestInUnitPrice().findAll(specification, sort).stream()
                
                .map(UnitpriceMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds the first TestInUnitPrice entity that matches the given search criteria
     * and returns it as a TestInUnitPriceDTO object.
     * If no matching entity is found, this method returns {@code null}.
     *
     * @param searchDTO the search criteria
     * @return the first matching TestInUnitPriceDTO object, or {@code null} if no match is found
     */
    public TestInUnitPriceDTO findFirstBySearch(TestInUnitPriceSrchDTO searchDTO) {
        List<TestInUnitPriceDTO> list=findBySearch(searchDTO);
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }



    /**
     * Finds a TestInUnitPrice entity by its ID and converts it to a TestInUnitPriceDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the TestInUnitPriceDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<TestInUnitPriceDTO> findById(String id) {
        return helper.getTestInUnitPrice().findById(id).map(UnitpriceMapper::toDTO);
    }


    /**
     * Saves a new TestInUnitPrice entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the TestInUnitPriceDTO to save
     * @return the saved TestInUnitPriceDTO
     */
    public TestInUnitPriceDTO save(TestInUnitPriceDTO dto) {
        TestInUnitPrice entity = UnitpriceMapper.toEntity(dto);
        
    

        TestInUnitPrice result = helper.getTestInUnitPrice().save(entity);
        return UnitpriceMapper.toDTO(result);
    }


    /**
     * Updates an existing TestInUnitPrice entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the TestInUnitPriceDTO with updated information
     * @return the updated TestInUnitPriceDTO
     */
    public TestInUnitPriceDTO update(TestInUnitPriceDTO dto) {
        TestInUnitPrice entity = helper.getTestInUnitPrice().findById(dto.getId()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid TestInUnitPriceDTO ID");
        UnitpriceMapper.mapping(dto, entity, true);
        
    


        TestInUnitPrice result = helper.getTestInUnitPrice().save(entity);
        return UnitpriceMapper.toDTO(result);
    }

    /**
     * Deletes a list of TestInUnitPrice entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of TestInUnitPrice representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<TestInUnitPriceKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (TestInUnitPriceKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a TestInUnitPrice entity by its ID.
     *
     * @param id the ID of the TestInUnitPrice entity to delete
     * @return true if the TestInUnitPrice entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getTestInUnitPrice().deleteById(id);
        return !helper.getTestInUnitPrice().existsById(id);
    }




    /**
     * Finds the greatest string value of the specified field in TestInUnitPrice entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(TestInUnitPriceSrchDTO dto, String fieldName) {
        return findStringValue("greatest", fieldName, UnitpriceMapper.toSpec(dto));
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
        Root<TestInUnitPrice> root = cq.from(TestInUnitPrice.class);

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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,TestInUnitPriceSrchDTO dto) {
        Specification<TestInUnitPrice> spec = UnitpriceMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<TestInUnitPrice> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<TestInUnitPrice> root = cq.from(TestInUnitPrice.class);

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

//ecd:-454675557H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.channel.service;

import com.xyrokorea.xyroplug.domain.channel.entity.*;
import com.xyrokorea.xyroplug.domain.channel.dto.*;
import com.xyrokorea.xyroplug.domain.channel.repository.*;

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
public class TestInChannelCoreService {

    protected final ChannelRepositoryHelper helper;


    /**
     * Retrieves all tTestInChannel entities, converts them to TestInChannelDTO objects, and returns them as a list.
     *
     * @return a list of TestInChannelDTO objects
     */
    public List<TestInChannelDTO> findAll() {
        return helper.getTestInChannel().findAll().stream()
                 
                .map(ChannelMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tTestInChannel entities, converts them to TestInChannelDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of TestInChannelDTO objects
     */
    public List<TestInChannelDTO> findAll(Sort sort) {
        return helper.getTestInChannel().findAll(sort).stream()
                 
                .map(ChannelMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes TestInChannel entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(TestInChannelSrchDTO searchDTO) {
        Specification<TestInChannel> specification = ChannelMapper.toSpec(searchDTO);
        return helper.getTestInChannel().delete(specification);
    }


    /**
     * Finds a list of TestInChannel entities that match the given search criteria
     * and returns them as a list of TestInChannelDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching TestInChannelDTO objects
     */
    @Transactional
    public List<TestInChannelDTO> findBySearch(TestInChannelSrchDTO searchDTO) {
        Specification<TestInChannel> specification = ChannelMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getTestInChannel().findAll(specification).stream()
                        
                        .map(ChannelMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getTestInChannel().findAll(specification, sort).stream()
                
                .map(ChannelMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds the first TestInChannel entity that matches the given search criteria
     * and returns it as a TestInChannelDTO object.
     * If no matching entity is found, this method returns {@code null}.
     *
     * @param searchDTO the search criteria
     * @return the first matching TestInChannelDTO object, or {@code null} if no match is found
     */
    public TestInChannelDTO findFirstBySearch(TestInChannelSrchDTO searchDTO) {
        List<TestInChannelDTO> list=findBySearch(searchDTO);
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }



    /**
     * Finds a TestInChannel entity by its ID and converts it to a TestInChannelDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the TestInChannelDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<TestInChannelDTO> findById(String id) {
        return helper.getTestInChannel().findById(id).map(ChannelMapper::toDTO);
    }


    /**
     * Saves a new TestInChannel entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the TestInChannelDTO to save
     * @return the saved TestInChannelDTO
     */
    public TestInChannelDTO save(TestInChannelDTO dto) {
        TestInChannel entity = ChannelMapper.toEntity(dto);
        
    

        TestInChannel result = helper.getTestInChannel().save(entity);
        return ChannelMapper.toDTO(result);
    }


    /**
     * Updates an existing TestInChannel entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the TestInChannelDTO with updated information
     * @return the updated TestInChannelDTO
     */
    public TestInChannelDTO update(TestInChannelDTO dto) {
        TestInChannel entity = helper.getTestInChannel().findById(dto.getId()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid TestInChannelDTO ID");
        ChannelMapper.mapping(dto, entity, true);
        
    


        TestInChannel result = helper.getTestInChannel().save(entity);
        return ChannelMapper.toDTO(result);
    }

    /**
     * Deletes a list of TestInChannel entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of TestInChannel representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<TestInChannelKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (TestInChannelKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a TestInChannel entity by its ID.
     *
     * @param id the ID of the TestInChannel entity to delete
     * @return true if the TestInChannel entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String id) {
        helper.getTestInChannel().deleteById(id);
        return !helper.getTestInChannel().existsById(id);
    }




    /**
     * Finds the greatest string value of the specified field in TestInChannel entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(TestInChannelSrchDTO dto, String fieldName) {
        return findStringValue("greatest", fieldName, ChannelMapper.toSpec(dto));
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
        Root<TestInChannel> root = cq.from(TestInChannel.class);

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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,TestInChannelSrchDTO dto) {
        Specification<TestInChannel> spec = ChannelMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<TestInChannel> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<TestInChannel> root = cq.from(TestInChannel.class);

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

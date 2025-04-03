//ecd:-1361501095H20250402133543_V1.0
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
public class BoardCoreService {

    protected final ChannelRepositoryHelper helper;


    /**
     * Retrieves all Board entities, converts them to BoardDTO objects, and returns them as a list.
     *
     * @return a list of BoardDTO objects
     */
    public List<BoardDTO> findAll() {
        return helper.getBoard().findAll().stream()
                 
                .map(ChannelMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tBoard entities, converts them to BoardDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of BoardDTO objects
     */
    public List<BoardDTO> findAll(Sort sort) {
        return helper.getBoard().findAll(sort).stream()
                 
                .map(ChannelMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes Board entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @jakarta.transaction.Transactional
    public long delete(BoardSrchDTO searchDTO) {
        Specification<Board> specification = ChannelMapper.toSpec(searchDTO);
        return helper.getBoard().delete(specification);
    }




    /**
     * Finds a list of Board entities that match the given search criteria
     * and returns them as a list of BoardDTO objects.
     * If sorting information is provided in the search criteria, the results are sorted accordingly.
     *
     * @param searchDTO the search criteria
     * @return a list of matching BoardDTO objects
     */
    @Transactional
    public List<BoardDTO> listBySearch(BoardSrchDTO searchDTO) {
        Specification<Board> specification = ChannelMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getBoard().findAll(specification).stream()
                        
                        .map(ChannelMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getBoard().findAll(specification, sort).stream()
                
                .map(ChannelMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds Board entities that match the given search criteria and returns them as a paginated list of BoardDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of BoardDTO objects
     */
    @Transactional
    public Page<BoardDTO> findBySearch(BoardSrchDTO searchDTO) {
        Specification<Board> specification = ChannelMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<Board> result = helper.getBoard().findAll(specification, pageable);
        return result.map(ChannelMapper::toDTO);
    }

    /**
     * Finds the first Board entity that matches the given search criteria
     * and returns it as a BoardDTO object.
     *
     * @param searchDTO the search criteria
     * @return the first matching BoardDTO object, or {@code null} if no match is found
     */
    public BoardDTO findFirstBySearch(BoardSrchDTO searchDTO) {
        searchDTO.setPageSize(1);
        Page<BoardDTO> pages= findBySearch(searchDTO);
        List<BoardDTO> list=pages.getContent();
        if(list==null || list.size()==0)
            return null;
        return list.get(0);
    }


    /**
     * Counts the number of Board entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(BoardSrchDTO searchDTO) {
        Specification<Board> specification = ChannelMapper.toSpec(searchDTO);
        return helper.getBoard().count(specification);
    }
    /**
     * Finds a Board entity by its ID and converts it to a BoardDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the BoardDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<BoardDTO> findById(Long id) {
        return helper.getBoard().findById(id).map(ChannelMapper::toDTO);
    }


    /**
     * Saves a new Board entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the BoardDTO to save
     * @return the saved BoardDTO
     */
    public BoardDTO save(BoardDTO dto) {
        Board entity = ChannelMapper.toEntity(dto);
        
    

        Board result = helper.getBoard().save(entity);
        return ChannelMapper.toDTO(result);
    }


    /**
     * Updates an existing Board entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the BoardDTO with updated information
     * @return the updated BoardDTO
     */
    public BoardDTO update(BoardDTO dto) {
        Board entity = helper.getBoard().findById(dto.getId()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid BoardDTO ID");
        ChannelMapper.mapping(dto, entity, true);
        
    


        Board result = helper.getBoard().save(entity);
        return ChannelMapper.toDTO(result);
    }

    /**
     * Deletes a list of Board entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of Board representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<BoardKeyDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (BoardKeyDTO dto : dtoList) {
            result.add(this.deleteById(dto.getId()));
        }
        return result;
    }


    /**
     * Deletes a Board entity by its ID.
     *
     * @param id the ID of the Board entity to delete
     * @return true if the Board entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(Long id) {
        helper.getBoard().deleteById(id);
        return !helper.getBoard().existsById(id);
    }




    /**
     * Finds the greatest string value of the specified field in Board entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(BoardSrchDTO dto, String fieldName) {
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
        Root<Board> root = cq.from(Board.class);

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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,BoardSrchDTO dto) {
        Specification<Board> spec = ChannelMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<Board> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<Board> root = cq.from(Board.class);

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

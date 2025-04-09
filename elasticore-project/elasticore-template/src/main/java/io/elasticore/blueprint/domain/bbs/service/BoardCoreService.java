//ecd:-1166025501H20250409104819_V1.0
package io.elasticore.blueprint.domain.bbs.service;

import io.elasticore.blueprint.domain.bbs.entity.*;
import io.elasticore.blueprint.domain.bbs.dto.*;
import io.elasticore.blueprint.domain.bbs.repository.*;

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
import java.lang.reflect.Field;

/**
 * Comprehensive service layer for managing Board entities.
 *
 * Provides full CRUD operations, dynamic search with specification support,
 * pagination, sorting, and advanced query functions such as min/max/sum.
 * Delegates repository operations via BbsRepositoryHelper.
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Service
@AllArgsConstructor
public class BoardCoreService {

    protected final BbsRepositoryHelper helper;


    /**
     * Retrieves all Board entities, converts them to BoardDTO objects, and returns them as a list.
     *
     * @return a list of BoardDTO objects
     */
    public List<BoardDTO> findAll() {
        return helper.getBoard().findAll().stream()
                 
                .map(BbsMapper::toDTO)
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
                 
                .map(BbsMapper::toDTO)
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
        Specification<Board> specification = BbsMapper.toSpec(searchDTO);
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
        Specification<Board> specification = BbsMapper.toSpec(searchDTO);
        Sort sort = searchDTO.getSort();
        if(sort ==null) {
            return helper.getBoard().findAll(specification).stream()
                        
                        .map(BbsMapper::toDTO)
                        .collect(Collectors.toList());
        }
        return helper.getBoard().findAll(specification, sort).stream()
                
                .map(BbsMapper::toDTO)
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
        Specification<Board> specification = BbsMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<Board> result = helper.getBoard().findAll(specification, pageable);
        return result.map(BbsMapper::toDTO);
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
        Specification<Board> specification = BbsMapper.toSpec(searchDTO);
        return helper.getBoard().count(specification);
    }
    /**
     * Finds a Board entity by its ID and converts it to a BoardDTO.
     *
     * @param bid the ID of the FaxResult entity
     * @return an Optional containing the BoardDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<BoardDTO> findById(Long bid) {
        return helper.getBoard().findById(bid).map(BbsMapper::toDTO);
    }


    /**
     * Saves a new Board entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the BoardDTO to save
     * @return the saved BoardDTO
     */
    public BoardDTO save(BoardDTO dto) {
        Board entity = BbsMapper.toEntity(dto);
        
    

        Board result = helper.getBoard().save(entity);
        return BbsMapper.toDTO(result);
    }


    /**
     * Updates an existing Board entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the BoardDTO with updated information
     * @return the updated BoardDTO
     */
    public BoardDTO update(BoardDTO dto) {
        Board entity = helper.getBoard().findById(dto.getBid()).orElse(null);
        if(entity==null)
            throw new EntityNotFoundException("Invalid BoardDTO ID");
        BbsMapper.mapping(dto, entity, true);
        
    


        Board result = helper.getBoard().save(entity);
        return BbsMapper.toDTO(result);
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
            result.add(this.deleteById(dto.getBid()));
        }
        return result;
    }


    /**
     * Deletes a Board entity by its ID.
     *
     * @param bid the ID of the Board entity to delete
     * @return true if the Board entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(Long bid) {
        helper.getBoard().deleteById(bid);
        return !helper.getBoard().existsById(bid);
    }





    /**
     * Updates Message entities that match the given search criteria,
     * using non-null fields from the update DTO.
     *
     * @param dto     DTO containing the fields to update (only non-null fields will be applied)
     * @param srchDTO DTO containing search conditions for filtering target entities
     * @return number of records updated
     */
    @Transactional
    public int updateBySpecification(BoardDTO dto, BoardSrchDTO srchDTO) {
        return updateBySpecification(dto, BbsMapper.toSpec(srchDTO));
    }

    /**
     * Performs a conditional update using the provided Specification and non-null fields from the DTO.
     * Prevents full table updates by requiring at least one condition.
     *
     * @param dto  DTO containing the fields to update (only non-null fields will be applied)
     * @param spec JPA Specification defining update conditions
     * @return number of records updated
     * @throws IllegalArgumentException if no condition is provided
     */
    @Transactional
    public int updateBySpecification(BoardDTO dto, Specification<Board> spec) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<Board> update = cb.createCriteriaUpdate(Board.class);
        Root<Board> root = update.from(Board.class);

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                if (value != null)
                    update.set(root.get(field.getName()), value);
            } catch (IllegalAccessException e) { }
        }
        Predicate predicate = spec.toPredicate(root, cb.createQuery(Board.class), cb);
        if (predicate == null)
            throw new IllegalArgumentException("Update condition is missing. Full table update is not allowed.");
        update.where(predicate);
        return helper.getEntityManager().createQuery(update).executeUpdate();
    }
}

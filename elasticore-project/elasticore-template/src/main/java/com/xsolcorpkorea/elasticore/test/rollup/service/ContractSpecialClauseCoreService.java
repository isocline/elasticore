//ecd:2144913638H20241207204630_V1.0
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
public class ContractSpecialClauseCoreService {

    protected final SearchResultRepositoryHelper helper;


    /**
     * Retrieves all tContractSpecialClause entities, converts them to ContractSpecialClauseDTO objects, and returns them as a list.
     *
     * @return a list of ContractSpecialClauseDTO objects
     */
    public List<ContractSpecialClauseDTO> findAll() {
        return helper.getContractSpecialClause().findAll().stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tContractSpecialClause entities, converts them to ContractSpecialClauseDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of ContractSpecialClauseDTO objects
     */
    public List<ContractSpecialClauseDTO> findAll(Sort sort) {
        return helper.getContractSpecialClause().findAll(sort).stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes ContractSpecialClause entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @javax.transaction.Transactional
    public long delete(ContractSpecialClauseSrchDTO searchDTO) {
            Specification<ContractSpecialClause> specification = SearchResultMapper.toSpec(searchDTO);
            return helper.getContractSpecialClause().delete(specification);
    }




    /**
     * Finds ContractSpecialClause entities that match the given search criteria and returns them as a paginated list of ContractSpecialClauseDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of ContractSpecialClauseDTO objects
     */
    @Transactional
    public Page<ContractSpecialClauseDTO> findBySearch(ContractSpecialClauseSrchDTO searchDTO) {
        Specification<ContractSpecialClause> specification = SearchResultMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<ContractSpecialClause> result = helper.getContractSpecialClause().findAll(specification, pageable);
        return result.map(SearchResultMapper::toDTO);
    }


    /**
     * Counts the number of ContractSpecialClause entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(ContractSpecialClauseSrchDTO searchDTO) {
        Specification<ContractSpecialClause> specification = SearchResultMapper.toSpec(searchDTO);
        return helper.getContractSpecialClause().count(specification);
    }

    /**
     * Finds a ContractSpecialClause entity by its ID and converts it to a ContractSpecialClauseDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the ContractSpecialClauseDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<ContractSpecialClauseDTO> findById(String insuComCd ,String policyNo ,Integer seq) {
        return helper.getContractSpecialClause().findById(new ContractSpecialClauseIdentity(insuComCd ,policyNo ,seq)).map(SearchResultMapper::toDTO);
    }


    /**
     * Saves a new ContractSpecialClause entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the ContractSpecialClauseDTO to save
     * @return the saved ContractSpecialClauseDTO
     */
    public ContractSpecialClauseDTO save(ContractSpecialClauseDTO dto) {
        ContractSpecialClause entity = SearchResultMapper.toEntity(dto);
        
    

        ContractSpecialClause result = helper.getContractSpecialClause().save(entity);
        return SearchResultMapper.toDTO(result);
    }


    /**
     * Updates an existing ContractSpecialClause entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the ContractSpecialClauseDTO with updated information
     * @return the updated ContractSpecialClauseDTO
     */
    public ContractSpecialClauseDTO update(ContractSpecialClauseDTO dto) {
        ContractSpecialClause entity = helper.getContractSpecialClause().findById(new ContractSpecialClauseIdentity(dto.getInsuComCd() ,dto.getPolicyNo() ,dto.getSeq())).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid ContractSpecialClauseDTO ID");
        SearchResultMapper.mapping(dto, entity, true);
        
    


        ContractSpecialClause result = helper.getContractSpecialClause().save(entity);
        return SearchResultMapper.toDTO(result);
    }

    /**
     * Deletes a list of ContractSpecialClause entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of ContractSpecialClause representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<ContractSpecialClauseDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (ContractSpecialClauseDTO dto : dtoList) {
            result.add(this.deleteById(dto.getInsuComCd() ,dto.getPolicyNo() ,dto.getSeq()));
        }
        return result;
    }


    /**
     * Deletes a ContractSpecialClause entity by its ID.
     *
     * @param id the ID of the ContractSpecialClause entity to delete
     * @return true if the ContractSpecialClause entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String insuComCd ,String policyNo ,Integer seq) {
        helper.getContractSpecialClause().deleteById(new ContractSpecialClauseIdentity(insuComCd ,policyNo ,seq));
        return helper.getContractSpecialClause().existsById(new ContractSpecialClauseIdentity(insuComCd ,policyNo ,seq));
    }




    /**
     * Finds the greatest string value of the specified field in ContractSpecialClause entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(ContractSpecialClauseSrchDTO dto, String fieldName) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<ContractSpecialClause> root = cq.from(ContractSpecialClause.class);

        Specification<ContractSpecialClause> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,ContractSpecialClauseSrchDTO dto) {
        Specification<ContractSpecialClause> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<ContractSpecialClause> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<ContractSpecialClause> root = cq.from(ContractSpecialClause.class);

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

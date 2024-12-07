//ecd:278909102H20241207204630_V1.0
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
public class ContractPaymentCoreService {

    protected final SearchResultRepositoryHelper helper;


    /**
     * Retrieves all tContractPayment entities, converts them to ContractPaymentDTO objects, and returns them as a list.
     *
     * @return a list of ContractPaymentDTO objects
     */
    public List<ContractPaymentDTO> findAll() {
        return helper.getContractPayment().findAll().stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all tContractPayment entities, converts them to ContractPaymentDTO objects, and returns them as a list.
     *
     * @param sort the sort information
     * @return a list of ContractPaymentDTO objects
     */
    public List<ContractPaymentDTO> findAll(Sort sort) {
        return helper.getContractPayment().findAll(sort).stream()
                 
                .map(SearchResultMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * Deletes ContractPayment entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities deleted
     */
    @javax.transaction.Transactional
    public long delete(ContractPaymentSrchDTO searchDTO) {
            Specification<ContractPayment> specification = SearchResultMapper.toSpec(searchDTO);
            return helper.getContractPayment().delete(specification);
    }




    /**
     * Finds ContractPayment entities that match the given search criteria and returns them as a paginated list of ContractPaymentDTO.
     *
     * @param searchDTO the search criteria
     * @return a paginated list of ContractPaymentDTO objects
     */
    @Transactional
    public Page<ContractPaymentDTO> findBySearch(ContractPaymentSrchDTO searchDTO) {
        Specification<ContractPayment> specification = SearchResultMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<ContractPayment> result = helper.getContractPayment().findAll(specification, pageable);
        return result.map(SearchResultMapper::toDTO);
    }


    /**
     * Counts the number of ContractPayment entities that match the given search criteria.
     *
     * @param searchDTO the search criteria
     * @return the number of entities that match the search criteria
     */
    public long countBySearch(ContractPaymentSrchDTO searchDTO) {
        Specification<ContractPayment> specification = SearchResultMapper.toSpec(searchDTO);
        return helper.getContractPayment().count(specification);
    }

    /**
     * Finds a ContractPayment entity by its ID and converts it to a ContractPaymentDTO.
     *
     * @param id the ID of the FaxResult entity
     * @return an Optional containing the ContractPaymentDTO if found, or an empty Optional if not found
     */
    @Transactional
    public Optional<ContractPaymentDTO> findById(String policyNo ,String insuComCd ,Integer seq) {
        return helper.getContractPayment().findById(new ContractPaymentIdentity(policyNo ,insuComCd ,seq)).map(SearchResultMapper::toDTO);
    }


    /**
     * Saves a new ContractPayment entity based on the given DTO and returns the saved entity as a DTO.
     *
     * @param dto the ContractPaymentDTO to save
     * @return the saved ContractPaymentDTO
     */
    public ContractPaymentDTO save(ContractPaymentDTO dto) {
        ContractPayment entity = SearchResultMapper.toEntity(dto);
        
    

        ContractPayment result = helper.getContractPayment().save(entity);
        return SearchResultMapper.toDTO(result);
    }


    /**
     * Updates an existing ContractPayment entity based on the given DTO and returns the updated entity as a DTO.
     *
     * @param dto the ContractPaymentDTO with updated information
     * @return the updated ContractPaymentDTO
     */
    public ContractPaymentDTO update(ContractPaymentDTO dto) {
        ContractPayment entity = helper.getContractPayment().findById(new ContractPaymentIdentity(dto.getPolicyNo() ,dto.getInsuComCd() ,dto.getSeq())).orElse(null);
        if(entity==null)
          throw new IllegalArgumentException("Invalid ContractPaymentDTO ID");
        SearchResultMapper.mapping(dto, entity, true);
        
    


        ContractPayment result = helper.getContractPayment().save(entity);
        return SearchResultMapper.toDTO(result);
    }

    /**
     * Deletes a list of ContractPayment entities based on the given list of DTOs and returns a list of boolean results
     * indicating the success or failure of each deletion.
     *
     * @param dtoList the list of ContractPayment representing the entities to delete
     * @return a list of boolean values where each value represents the deletion success (true) or failure (false)
     *         for the corresponding ContractRelated entity
     */
    public List<Boolean> delete(List<ContractPaymentDTO> dtoList) {
        List<Boolean> result = new ArrayList<>();
        for (ContractPaymentDTO dto : dtoList) {
            result.add(this.deleteById(dto.getPolicyNo() ,dto.getInsuComCd() ,dto.getSeq()));
        }
        return result;
    }


    /**
     * Deletes a ContractPayment entity by its ID.
     *
     * @param id the ID of the ContractPayment entity to delete
     * @return true if the ContractPayment entity was successfully deleted, false if it still exists
     */
    public boolean deleteById(String policyNo ,String insuComCd ,Integer seq) {
        helper.getContractPayment().deleteById(new ContractPaymentIdentity(policyNo ,insuComCd ,seq));
        return helper.getContractPayment().existsById(new ContractPaymentIdentity(policyNo ,insuComCd ,seq));
    }




    /**
     * Finds the greatest string value of the specified field in ContractPayment entities
     * that match the given search criteria.
     *
     * @param dto        the search criteria
     * @param fieldName  the name of the field for which to find the maximum value
     * @return the greatest string value of the specified field, or null if no results are found
     */
    public String findGreatest(ContractPaymentSrchDTO dto, String fieldName) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<ContractPayment> root = cq.from(ContractPayment.class);

        Specification<ContractPayment> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass,ContractPaymentSrchDTO dto) {
        Specification<ContractPayment> spec = SearchResultMapper.toSpec(dto);
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
    public <T extends Number> T findValue(String funcName, String fieldName, Class<T> typeClass, Specification<ContractPayment> spec ) {
        EntityManager em = helper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(typeClass);
        Root<ContractPayment> root = cq.from(ContractPayment.class);

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

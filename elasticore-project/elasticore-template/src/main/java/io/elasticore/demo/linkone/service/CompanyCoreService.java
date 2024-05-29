//ecd:-1352768314H20240529174205V0.7
package io.elasticore.demo.linkone.service;

import io.elasticore.demo.linkone.entity.*;
import io.elasticore.demo.linkone.dto.*;
import io.elasticore.demo.linkone.repository.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
public class CompanyCoreService {

    protected final LinkoneRepositoryHelper helper;

    public List<CompanyDTO> findAll() {
        return helper.getCompany().findAll().stream()
                .map(LinkoneMapper::toDTO)
                .collect(Collectors.toList());
    }




    public Optional<CompanyDTO> findById(Long id) {
        return helper.getCompany().findById(id).map(LinkoneMapper::toDTO);
    }

    public CompanyDTO save(CompanyDTO dto) {
        Company entity = LinkoneMapper.toEntity(dto);
        
    


        Company result = helper.getCompany().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public CompanyDTO update(CompanyDTO dto) {
        Company entity = helper.getCompany().findById(dto.getComSeq()).orElse(null);
        LinkoneMapper.mapping(dto, entity, true);
        
    


        Company result = helper.getCompany().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public void deleteById(Long id) {
        helper.getCompany().deleteById(id);
    }



    public Page<CompanySeachResultDTO> findBySearch(CompanySearchDTO searchDTO) {
        Specification<Company> specification = LinkoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();

        return searchList(specification, pageable);
    }

    public Page<CompanySeachResultDTO> searchList(Specification<Company> specification, Pageable pageable) {
        CriteriaBuilder cb = helper.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<CompanySeachResultDTO> query = cb.createQuery(CompanySeachResultDTO.class);
        Root<Company> root = query.from(Company.class);

        query.select(cb.construct(
                CompanySeachResultDTO.class,

                root.get("comSeq") ,root.get("comGrpCode") ,root.get("comName") ,root.get("respName") ,root.get("respTel") ,root.get("createDate") ,root.get("createdBy") ,root.get("lastModifiedDate")
        ));

        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, query, cb);
            if(predicate!=null)
                query.where(predicate);
        }

        TypedQuery<CompanySeachResultDTO> typedQuery = helper.getEntityManager().createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<CompanySeachResultDTO> resultList = typedQuery.getResultList();



        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Company> countRoot = countQuery.from(Company.class);
        countQuery.select(cb.count(countRoot));

        if (specification != null) {
            Predicate countPredicate = specification.toPredicate(countRoot, countQuery, cb);
            if(countPredicate!=null)
                countQuery.where(countPredicate);
        }

        Long count = helper.getEntityManager().createQuery(countQuery).getSingleResult();

        return new PageImpl<CompanySeachResultDTO>(resultList, pageable, count);
    }


}

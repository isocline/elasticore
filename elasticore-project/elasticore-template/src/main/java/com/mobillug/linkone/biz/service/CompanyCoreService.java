//ecd:-2091966854H20240618012928_V0.8
package com.mobillug.linkone.biz.service;

import com.mobillug.linkone.biz.entity.*;
import com.mobillug.linkone.biz.dto.*;
import com.mobillug.linkone.biz.repository.*;

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



    public Page<CompanySearchResultDTO> findBySearch(CompanySearchDTO searchDTO) {
        Specification<Company> specification = LinkoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<Company> result = helper.getCompany().findAll(specification, pageable);
        return result.map(LinkoneMapper::toCompanySearchResultDTO);
    }


    public Optional<CompanyDTO> findById(String id) {
        return helper.getCompany().findById(id).map(LinkoneMapper::toDTO);
    }

    public CompanyDTO save(CompanyDTO dto) {
        Company entity = LinkoneMapper.toEntity(dto);
        
    


        Company result = helper.getCompany().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public CompanyDTO update(CompanyDTO dto) {
        Company entity = helper.getCompany().findById(dto.getId()).orElse(null);
        LinkoneMapper.mapping(dto, entity, true);
        
    


        Company result = helper.getCompany().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public void deleteById(String id) {
        helper.getCompany().deleteById(id);
    }




}

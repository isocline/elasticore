//ecd:329161181H20240618012928_V0.8
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
public class CustUserCoreService {

    protected final LinkoneRepositoryHelper helper;

    public List<CustUserDTO> findAll() {
        return helper.getCustUser().findAll().stream()
                .map(LinkoneMapper::toDTO)
                .collect(Collectors.toList());
    }



    public Page<CustUserDTO> findBySearch(CustUserSearchDTO searchDTO) {
        Specification<CustUser> specification = LinkoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<CustUser> result = helper.getCustUser().findAll(specification, pageable);
        return result.map(LinkoneMapper::toDTO);
    }


    public Optional<CustUserDTO> findById(String id) {
        return helper.getCustUser().findById(id).map(LinkoneMapper::toDTO);
    }

    public CustUserDTO save(CustUserDTO dto) {
        CustUser entity = LinkoneMapper.toEntity(dto);
        
        if(dto.getCompanyId()!=null){
            Company item = helper.getCompany().findById(dto.getCompanyId()).orElse(null);
            if(item!=null) entity.setCompany(item);
        }
    


        CustUser result = helper.getCustUser().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public CustUserDTO update(CustUserDTO dto) {
        CustUser entity = helper.getCustUser().findById(dto.getId()).orElse(null);
        LinkoneMapper.mapping(dto, entity, true);
        
        if(dto.getCompanyId()!=null){
            Company item = helper.getCompany().findById(dto.getCompanyId()).orElse(null);
            if(item!=null) entity.setCompany(item);
        }
    


        CustUser result = helper.getCustUser().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public void deleteById(String id) {
        helper.getCustUser().deleteById(id);
    }




}

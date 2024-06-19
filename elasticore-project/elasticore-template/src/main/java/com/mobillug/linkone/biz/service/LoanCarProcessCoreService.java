//ecd:-1740406328H20240618012928_V0.8
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
public class LoanCarProcessCoreService {

    protected final LinkoneRepositoryHelper helper;

    public List<LoanCarProcessDTO> findAll() {
        return helper.getLoanCarProcess().findAll().stream()
                .map(LinkoneMapper::toDTO)
                .collect(Collectors.toList());
    }



    public Page<LoanCarProcessDTO> findBySearch(LoanCarPrsSrchDTO searchDTO) {
        Specification<LoanCarProcess> specification = LinkoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<LoanCarProcess> result = helper.getLoanCarProcess().findAll(specification, pageable);
        return result.map(LinkoneMapper::toDTO);
    }


    public Optional<LoanCarProcessDTO> findById(Long id) {
        return helper.getLoanCarProcess().findById(id).map(LinkoneMapper::toDTO);
    }

    public LoanCarProcessDTO save(LoanCarProcessDTO dto) {
        LoanCarProcess entity = LinkoneMapper.toEntity(dto);
        
        if(dto.getRentComId()!=null){
            Company item = helper.getCompany().findById(dto.getRentComId()).orElse(null);
            if(item!=null) entity.setRentCompany(item);
        }
    


        LoanCarProcess result = helper.getLoanCarProcess().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public LoanCarProcessDTO update(LoanCarProcessDTO dto) {
        LoanCarProcess entity = helper.getLoanCarProcess().findById(dto.getLcpCode()).orElse(null);
        LinkoneMapper.mapping(dto, entity, true);
        
        if(dto.getRentComId()!=null){
            Company item = helper.getCompany().findById(dto.getRentComId()).orElse(null);
            if(item!=null) entity.setRentCompany(item);
        }
    


        LoanCarProcess result = helper.getLoanCarProcess().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public void deleteById(Long id) {
        helper.getLoanCarProcess().deleteById(id);
    }




}

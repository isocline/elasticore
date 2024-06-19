//ecd:1595351415H20240618012928_V0.8
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
public class LoanCarCoreService {

    protected final LinkoneRepositoryHelper helper;

    public List<LoanCarDTO> findAll() {
        return helper.getLoanCar().findAll().stream()
                .map(LinkoneMapper::toDTO)
                .collect(Collectors.toList());
    }



    public Page<LoanCarSearchResultDTO> findBySearch(LoanCarSearchDTO searchDTO) {
        Specification<LoanCar> specification = LinkoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<LoanCar> result = helper.getLoanCar().findAll(specification, pageable);
        return result.map(LinkoneMapper::toLoanCarSearchResultDTO);
    }


    public Optional<LoanCarDTO> findById(String id) {
        return helper.getLoanCar().findById(id).map(LinkoneMapper::toDTO);
    }

    public LoanCarDTO save(LoanCarDTO dto) {
        LoanCar entity = LinkoneMapper.toEntity(dto);
        
        if(dto.getRentComId()!=null){
            Company item = helper.getCompany().findById(dto.getRentComId()).orElse(null);
            if(item!=null) entity.setRentCompany(item);
        }
    


        LoanCar result = helper.getLoanCar().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public LoanCarDTO update(LoanCarDTO dto) {
        LoanCar entity = helper.getLoanCar().findById(dto.getId()).orElse(null);
        LinkoneMapper.mapping(dto, entity, true);
        
        if(dto.getRentComId()!=null){
            Company item = helper.getCompany().findById(dto.getRentComId()).orElse(null);
            if(item!=null) entity.setRentCompany(item);
        }
    


        LoanCar result = helper.getLoanCar().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public void deleteById(String id) {
        helper.getLoanCar().deleteById(id);
    }




}

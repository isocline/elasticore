//ecd:-213852360H20240806171759_V0.8
package com.mobillug.gateone.biz.service;

import com.mobillug.gateone.biz.entity.*;
import com.mobillug.gateone.biz.dto.*;
import com.mobillug.gateone.biz.repository.*;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
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
public class LoginUserCoreService {

    protected final GateoneRepositoryHelper helper;

    public List<LoginUserDTO> findAll() {
        return helper.getLoginUser().findAll().stream()
                .map(GateoneMapper::toDTO)
                .collect(Collectors.toList());
    }



    public Page<LoginUserSrchResultDTO> findBySearch(LoginUserSrchDTO searchDTO) {
        Specification<LoginUser> specification = GateoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<LoginUser> result = helper.getLoginUser().findAll(specification, pageable);
        return result.map(GateoneMapper::toLoginUserSrchResultDTO);
    }


    public Optional<LoginUserDTO> findById(String id) {
        return helper.getLoginUser().findById(id).map(GateoneMapper::toDTO);
    }

    public LoginUserDTO save(LoginUserDTO dto) {
        LoginUser entity = GateoneMapper.toEntity(dto);
        
    


        LoginUser result = helper.getLoginUser().save(entity);
        return GateoneMapper.toDTO(result);
    }


    public LoginUserDTO update(LoginUserDTO dto) {
        LoginUser entity = helper.getLoginUser().findById(dto.getId()).orElse(null);
        GateoneMapper.mapping(dto, entity, true);
        
    


        LoginUser result = helper.getLoginUser().save(entity);
        return GateoneMapper.toDTO(result);
    }


    public void deleteById(String id) {
        helper.getLoginUser().deleteById(id);
    }




}

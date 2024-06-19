//ecd:165606135H20240618012928_V0.8
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
public class LoginHistoryCoreService {

    protected final GateoneRepositoryHelper helper;

    public List<LoginHistoryDTO> findAll() {
        return helper.getLoginHistory().findAll().stream()
                .map(GateoneMapper::toDTO)
                .collect(Collectors.toList());
    }



    public Page<LoginHistorySrchResultDTO> findBySearch(LoginHistorySrchDTO searchDTO) {
        Specification<LoginHistory> specification = GateoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<LoginHistory> result = helper.getLoginHistory().findAll(specification, pageable);
        return result.map(GateoneMapper::toLoginHistorySrchResultDTO);
    }


    public Optional<LoginHistoryDTO> findById(Long id) {
        return helper.getLoginHistory().findById(id).map(GateoneMapper::toDTO);
    }

    public LoginHistoryDTO save(LoginHistoryDTO dto) {
        LoginHistory entity = GateoneMapper.toEntity(dto);
        
    


        LoginHistory result = helper.getLoginHistory().save(entity);
        return GateoneMapper.toDTO(result);
    }


    public LoginHistoryDTO update(LoginHistoryDTO dto) {
        LoginHistory entity = helper.getLoginHistory().findById(dto.getSeq()).orElse(null);
        GateoneMapper.mapping(dto, entity, true);
        
    


        LoginHistory result = helper.getLoginHistory().save(entity);
        return GateoneMapper.toDTO(result);
    }


    public void deleteById(Long id) {
        helper.getLoginHistory().deleteById(id);
    }




}

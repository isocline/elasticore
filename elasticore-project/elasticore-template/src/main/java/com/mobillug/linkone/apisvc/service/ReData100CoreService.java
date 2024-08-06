//ecd:999406132H20240805175916_V0.8
package com.mobillug.linkone.apisvc.service;

import com.mobillug.linkone.apisvc.entity.*;
import com.mobillug.linkone.apisvc.dto.*;
import com.mobillug.linkone.apisvc.repository.*;

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
public class ReData100CoreService {

    protected final ApisvcRepositoryHelper helper;

    public List<Re100> findAll() {
        return helper.getReData100().findAll().stream()
                .map(ApisvcMapper::toDTO)
                .collect(Collectors.toList());
    }



    public Page<Re100> findBySearch(Re100SrchDTO searchDTO) {
        Specification<ReData100> specification = ApisvcMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<ReData100> result = helper.getReData100().findAll(specification, pageable);
        return result.map(ApisvcMapper::toDTO);
    }


    public Optional<Re100> findById(Long id) {
        return helper.getReData100().findById(id).map(ApisvcMapper::toDTO);
    }

    public ReData100DTO save(Re100 dto) {
        ReData100 entity = ApisvcMapper.toEntity(dto);
        
    


        ReData100 result = helper.getReData100().save(entity);
        return ApisvcMapper.toDTO(result);
    }


    public ReData100DTO update(Re100 dto) {
        ReData100 entity = helper.getReData100().findById(dto.getId()).orElse(null);
        ApisvcMapper.mapping(dto, entity, true);
        
    


        ReData100 result = helper.getReData100().save(entity);
        return ApisvcMapper.toDTO(result);
    }


    public void deleteById(Long id) {
        helper.getReData100().deleteById(id);
    }




}

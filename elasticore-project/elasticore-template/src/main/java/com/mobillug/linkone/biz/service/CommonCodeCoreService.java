//ecd:-894749906H20240618012928_V0.8
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
public class CommonCodeCoreService {

    protected final LinkoneRepositoryHelper helper;

    public List<CommonCodeDTO> findAll() {
        return helper.getCommonCode().findAll().stream()
                .map(LinkoneMapper::toDTO)
                .collect(Collectors.toList());
    }



    public Page<CommonCodeDTO> findBySearch(CommonCodeSearchDTO searchDTO) {
        Specification<CommonCode> specification = LinkoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<CommonCode> result = helper.getCommonCode().findAll(specification, pageable);
        return result.map(LinkoneMapper::toDTO);
    }


    public Optional<CommonCodeDTO> findById(Long id) {
        return helper.getCommonCode().findById(id).map(LinkoneMapper::toDTO);
    }

    public CommonCodeDTO save(CommonCodeDTO dto) {
        CommonCode entity = LinkoneMapper.toEntity(dto);
        
    


        CommonCode result = helper.getCommonCode().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public CommonCodeDTO update(CommonCodeDTO dto) {
        CommonCode entity = helper.getCommonCode().findById(dto.getCodeSn()).orElse(null);
        LinkoneMapper.mapping(dto, entity, true);
        
    


        CommonCode result = helper.getCommonCode().save(entity);
        return LinkoneMapper.toDTO(result);
    }


    public void deleteById(Long id) {
        helper.getCommonCode().deleteById(id);
    }




}

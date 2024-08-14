//ecd:617692237H20240806171759_V0.8
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
public class ServiceInfoCoreService {

    protected final GateoneRepositoryHelper helper;

    public List<ServiceInfoDTO> findAll() {
        return helper.getServiceInfo().findAll().stream()
                .map(GateoneMapper::toDTO)
                .collect(Collectors.toList());
    }



    public Page<ServiceInfoSrchResultDTO> findBySearch(ServiceInfoSrchDTO searchDTO) {
        Specification<ServiceInfo> specification = GateoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<ServiceInfo> result = helper.getServiceInfo().findAll(specification, pageable);
        return result.map(GateoneMapper::toServiceInfoSrchResultDTO);
    }


    public Optional<ServiceInfoDTO> findById(String id) {
        return helper.getServiceInfo().findById(id).map(GateoneMapper::toDTO);
    }

    public ServiceInfoDTO save(ServiceInfoDTO dto) {
        ServiceInfo entity = GateoneMapper.toEntity(dto);
        
    


        ServiceInfo result = helper.getServiceInfo().save(entity);
        return GateoneMapper.toDTO(result);
    }


    public ServiceInfoDTO update(ServiceInfoDTO dto) {
        ServiceInfo entity = helper.getServiceInfo().findById(dto.getId()).orElse(null);
        GateoneMapper.mapping(dto, entity, true);
        
    


        ServiceInfo result = helper.getServiceInfo().save(entity);
        return GateoneMapper.toDTO(result);
    }


    public void deleteById(String id) {
        helper.getServiceInfo().deleteById(id);
    }




}

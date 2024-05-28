//ecd:-898188634H20240528142512V0.7
package io.elasticore.demo.linkone.service;

import io.elasticore.demo.linkone.entity.*;
import io.elasticore.demo.linkone.dto.*;
import io.elasticore.demo.linkone.repository.*;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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

    public Optional<CustUserDTO> findById(Long id) {
        return helper.getCustUser().findById(id).map(LinkoneMapper::toDTO);
    }

    public CustUserDTO save(CustUserDTO dto) {
        CustUser entity = LinkoneMapper.toEntity(dto);
        
        if(dto.getCompanyComSeq()!=null){
            Company item = helper.getCompany().findById(dto.getCompanyComSeq()).orElse(null);
            if(item!=null) entity.setCompany(item);
        }
    


        CustUser result = helper.getCustUser().save(entity);
        return LinkoneMapper.toDTO(result);
    }

    public CustUserDTO update(CustUserDTO dto) {
        CustUser entity = helper.getCustUser().findById(dto.getUsrSeq()).orElse(null);
        LinkoneMapper.mapping(dto, entity, true);
        
        if(dto.getCompanyComSeq()!=null){
            Company item = helper.getCompany().findById(dto.getCompanyComSeq()).orElse(null);
            if(item!=null) entity.setCompany(item);
        }
    


        CustUser result = helper.getCustUser().save(entity);
        return LinkoneMapper.toDTO(result);
    }

    public void deleteById(Long id) {
        helper.getCustUser().deleteById(id);
    }
}

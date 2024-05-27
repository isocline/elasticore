//ecd:2116102760H20240528005455V0.7
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
public class CompanyCoreService {

    private final LinkoneRepositoryHelper helper;

    public List<CompanyDTO> findAll() {
        return helper.getCompany().findAll().stream()
                .map(LinkoneMapper::toDTO)
                .collect(Collectors.toList());
    }


    public Page<CompanyDTO> findBySearch(CompanySearchDTO searchDTO) {
        Specification<Company> specification = LinkoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<Company> result = helper.getCompany().findAll(specification, pageable);
        return result.map(LinkoneMapper::toDTO);
    }

    public Optional<CompanyDTO> findById(Long id) {
        return helper.getCompany().findById(id).map(LinkoneMapper::toDTO);
    }

    public CompanyDTO save(CompanyDTO dto) {
        Company entity = LinkoneMapper.toEntity(dto);
        
    

        
        Company result = helper.getCompany().save(entity);
        return LinkoneMapper.toDTO(result);
    }

    public void deleteById(Long id) {
        helper.getCompany().deleteById(id);
    }
}

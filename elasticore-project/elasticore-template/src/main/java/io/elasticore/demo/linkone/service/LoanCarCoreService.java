//ecd:990658389H20240527134253V0.7
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
public class LoanCarCoreService {

    private final LoanCarRepository repository;

    public List<LoanCarDTO> findAll() {
        return repository.findAll().stream()
                .map(LinkoneMapper::toDTO)
                .collect(Collectors.toList());
    }


    public Page<LoanCarDTO> findBySearch(LoanCarSearchDTO searchDTO) {
        Specification<LoanCar> specification = LinkoneMapper.toSpec(searchDTO);
        Pageable pageable = searchDTO.getPageable();
        Page<LoanCar> result = repository.findAll(specification, pageable);
        return result.map(LinkoneMapper::toDTO);
    }

    public Optional<LoanCarDTO> findById(Long id) {
        return repository.findById(id).map(LinkoneMapper::toDTO);
    }

    public LoanCarDTO save(LoanCarDTO dto) {
        LoanCar entity = LinkoneMapper.toEntity(dto);
        LoanCar result = repository.save(entity);
        return LinkoneMapper.toDTO(result);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

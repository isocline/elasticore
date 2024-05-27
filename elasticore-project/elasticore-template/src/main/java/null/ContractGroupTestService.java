//ecd:404498632H20240527110524V0.7
package ;


import io.elasticore.demo.crm.entity.*;

import io.elasticore.demo.crm.dto.*;

import io.elasticore.demo.crm.repository.*;


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
public class ContractGroupTestService {


    private final ContractGroupRepository repository;


    public List<ContractGroupDTO> findAll() {

        return repository.findAll().stream()
                .map(CrmTestMapper::toDTO)

                .collect(Collectors.toList());
    }

    public List<ContractGroupDTO> findAll( searchDTO) {

        Specification<ContractGroup> specification = CrmTestMapper.toSpec(searchDTO);

        Sort sort = searchDTO.getSort();
        return repository.findAll(specification, sort).stream()
                .map(CrmTestMapper::toDTO)

                .collect(Collectors.toList());
    }

    public Page<ContractGroupDTO> findAllByPage( searchDTO) {

        Specification<ContractGroup> specification = CrmTestMapper.toSpec(searchDTO);

        Pageable pageable = searchDTO.getPageable();
        Page<ContractGroup> result = repository.findAll(specification, pageable);

        return result.map(CrmTestMapper::toDTO);

    }

    public Optional<ContractGroupDTO> findById(Long id) {

        return repository.findById(id).map(CrmTestMapper::toDTO);

    }

    public ContractGroupDTO save(ContractGroupDTO dto) {

        ContractGroup entity = CrmTestMapper.toEntity(dto);

        ContractGroup result = repository.save(entity);

        return CrmTestMapper.toDTO(result);

    }


        repository.deleteById(id);
    }
}

package io.elasticore.demo.crm.service;


import io.elasticore.demo.crm.entity.ContractGroup;
import io.elasticore.demo.crm.repository.ContractGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestService {

    private final ContractGroupRepository contractGroupRepository;


    public void test() {

        ContractGroup grp = new ContractGroup();
        grp.setGroupName("test");
        contractGroupRepository.save(grp);

        ContractGroup grp2 = new ContractGroup();
        grp2.setGroupName("test2");
        contractGroupRepository.save(grp2);


        contractGroupRepository.findAllByOrderByGrpSeqDesc();
    }
}

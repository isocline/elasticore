
package io.elasticore.demo.crm.service;


import io.elasticore.demo.crm.dto.SelectCnctCustListOutput;
import io.elasticore.demo.crm.entity.ContractGroup;
import io.elasticore.demo.crm.repository.ContactInfoRepository;
import io.elasticore.demo.crm.repository.ContractGroupRepository;
import io.elasticore.demo.crm.repository.RepositoryHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestService {

    private final ContractGroupRepository contractGroupRepository;

    private final ContactInfoRepository contactInfoRepository;


    private final RepositoryHelper repositoryHelper;

    public void test() {

        ContractGroup grp = new ContractGroup();
        grp.setGroupName("test");
        contractGroupRepository.save(grp);

        ContractGroup grp2 = new ContractGroup();
        grp2.setGroupName("test2");
        contractGroupRepository.save(grp2);


        List<ContractGroup> list = contractGroupRepository.findAllByOrderByGrpSeqDesc();

        for (ContractGroup g : list) {
            System.err.println(g.getGrpSeq() + " : " + g.getGroupName());

        }

        List<SelectCnctCustListOutput> list2 = repositoryHelper.selectCnctCustList(34);

        //List<ContractGroupDTO> list2 = contactInfoRepository.selectCnctCustList(34);
        for (SelectCnctCustListOutput g : list2) {
            System.err.println(g);

        }
    }
}

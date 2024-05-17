package io.elasticore.demo.crm.service;


import io.elasticore.demo.crm.dto.SelectCnctCustListOutput;
import io.elasticore.demo.crm.entity.ContractGroup;
import io.elasticore.demo.crm.repository.RepositoryHelper;
import io.elasticore.demo.crm.repository.RepositoryHelper2;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestService {

    private final RepositoryHelper2 repositoryHelper;


    public void test() {
        List<SelectCnctCustListOutput>  list = repositoryHelper.selectCnctCustList(34);

        for(SelectCnctCustListOutput item: list) {
            System.err.println( item);
        }

        List<ContractGroup> list2 = repositoryHelper.findAllConnectionGroups("test", null);
        for(ContractGroup item: list2) {
            System.err.println( item);
        }
    }
}

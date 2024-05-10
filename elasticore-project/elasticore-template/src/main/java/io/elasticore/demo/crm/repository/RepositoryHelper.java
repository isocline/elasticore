package io.elasticore.demo.crm.repository;


import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

import io.elasticore.demo.crm.dto.*;


@Getter
@Service
@AllArgsConstructor
public class RepositoryHelper {


    private final ContractGroupRepository contractGroup;
    
    private final ContactInfoRepository contactInfo;
    


    public List<SelectCnctCustListOutput> selectCnctCustList(Integer grpSeq) {
        String[] fieldNames = {"carSeq" ,"contrNm" ,"custNm" ,"contrTel" ,"rentAmt" ,"exceptAmt" ,"penaltyRate" ,"contractNo" ,"driverSeqNo" ,"custNo"};
        List<Object[]> result = contactInfo.selectCnctCustList(grpSeq);
        return new ModelTransList<SelectCnctCustListOutput>(result, SelectCnctCustListOutput.class, fieldNames);
    }
    

}




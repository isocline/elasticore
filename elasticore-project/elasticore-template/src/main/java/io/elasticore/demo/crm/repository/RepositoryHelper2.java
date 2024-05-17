//ecd:426870136H20240513200310V0.7
package io.elasticore.demo.crm.repository;


import io.elasticore.base.util.ModelTransList;

import io.elasticore.demo.crm.entity.ContractGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

import io.elasticore.demo.crm.dto.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;


@Getter
@Service
@AllArgsConstructor
public class RepositoryHelper2 {


    private final ContractGroupRepository contractGroup;
    
    private final ContactInfoRepository contactInfo;


    private final EntityManager entityManager;

    public List<ContractGroup> findAllConnectionGroups(String groupName, Integer grpSeq) {
        StringBuilder sb = new StringBuilder("SELECT * FROM T_CNCT_GRP");
        sb.append( " WHERE 1=1 ");

        if(groupName!=null && !groupName.isEmpty())
            sb.append(" AND group_name like CONCAT('%', :groupName, '%') ");

        if(grpSeq!=null)
            sb.append(" AND grp_seq = :grpSeq");

        Query query = entityManager.createQuery(sb.toString(), ContractGroup.class);

        if(groupName!=null && !groupName.isEmpty())
            query.setParameter("groupName" , groupName);

        if(grpSeq!=null)
            query.setParameter("grpSeq" , grpSeq);


        return query.getResultList();
    }

    public List<SelectCnctCustListOutput> selectCnctCustList(Integer grpSeq) {
        String[] fieldNames = {"carSeq" ,"contrNm" ,"custNm" ,"contrTel" ,"rentAmt" ,"exceptAmt" ,"penaltyRate" ,"contractNo" ,"driverSeqNo" ,"custNo"};
        List<Object[]> result = contactInfo.selectCnctCustList(grpSeq);
        return new ModelTransList<SelectCnctCustListOutput>(result, SelectCnctCustListOutput.class, fieldNames);
    }
    

}




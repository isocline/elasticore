//ecd:39791309H20240806171758_V0.8
package io.elasticore.demo.crm.repository;

import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;



import io.elasticore.demo.crm.entity.*;
import io.elasticore.demo.crm.dto.*;




@Getter
@Service
@AllArgsConstructor
public class CrmTestRepositoryHelper {

    private final EntityManager entityManager;

    private final ContractGroupRepository contractGroup;
    
    private final ContactInfoRepository contactInfo;
    

    public List<Object[]> selectCnctList2(String contractTel ,String contractName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from T_CNCT_LST ");
        sb.append("where 1=1 ");
        if(contractName!=null)
          sb.append("and contractName = :contractName ");
        if(contractTel!=null)
          sb.append("and contractTel = :contractTel ");
        Query query = entityManager.createNativeQuery(sb.toString());
        if(contractName!=null)
          query.setParameter("contractName" , contractName);
        if(contractTel!=null)
          query.setParameter("contractTel" , contractTel);
        return query.getResultList();
    }
    
    public List<ContactInfo> listByContractTelAndContractName(String contractTel ,String contractName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ContactInfo ");
        sb.append("where 1=1 ");
        if(contractName!=null)
          sb.append("and contractName = :contractName ");
        if(contractTel!=null)
          sb.append("and contractTel = :contractTel ");
        Query query = entityManager.createQuery(sb.toString(), ContactInfo.class);
        if(contractName!=null)
          query.setParameter("contractName" , contractName);
        if(contractTel!=null)
          query.setParameter("contractTel" , contractTel);
        return query.getResultList();
    }
    
    public List<Object[]> selectCnctList4(String contractTel ,String contractName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append(" B.CONTRACT_NO ");
        sb.append(", B.DRIVER_SEQ_NO ");
        sb.append(", C.CUST_NO ");
        sb.append("from T_CNCT_LST ");
        sb.append("where 1=1 ");
        if(contractName!=null)
          sb.append("and contractName = :contractName ");
        if(contractTel!=null)
          sb.append("and contractTel = :contractTel ");
        Query query = entityManager.createNativeQuery(sb.toString());
        if(contractName!=null)
          query.setParameter("contractName" , contractName);
        if(contractTel!=null)
          query.setParameter("contractTel" , contractTel);
        return query.getResultList();
    }
    
    public List<SelectCnctCustListOutput> selectCnctCustList(Integer grpSeq) {
        String[] fieldNames = {"carSeq" ,"contrNm" ,"custNm" ,"contrTel" ,"rentAmt" ,"exceptAmt" ,"penaltyRate" ,"contractNo" ,"driverSeqNo" ,"custNo"};
        List<Object[]> result = contactInfo.selectCnctCustList(grpSeq);
        return new ModelTransList<SelectCnctCustListOutput>(result, SelectCnctCustListOutput.class, fieldNames);
    }
    
}




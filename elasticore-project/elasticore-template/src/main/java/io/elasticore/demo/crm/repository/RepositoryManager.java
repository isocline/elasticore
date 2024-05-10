package io.elasticore.demo.crm.repository;

import io.elasticore.base.util.ModelTransList;
import io.elasticore.demo.crm.dto.ContractGroupDTO;
import io.elasticore.demo.crm.dto.ContractGroupDTO2;
import io.elasticore.demo.crm.dto.SelectCnctCustListWrapDTO;
import lombok.AllArgsConstructor;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryManager {

    private final ContactInfoRepository contactInfo;

    private final ContractGroupRepository contractGroup;

    @PersistenceContext
    private EntityManager entityManager;

    private final static String SQL = " SELECT CASE WHEN C.CAR_SEQ IS NULL THEN -1 ELSE C.CAR_SEQ END AS carSeq"
            + "              , CASE WHEN B.CONTR_NM IS NULL THEN A.CONTACT_NAME ELSE B.CONTR_NM END AS contrNm"
            + "              , CASE WHEN D.CUST_NM IS NULL THEN '' ELSE D.CUST_NM END AS custName"
            + "              , CASE WHEN B.CONTR_TEL IS NULL THEN A.CONTACT_TEL ELSE B.CONTR_TEL END AS contrTel"

            + "         FROM T_CNCT_LST A"
            + "                  LEFT OUTER JOIN T_CONTRACT B ON A.CONTRACT_NO = B.CONTRACT_NO"
            + "                  LEFT OUTER JOIN T_CAR C ON A.CONTRACT_NO = C.CONTRACT_NO"
            + "                  LEFT OUTER JOIN T_CUST D ON C.CUST_NO = D.CUST_NO"
            + "         WHERE 1 = 1"
            + "           AND A.GRP_SEQ = :grpSeq";

    public List<ContractGroupDTO> selectCnctCustList(Integer grpSeq) {


        Query query = entityManager.createNativeQuery(SQL);

        query.setParameter("grpSeq", grpSeq);
        return query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(ContractGroupDTO.class))
                .getResultList();

    }


    public List<ContractGroupDTO2> selectCnctCustList2(Integer grpSeq) {


        Query query = entityManager.createNativeQuery(SQL);

        query.setParameter("grpSeq", grpSeq);
        List<Object[]> results = query.getResultList();

        return results.stream()
                .map(result -> new ContractGroupDTO2(result)).collect(Collectors.toList());


    }


    public List<ContractGroupDTO2> selectCnctCustList3(Integer grpSeq) {

        List<Object[]> result = contactInfo.selectCnctCustList(grpSeq);
        List<ContractGroupDTO2> li = new ModelTransList<ContractGroupDTO2>(result, ContractGroupDTO2.class);
        return li;
    }


    public List<SelectCnctCustListWrapDTO> selectCnctCustList4(Integer grpSeq) {
        String[] fieldNames = { "carSeq","contrNm","custNm"};
        List<Object[]> result = contactInfo.selectCnctCustList(grpSeq);
        List<SelectCnctCustListWrapDTO> li = new ModelTransList<SelectCnctCustListWrapDTO>(result, SelectCnctCustListWrapDTO.class, fieldNames);
        return li;
    }



}

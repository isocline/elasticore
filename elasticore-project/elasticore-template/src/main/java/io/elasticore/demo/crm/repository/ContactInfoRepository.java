//ecd:-2130818304H20240528142512V0.7
package io.elasticore.demo.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.*;
import io.elasticore.demo.crm.entity.*;
import io.elasticore.demo.crm.dto.*;



/**


 */



public interface ContactInfoRepository extends JpaRepository<ContactInfo,ContactInfoIdentity> , JpaSpecificationExecutor<LoanCar> {

    List<ContactInfo> findAllByIdGrpSeqOrderByIdContactSeqDesc(Integer grpSeq);
    
    @Query(nativeQuery=true, value=" SELECT CASE WHEN C.CAR_SEQ IS NULL THEN -1 ELSE C.CAR_SEQ END AS CAR_SEQ /* type:int */"
     		 +  "              , CASE WHEN B.CONTR_NM IS NULL THEN A.CONTACT_NAME ELSE B.CONTR_NM END AS CONTR_NM"
     		 +  "              , CASE WHEN D.CUST_NM IS NULL THEN '' ELSE D.CUST_NM END AS CUST_NM"
     		 +  "              , CASE WHEN B.CONTR_TEL IS NULL THEN A.CONTACT_TEL ELSE B.CONTR_TEL END AS CONTR_TEL"
     		 +  "              , CASE WHEN C.RENT_AMT IS NULL THEN 0 ELSE C.RENT_AMT END AS RENT_AMT /* type:int */"
     		 +  "              , CASE WHEN C.EXCEPT_AMT IS NULL THEN 0 ELSE C.EXCEPT_AMT END AS EXCEPT_AMT /* type:int */"
     		 +  "              , CASE WHEN B.PENALTY_RATE IS NULL THEN 0 ELSE B.PENALTY_RATE END AS PENALTY_RATE"
     		 +  "              , B.CONTRACT_NO"
     		 +  "              , B.DRIVER_SEQ_NO"
     		 +  "              , C.CUST_NO"
     		 +  "         FROM T_CNCT_LST A"
     		 +  "                  LEFT OUTER JOIN T_CONTRACT B ON A.CONTRACT_NO = B.CONTRACT_NO"
     		 +  "                  LEFT OUTER JOIN T_CAR C ON A.CONTRACT_NO = C.CONTRACT_NO"
     		 +  "                  LEFT OUTER JOIN T_CUST D ON C.CUST_NO = D.CUST_NO"
     		 +  "         WHERE 1 = 1"
     		 +  "           AND A.GRP_SEQ = :grpSeq"
     		 +  "         ORDER BY A.CTR_SEQ;")
    List<Object[]> selectCnctCustList(@Param("grpSeq") Integer grpSeq);
    

}

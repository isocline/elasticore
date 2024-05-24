//ecd:1888853266H20240524175232V0.7
package io.elasticore.demo.linkone.repository;


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




import io.elasticore.demo.linkone.entity.*;

import io.elasticore.demo.linkone.dto.*;





@Getter
@Service
@AllArgsConstructor
public class LinkoneRepositoryHelper {


    private final EntityManager entityManager;

    private final LoanCarRepository loanCar;
    
    private final CallInfoRepository callInfo;
    
    private final LoanCarProcessRepository loanCarProcess;
    
    private final CapitalRepository capital;
    


    public Page<LoanCar> listByInsuranceCodeAndLcRepairShopName(String insuranceCode ,String lcRepairShopName ,Pageable pageable) {
      StringBuilder sb = new StringBuilder();
        sb.append("select a from LoanCar a");
        sb.append("where 1=1");
        if(insuranceCode!=null)
          sb.append("and insuranceCode = :insuranceCode");
        if(lcRepairShopName!=null)
          sb.append("and lcRepairShopName = :lcRepairShopName");
        Query query = entityManager.createQuery(sb.toString(), LoanCar.class);
        if(insuranceCode!=null)
          query.setParameter("insuranceCode" , insuranceCode);
        if(lcRepairShopName!=null)
          query.setParameter("lcRepairShopName" , lcRepairShopName);
        int totalRows = query.getResultList().size();
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return new PageImpl<LoanCar>(query.getResultList(), pageable, totalRows);
    }
    

}




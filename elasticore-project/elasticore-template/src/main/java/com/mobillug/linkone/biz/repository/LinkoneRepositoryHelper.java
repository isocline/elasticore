//ecd:-1168457926H20240618012928_V0.8
package com.mobillug.linkone.biz.repository;

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



import com.mobillug.linkone.biz.entity.*;
import com.mobillug.linkone.biz.dto.*;




@Getter
@Service
@AllArgsConstructor
public class LinkoneRepositoryHelper {

    private final EntityManager entityManager;

    private final CompanyRepository company;
    
    private final LoanCarProcessRepository loanCarProcess;
    
    private final CommonCodeRepository commonCode;
    
    private final CustUserRepository custUser;
    
    private final CallHistoryRepository callHistory;
    
    private final LoanCarRepository loanCar;
    

    public List<Object[]> selectCnctList4(String comName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append(" B.CONTRACT_NO ");
        sb.append(", B.DRIVER_SEQ_NO ");
        sb.append(", C.CUST_NO ");
        sb.append("from company ");
        sb.append("where 1=1 ");
        if(comName!=null)
          sb.append("and contractName = :comName ");
        Query query = entityManager.createNativeQuery(sb.toString());
        if(comName!=null)
          query.setParameter("comName" , comName);
        return query.getResultList();
    }
    
    public Page<CompanyLoanCarProcessDTO> selectLoanCarProcessByCompanyId(String companyId ,String custNm ,String custTel ,Pageable pageable) {
        String[] fieldNames = {"id" ,"createDate" ,"custNm" ,"custTel" ,"rentCarNm" ,"processType" ,"memo" ,"applyDate" ,"applyTime" ,"custReqMemo" ,"insureyn"};
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  lc.id, lcp.create_date, lc.cust_nm , lc.cust_tel, lc.rent_car_nm , lcp.process_type ");
        sb.append("  ,lcp.memo,lcp.apply_date ,lcp.apply_time ,lcp.cust_req_memo ,lcp.insureyn ");
        sb.append("FROM Loan_Car_Process lcp ");
        sb.append("JOIN Loan_Car lc ON lcp.loan_car_master_id = lc.id ");
        sb.append("where ");
        sb.append("  lcp.rent_company_id  =  :companyId ");
        if(custNm!=null)
          sb.append("and lc.cust_nm like concat('%' , :custNm ,'%') ");
        if(custTel!=null)
          sb.append("and lc.cust_tel like concat('%' , :custTel ,'%') ");
        sb.append("order by lcp.create_date  desc ");
        Query query = entityManager.createNativeQuery(sb.toString());
        if(custNm!=null)
          query.setParameter("custNm" , custNm);
        if(custTel!=null)
          query.setParameter("custTel" , custTel);
        query.setParameter("companyId" , companyId);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
    
        Query countQuery = entityManager.createNativeQuery("select count(*) from ( "+sb+" ) as X");
        if(custNm!=null)
          countQuery.setParameter("custNm" , custNm);
        if(custTel!=null)
          countQuery.setParameter("custTel" , custTel);
        countQuery.setParameter("companyId" , companyId);
        long totalRows = ((Number) countQuery.getSingleResult()).longValue();
        ModelTransList<CompanyLoanCarProcessDTO> list = 
           new ModelTransList<CompanyLoanCarProcessDTO>(query.getResultList(), CompanyLoanCarProcessDTO.class, fieldNames);
        return new PageImpl<CompanyLoanCarProcessDTO>(list, pageable, totalRows);
    }
    
}




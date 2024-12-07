//ecd:1110780167H20241207204629_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.repository;

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

import com.xsolcorpkorea.elasticore.test.rollup.entity.*;
import com.xsolcorpkorea.elasticore.test.rollup.dto.*;



@Getter
@Service
@AllArgsConstructor
public class SearchResultRepositoryHelper {

    private final EntityManager entityManager;

    private final PersonRepository person;
    
    private final PersonGroupRepository personGroup;
    
    private final CommonCodeRepository commonCode;
    
    private final OrganizationRepository organization;
    
    private final EmployeeRepository employee;
    
    private final EmpFamilyRepository empFamily;
    
    private final EmpEducationRepository empEducation;
    
    private final CustomerRepository customer;
    
    private final CustContactHistoryRepository custContactHistory;
    
    private final CustInsuranceStatusRepository custInsuranceStatus;
    
    private final ContractRepository contract;
    
    private final ContractSpecialClauseRepository contractSpecialClause;
    
    private final ContractRelatedRepository contractRelated;
    
    private final ContractPaymentRepository contractPayment;
    
    private final InsuranceCompanyCodeRepository insuranceCompanyCode;
    
    private final InsProductCodeRepository insProductCode;
    
    private final CommissionMasterRepository commissionMaster;
    
    private final CommissionContractDetailsRepository commissionContractDetails;
    
    private final CommissionDetailsRepository commissionDetails;
    

    public Page<TestDTO> test2(String id ,Pageable pageable) {
        String[] fieldNames = {};
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT series_Name ");
        sb.append("FROM series_info lc ");
        sb.append("where lc.id  = :id ");
        sb.append("   ");
        java.util.Map<String, Object> placeholders = new java.util.HashMap<>();
        String sql = ModelTransList.replace(sb.toString(),  placeholders);
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id" , id);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
    
        Query countQuery = entityManager.createNativeQuery("select count(*) from ( "+sb+" ) as X");
        countQuery.setParameter("id" , id);
        long totalRows = ((Number) countQuery.getSingleResult()).longValue();
        ModelTransList<TestDTO> list = 
           new ModelTransList<TestDTO>(query.getResultList(), TestDTO.class, fieldNames);
        return new PageImpl<TestDTO>(list, pageable, totalRows);
    }
    
}




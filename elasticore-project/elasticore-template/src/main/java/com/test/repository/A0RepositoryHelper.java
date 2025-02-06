//ecd:1481373465H20250204110511_V1.0
package com.test.repository;

import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import com.test.entity.*;
import com.test.dto.*;



@Getter
@Service
@AllArgsConstructor
public class A0RepositoryHelper {

    private final EntityManager entityManager;

    private final CompanyRepository company;
    
    private final EmployeeRepository employee;
    

    public Page<EmployeeCompanyDTO> selectJoinData(Integer minAge ,Integer maxAge ,Pageable pageable) {
        String[] fieldNames = {"employeeId" ,"employeeName" ,"genderCode" ,"employeeAge" ,"employeeAddress" ,"isAdult" ,"companyId" ,"companyName" ,"companyAddress" ,"companyPhone" ,"companyCapital"};
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  ");
        sb.append("  emp.id AS employee_id, ");
        sb.append("  emp.name AS employee_name, ");
        sb.append("  emp.gender AS gender_code, ");
        sb.append("  emp.age AS employee_age, ");
        sb.append("  emp.addr AS employee_address, ");
        sb.append("  emp.is_adult AS is_adult, ");
        sb.append("  com.cid AS company_id, ");
        sb.append("  com.name AS company_name, ");
        sb.append("  com.addr AS company_address, ");
        sb.append("  com.tel_no AS company_phone, ");
        sb.append("  com.capital AS company_capital ");
        sb.append("FROM ");
        sb.append("Employee emp ");
        sb.append("INNER JOIN ");
        sb.append("Company com ");
        sb.append("ON ");
        sb.append("emp.company = com.cid ");
        sb.append("WHERE ");
        sb.append("emp.age BETWEEN :minAge AND :maxAge   ");
        sb.append("ORDER BY ");
        sb.append("emp.name ASC, com.name ASC; ");
        java.util.Map<String, Object> placeholders = new java.util.HashMap<>();
        String sql = ModelTransList.replace(sb.toString(),  placeholders);
        Query query = createQuery(sql, true, EmployeeCompanyDTO.class, pageable);
        query.setParameter("maxAge" , maxAge);
        query.setParameter("minAge" , minAge);
    
        Query countQuery = entityManager.createNativeQuery("select count(*) from ( "+sb+" ) as X");
        countQuery.setParameter("minAge" , minAge);
        countQuery.setParameter("maxAge" , maxAge);
        long totalRows = ((Number) countQuery.getSingleResult()).longValue();
        ModelTransList<EmployeeCompanyDTO> list = 
           new ModelTransList<EmployeeCompanyDTO>(query.getResultList(), EmployeeCompanyDTO.class, fieldNames);
        return new PageImpl<EmployeeCompanyDTO>(list, pageable, totalRows);
    }
    

    protected Query createQuery(String sql, boolean isNativeQuery, Class clz, Pageable pageable) {
        if (pageable!=null && pageable.getSort()!=null && pageable.getSort().isSorted()) {
            StringBuilder orderBy = new StringBuilder(" ORDER BY ");
            Iterator<Sort.Order> iterator = pageable.getSort().stream().iterator();
            while (iterator.hasNext()) {
                Sort.Order order = iterator.next();
                String properyNm = order.getProperty();
                if(isNativeQuery)
                    properyNm = io.elasticore.base.util.StringUtils.camelToSnake(properyNm);
                orderBy.append(properyNm)
                        .append(" ")
                        .append(order.getDirection().isAscending() ? "ASC" : "DESC");
                if (iterator.hasNext()) {
                    orderBy.append(", ");
                }
            }
            sql = sql+" "+orderBy.toString();
        }
        Query query = null;
        if(isNativeQuery) {
            query = entityManager.createNativeQuery(sql);
        }else{
            query = entityManager.createQuery(sql, clz);
        }
        if(pageable!=null) {
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }
        return query;
    }
}




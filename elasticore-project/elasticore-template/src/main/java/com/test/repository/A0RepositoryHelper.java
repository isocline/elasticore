//ecd:728343991H20250204104408_V1.0
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
    

    public List<EmployeeCompanyDTO> selectJoinData(Integer minAge ,Integer maxAge) {
        String[] fieldNames = {"employeeId" ,"employeeName" ,"genderCode" ,"employeeAge" ,"employeeAddress" ,"isAdult" ,"companyId" ,"companyName" ,"companyAddress" ,"companyPhone" ,"companyCapital"};
        List<Object[]> result = company.selectJoinData(minAge ,maxAge);
        return new ModelTransList<EmployeeCompanyDTO>(result, EmployeeCompanyDTO.class, fieldNames);
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




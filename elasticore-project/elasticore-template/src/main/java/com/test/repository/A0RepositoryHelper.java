//ecd:783305139H20241224183121_V1.0
package com.test.repository;

import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

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

    private final CommonCodeRepository commonCode;
    
    private final ArticleRepository article;
    

    public String selectTrimMax(String param) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT max(a.car_id) as id ");
        sb.append("FROM base_car_info a ");
        sb.append("WHERE a.car_id LIKE :param ");
        java.util.Map<String, Object> placeholders = new java.util.HashMap<>();
        String sql = ModelTransList.replace(sb.toString(),  placeholders);
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("param" , param);
        Object result = query.getSingleResult();
        return result != null ? result.toString() : null;
    }
    
}




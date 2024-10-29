//ecd:947615502H20241029022001_V1.0
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
    

    public Page<TestDTO> test2(String id ,Pageable pageable) {
        String[] fieldNames = {"name"};
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT series_Name ");
        sb.append("FROM series_info lc ");
        sb.append("where lc.id  = :id ");
        sb.append("   ");
        Query query = entityManager.createNativeQuery(sb.toString());
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




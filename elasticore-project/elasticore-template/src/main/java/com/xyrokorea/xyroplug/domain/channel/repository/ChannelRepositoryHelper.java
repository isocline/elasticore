//ecd:1615555385H20250403200008_V1.0
package com.xyrokorea.xyroplug.domain.channel.repository;

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

import com.xyrokorea.xyroplug.domain.channel.entity.*;
import com.xyrokorea.xyroplug.domain.channel.dto.*;



@Getter
@Service
@AllArgsConstructor
public class ChannelRepositoryHelper {

    private final EntityManager entityManager;

    private final MessageRepository message;
    



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




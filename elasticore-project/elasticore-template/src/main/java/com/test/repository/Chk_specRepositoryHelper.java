//ecd:-1699972391H20250312162715_V1.0
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
public class Chk_specRepositoryHelper {

    private final EntityManager entityManager;

    private final InsureInfoRepository insureInfo;
    

    public List<CustNoticesDTO> getNotices(CrmNotiInfoSrchDTO input) {
        String[] fieldNames = {"notiSeq" ,"notiCpgSeq" ,"insDtm" ,"notiTy" ,"msg" ,"title" ,"notiNm" ,"custNm"};
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  A.NOTI_SEQ, ");
        sb.append("  A.NOTI_CPG_SEQ, ");
        sb.append("  DATE_FORMAT(A.INS_DTM, '%Y-%m-%d %H:%i:%s') AS INS_DTM, ");
        sb.append("  A.NOTI_TY, ");
        sb.append("  A.MSG, ");
        sb.append("  C.TITLE, ");
        sb.append("  (CASE A.NOTI_TY ");
        sb.append("    WHEN 'CALL' THEN 'TTS' ");
        sb.append("    WHEN 'KAKAO' THEN '카카오톡' ");
        sb.append("    ELSE 'SMS' ");
        sb.append("  END) AS NOTI_NM, ");
        sb.append("  (CASE ");
        sb.append("    WHEN JSON_VALID(A.PARAM_INFO) = 1 THEN JSON_UNQUOTE(JSON_EXTRACT(A.PARAM_INFO, '$.CUST_NM')) ");
        sb.append("    ELSE NULL ");
        sb.append("  END) AS CUST_NM ");
        sb.append("FROM ");
        sb.append("  T_CUST_NOTICES A ");
        sb.append("  LEFT OUTER JOIN T_NOTI_LIST B ON A.NOTI_CPG_SEQ = B.NOTI_CPG_SEQ ");
        sb.append("  LEFT OUTER JOIN T_NOTIFY_TMPL C ON B.TMPL_SEQ = C.TMPL_SEQ ");
        sb.append("WHERE ");
        sb.append("  1 = 1 ");
        if(input.getFromDate() != null && !input.getFromDate().isEmpty())
          sb.append("AND DATE_FORMAT(A.INS_DTM, '%Y%m%d') BETWEEN :fromDate AND :toDate ");
        if(input.getNotiTy() != null && !input.getNotiTy().isEmpty())
          sb.append("AND A.NOTI_TY LIKE CONCAT('%',:notiTy, '%') ");
        if(input.getCustNm() != null && !input.getCustNm().isEmpty())
          sb.append("AND JSON_VALID(A.PARAM_INFO) = 1 AND JSON_UNQUOTE(JSON_EXTRACT(A.PARAM_INFO, :custNm )) LIKE CONCAT('%',:custNm, '%') ");
        if(input.getContactName() != null && !input.getContactName().isEmpty())
          sb.append("AND JSON_VALID(A.PARAM_INFO) = 1 AND JSON_UNQUOTE(JSON_EXTRACT(A.PARAM_INFO, :contactName )) LIKE CONCAT('%', :contactName, '%') ");
        if(input.getContactTel() != null && !input.getContactTel().isEmpty())
          sb.append("AND JSON_VALID(A.PARAM_INFO) = 1 AND JSON_UNQUOTE(JSON_EXTRACT(A.PARAM_INFO, :contactTel)) LIKE CONCAT('%', :contactTel, '%') ");
        java.util.Map<String, Object> placeholders = new java.util.HashMap<>();
        String sql = ModelTransList.replace(sb.toString(),  placeholders);
        Query query = createQuery(sql, true, CustNoticesDTO.class, null);
        if(input.getFromDate() != null && !input.getFromDate().isEmpty()) {
          query.setParameter("fromDate" , input.getFromDate());
          query.setParameter("toDate" , input.getToDate());
        }
        if(input.getNotiTy() != null && !input.getNotiTy().isEmpty()) {
          query.setParameter("notiTy" , input.getNotiTy());
        }
        if(input.getCustNm() != null && !input.getCustNm().isEmpty()) {
          query.setParameter("custNm" , input.getCustNm());
          query.setParameter("custNm" , input.getCustNm());
        }
        if(input.getContactName() != null && !input.getContactName().isEmpty()) {
          query.setParameter("contactName" , input.getContactName());
          query.setParameter("contactName" , input.getContactName());
        }
        if(input.getContactTel() != null && !input.getContactTel().isEmpty()) {
          query.setParameter("contactTel" , input.getContactTel());
          query.setParameter("contactTel" , input.getContactTel());
        }
        return new ModelTransList<CustNoticesDTO>(query.getResultList(), CustNoticesDTO.class, fieldNames);
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




//ecd:-96242715H20240911095941_V1.0
package com.mobillug.linkone.mongo.dto;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import com.mobillug.linkone.mongo.entity.*;
import com.mobillug.linkone.mongo.dto.*;



import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class MongoMapper {

    private static TransformPermissionChecker permissionChecker;

    public static void setTransformPermissionChecker(TransformPermissionChecker checker) {
        permissionChecker = checker;
    }


    protected static void checkPermission(Object from, Object to) {
        if(permissionChecker !=null) {
            if( !permissionChecker.hasPermission(from, to)) {
                throw new PermissionDeniedDataAccessException(from.getClass().getName()+ " access error" ,new RuntimeException());
            }
        }
    }

    
    public static Specification<PremiumCalcResult> toSpec(PremiumCalcResultSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<PremiumCalcResult> toSpec(PremiumCalcResultSrchDTO searchDTO, Specification<PremiumCalcResult> sp){
        String processId = searchDTO.getProcessId();
        if(hasValue(processId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("processId"),processId));
        }
        String prodCd = searchDTO.getProdCd();
        if(hasValue(prodCd)){
            sp = sp.and((r,q,c) -> c.equal(r.get("prodCd"),prodCd));
        }
        return sp;
    }
    


    private static boolean hasValue(String val) {
        if(val != null && !val.isEmpty())
            return true;

        return false;
    }

    private static boolean hasValue(java.time.LocalDateTime dateTime) {
        if(dateTime !=null)
            return true;

        return false;
    }

    private static boolean hasValue(List val) {
            if(val != null && val.size()>0)
                return true;

            return false;
    }

    private static boolean hasValue(Object val) {
        if(val != null )
            return true;

        return false;
    }
}

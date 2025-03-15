//ecd:614197490H20250313130133_V1.0
package com.test.dto;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import com.test.entity.*;
import com.test.dto.*;
import com.test.enums.*;

import com.test.enums.*;
import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class Chk_specMapper {

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

    
    public static void mapping(InsureInfo from, InsureInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getId2(), to::setId2, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getCustomerType(), to::setCustomerType, isSkipNull);
    }
    
    
    public static void mapping(InsureInfo from, InsureInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static InsureInfoDTO toDTO(InsureInfo from){
        if(from==null) return null;
        InsureInfoDTO to = new InsureInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<InsureInfoDTO> toInsureInfoDTOList(List<InsureInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(Chk_specMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<InsureInfoDTO> toInsureInfoDTOList(List<InsureInfo> fromList, BiFunction<InsureInfo, InsureInfoDTO, InsureInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                InsureInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(InsureInfoDTO from, InsureInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getId2(), to::setId2, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getCustomerType(), to::setCustomerType, isSkipNull);
    }
    
    
    public static void mapping(InsureInfoDTO from, InsureInfo to){
        mapping(from,to,false);
    }
    
    
    public static InsureInfo toEntity(InsureInfoDTO from){
        if(from==null) return null;
        InsureInfo to = new InsureInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<InsureInfo> toInsureInfoList(List<InsureInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(Chk_specMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<InsureInfo> toInsureInfoList(List<InsureInfoDTO> fromList, BiFunction<InsureInfoDTO, InsureInfo, InsureInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                InsureInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<InsureInfo> toSpec(InsureInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<InsureInfo> toSpec(InsureInfoSrchDTO searchDTO, Specification<InsureInfo> sp){
        String insureCompanyId = searchDTO.getInsureCompanyId();
        if(hasValue(insureCompanyId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("insureCompany").get("id"),insureCompanyId));
        }
        List<CustomerType> customerType = searchDTO.getCustomerType();
        if(hasValue(customerType)){
            sp = sp.and((root, query, criteriaBuilder) -> {
                Join<InsureInfo, CustomerType> join = root.join("customerType");
                return join.in(customerType);
            });
        }
        return sp;
    }
    

    protected static Specification setSpec(Specification sp, String fieldName, String value) {
        if(hasValue(value)){
            if(value.startsWith("%") || value.endsWith("%"))
                return sp.and((r,q,c) -> c.like(r.get(fieldName),value));
            else
                return sp.and((r,q,c) -> c.equal(r.get(fieldName),value));
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


    private static boolean isSkip(String fromClassName, String refClassName) {
        return false;
    }

    public static void setVal(String value, Consumer<String> setter, boolean isSkipNull) {
        if ((value != null && !value.isEmpty())  || !isSkipNull) {
            Optional.ofNullable(value).ifPresent(setter);
        }
    }

    public static <T> void setVal(T value, Consumer<T> setter, boolean isSkipNull) {
        if (value != null || !isSkipNull) {
            Optional.ofNullable(value).ifPresent(setter);
        }
    }


    public static <T, R> void setVal(T value, Consumer<R> setter, boolean isSkipNull, Function<T, R> mapper) {


        if (value != null || !isSkipNull) {
            Optional.ofNullable(value).map(mapper).ifPresent(setter);
        }
    }
}

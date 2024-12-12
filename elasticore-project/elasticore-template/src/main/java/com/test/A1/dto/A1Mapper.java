//ecd:-853179532H20241212190436_V1.0
package com.test.A1.dto;

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
import com.test.A1.entity.*;
import com.test.A1.dto.*;



import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class A1Mapper {

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

    
    public static void mapping(Company from, CompanyDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        if(isSkip("Company","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
    }
    
    
    public static void mapping(Company from, CompanyDTO to){
        mapping(from,to,false);
    }
    
    
    public static CompanyDTO toDTO(Company from){
        if(from==null) return null;
        CompanyDTO to = new CompanyDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CompanyDTO> toCompanyDTOList(List<Company> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A1Mapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CompanyDTO> toCompanyDTOList(List<Company> fromList, BiFunction<Company, CompanyDTO, CompanyDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CompanyDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CompanyDTO from, Company to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
    }
    
    
    public static void mapping(CompanyDTO from, Company to){
        mapping(from,to,false);
    }
    
    
    public static Company toEntity(CompanyDTO from){
        if(from==null) return null;
        Company to = new Company();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Company> toCompanyList(List<CompanyDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A1Mapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Company> toCompanyList(List<CompanyDTO> fromList, BiFunction<CompanyDTO, Company, Company> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Company to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<Company> toSpec(CompanySrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Company> toSpec(CompanySrchDTO searchDTO, Specification<Company> sp){
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
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

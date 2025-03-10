//ecd:1724754920H20250310231154_V1.0
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



import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class JPAMapper {

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

    
    public static void mapping(Address from, AddressDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getPostNo(), to::setPostNo, isSkipNull);
        setVal(from.getPostNo2(), to::setPostNo2, isSkipNull);
        setVal(from.getBaseAddr(), to::setBaseAddr, isSkipNull);
        setVal(from.getDetailAddr(), to::setDetailAddr, isSkipNull);
    }
    
    
    public static void mapping(Address from, AddressDTO to){
        mapping(from,to,false);
    }
    
    
    public static AddressDTO toDTO(Address from){
        if(from==null) return null;
        AddressDTO to = new AddressDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static void mapping(Employee from, EmployeeSrchResultDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getEmpNo(), to::setEmpNo, isSkipNull);
    }
    
    
    public static void mapping(Employee from, EmployeeSrchResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static EmployeeSrchResultDTO toEmployeeSrchResultDTO(Employee from){
        if(from==null) return null;
        EmployeeSrchResultDTO to = new EmployeeSrchResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<EmployeeSrchResultDTO> toEmployeeSrchResultDTOList(List<Employee> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(JPAMapper::toEmployeeSrchResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<EmployeeSrchResultDTO> toEmployeeSrchResultDTOList(List<Employee> fromList, BiFunction<Employee, EmployeeSrchResultDTO, EmployeeSrchResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                EmployeeSrchResultDTO to = toEmployeeSrchResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(AddressDTO from, Address to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getPostNo(), to::setPostNo, isSkipNull);
        setVal(from.getPostNo2(), to::setPostNo2, isSkipNull);
        setVal(from.getBaseAddr(), to::setBaseAddr, isSkipNull);
        setVal(from.getDetailAddr(), to::setDetailAddr, isSkipNull);
    }
    
    
    public static void mapping(AddressDTO from, Address to){
        mapping(from,to,false);
    }
    
    
    public static Address toEntity(AddressDTO from){
        if(from==null) return null;
        Address to = new Address();
        mapping(from, to);
        return to;
    }
    
    
    public static Specification<Address> toSpec(AddressSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Address> toSpec(AddressSrchDTO searchDTO, Specification<Address> sp){
        sp=setSpec(sp, "postNo", searchDTO.getPostNo());
        sp=setSpec(sp, "postNo2", searchDTO.getPostNo2());
        sp=setSpec(sp, "baseAddr", searchDTO.getBaseAddr());
        sp=setSpec(sp, "detailAddr", searchDTO.getDetailAddr());
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

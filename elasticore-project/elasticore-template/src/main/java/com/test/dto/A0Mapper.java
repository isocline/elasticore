//ecd:1450835019H20250204014854_V1.0
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


import com.test.entity.*;
import com.test.dto.*;
import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class A0Mapper {

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
        setVal(from.getCid(), to::setCid, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
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
        return fromList.stream().map(A0Mapper::toDTO).collect(Collectors.toList());
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
    
    
    public static void mapping(Employee from, EmployeeDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCompany(), to::setCompany, isSkipNull, A0Mapper::toDTO);
        if(hasValue(from.getCompany()))
            to.setCompanyCid(from.getCompany().getCid());
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(Employee from, EmployeeDTO to){
        mapping(from,to,false);
    }
    
    
    public static EmployeeDTO toDTO(Employee from){
        if(from==null) return null;
        EmployeeDTO to = new EmployeeDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<EmployeeDTO> toEmployeeDTOList(List<Employee> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A0Mapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<EmployeeDTO> toEmployeeDTOList(List<Employee> fromList, BiFunction<Employee, EmployeeDTO, EmployeeDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                EmployeeDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CompanyDTO from, Company to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCid(), to::setCid, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
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
        return fromList.stream().map(A0Mapper::toEntity).collect(Collectors.toList());
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
    
    
    public static void mapping(EmployeeDTO from, Employee to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        
        
        if(hasValue(from.getCompanyCid())){
            Company t = new Company();
            t.setCid(from.getCompanyCid());
            to.setCompany(t);
        }
    }
    
    
    public static void mapping(EmployeeDTO from, Employee to){
        mapping(from,to,false);
    }
    
    
    public static Employee toEntity(EmployeeDTO from){
        if(from==null) return null;
        Employee to = new Employee();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Employee> toEmployeeList(List<EmployeeDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A0Mapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Employee> toEmployeeList(List<EmployeeDTO> fromList, BiFunction<EmployeeDTO, Employee, Employee> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Employee to = toEntity(from);
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
        sp=setSpec(sp, "cid", searchDTO.getCid());
        sp=setSpec(sp, "name", searchDTO.getName());
        return sp;
    }
    
    
    public static Specification<Employee> toSpec(EmployeeSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Employee> toSpec(EmployeeSrchDTO searchDTO, Specification<Employee> sp){
        String companyCid = searchDTO.getCompanyCid();
        if(hasValue(companyCid)){
            sp = sp.and((r,q,c) -> c.equal(r.get("company").get("cid"),companyCid));
        }
        sp=setSpec(sp, "id", searchDTO.getId());
        String name = searchDTO.getName();
        if(hasValue(name)){
            sp = sp.and((r,q,c) -> c.like(r.get("name"),"%" +name+ "%"));
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

//ecd:-1001866366H20240925001546_V1.0
package com.xsolcorpkorea.elasticore.test.dto.dto;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import com.xsolcorpkorea.elasticore.test.dto.entity.*;
import com.xsolcorpkorea.elasticore.test.dto.dto.*;



import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class DtoMapper {

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

    
    public static void mapping(Customer from, CustomerDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
    }
    
    
    public static void mapping(Customer from, CustomerDTO to){
        mapping(from,to,false);
    }
    
    
    public static CustomerDTO toDTO(Customer from){
        if(from==null) return null;
        CustomerDTO to = new CustomerDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CustomerDTO> toCustomerDTOList(List<Customer> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(DtoMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CustomerDTO> toCustomerDTOList(List<Customer> fromList, BiFunction<Customer, CustomerDTO, CustomerDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CustomerDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(Company from, CompanyDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
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
        return fromList.stream().map(DtoMapper::toDTO).collect(Collectors.toList());
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
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
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
        return fromList.stream().map(DtoMapper::toEntity).collect(Collectors.toList());
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
    
    
    public static void mapping(CustomerDTO from, Customer to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
    }
    
    
    public static void mapping(CustomerDTO from, Customer to){
        mapping(from,to,false);
    }
    
    
    public static Customer toEntity(CustomerDTO from){
        if(from==null) return null;
        Customer to = new Customer();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Customer> toCustomerList(List<CustomerDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(DtoMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Customer> toCustomerList(List<CustomerDTO> fromList, BiFunction<CustomerDTO, Customer, Customer> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Customer to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<Customer> toSpec(CustomerSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Customer> toSpec(CustomerSrchDTO searchDTO, Specification<Customer> sp){
        String name = searchDTO.getName();
        if(hasValue(name)){
            sp = sp.and((r,q,c) -> c.equal(r.get("name"),name));
        }
        return sp;
    }
    
    
    public static Specification<Company> toSpec(CompanySrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Company> toSpec(CompanySrchDTO searchDTO, Specification<Company> sp){
        String name = searchDTO.getName();
        if(hasValue(name)){
            sp = sp.and((r,q,c) -> c.equal(r.get("name"),name));
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

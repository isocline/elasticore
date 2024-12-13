//ecd:-1444952668H20241213011350_V1.0
package com.test.A2.dto;

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
import com.test.A2.entity.*;
import com.test.A2.dto.*;



import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class A2Mapper {

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

    
    public static void mapping(Person from, PersonDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getAge(), to::setAge, isSkipNull);
        if(isSkip("Person","AuditEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
    }
    
    
    public static void mapping(Person from, PersonDTO to){
        mapping(from,to,false);
    }
    
    
    public static PersonDTO toDTO(Person from){
        if(from==null) return null;
        PersonDTO to = new PersonDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<PersonDTO> toPersonDTOList(List<Person> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A2Mapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<PersonDTO> toPersonDTOList(List<Person> fromList, BiFunction<Person, PersonDTO, PersonDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PersonDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PersonDTO from, Person to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getAge(), to::setAge, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
    }
    
    
    public static void mapping(PersonDTO from, Person to){
        mapping(from,to,false);
    }
    
    
    public static Person toEntity(PersonDTO from){
        if(from==null) return null;
        Person to = new Person();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Person> toPersonList(List<PersonDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A2Mapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Person> toPersonList(List<PersonDTO> fromList, BiFunction<PersonDTO, Person, Person> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Person to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<Person> toSpec(PersonSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Person> toSpec(PersonSrchDTO searchDTO, Specification<Person> sp){
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
        Integer age = searchDTO.getAge();
        if(hasValue(age)){
            sp = sp.and((r,q,c) -> c.equal(r.get("age"),age));
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

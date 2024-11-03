//ecd:631194838H20241031175957_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import com.xsolcorpkorea.elasticore.test.rollup.entity.*;
import com.xsolcorpkorea.elasticore.test.rollup.dto.*;



import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class SearchResultMapper {

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
        if(!isSkipNull || hasValue(from.getPersonGrp()))
            to.setPersonGrp(toDTO(from.getPersonGrp()));
        if(hasValue(from.getPersonGrp()))
            to.setPersonGrpId(from.getPersonGrp().getId());
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
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
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
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
    
    
    public static void mapping(PersonGroup from, PersonGroupResultDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
    }
    
    
    public static void mapping(PersonGroup from, PersonGroupResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static PersonGroupResultDTO toPersonGroupResultDTO(PersonGroup from){
        if(from==null) return null;
        PersonGroupResultDTO to = new PersonGroupResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<PersonGroupResultDTO> toPersonGroupResultDTOList(List<PersonGroup> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toPersonGroupResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<PersonGroupResultDTO> toPersonGroupResultDTOList(List<PersonGroup> fromList, BiFunction<PersonGroup, PersonGroupResultDTO, PersonGroupResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PersonGroupResultDTO to = toPersonGroupResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PersonGroup from, PersonGroupDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
    }
    
    
    public static void mapping(PersonGroup from, PersonGroupDTO to){
        mapping(from,to,false);
    }
    
    
    public static PersonGroupDTO toDTO(PersonGroup from){
        if(from==null) return null;
        PersonGroupDTO to = new PersonGroupDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<PersonGroupDTO> toPersonGroupDTOList(List<PersonGroup> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<PersonGroupDTO> toPersonGroupDTOList(List<PersonGroup> fromList, BiFunction<PersonGroup, PersonGroupDTO, PersonGroupDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PersonGroupDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PersonGroupDTO from, PersonGroup to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
    }
    
    
    public static void mapping(PersonGroupDTO from, PersonGroup to){
        mapping(from,to,false);
    }
    
    
    public static PersonGroup toEntity(PersonGroupDTO from){
        if(from==null) return null;
        PersonGroup to = new PersonGroup();
        mapping(from, to);
        return to;
    }
    
    
    public static List<PersonGroup> toPersonGroupList(List<PersonGroupDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<PersonGroup> toPersonGroupList(List<PersonGroupDTO> fromList, BiFunction<PersonGroupDTO, PersonGroup, PersonGroup> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                PersonGroup to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(PersonDTO from, Person to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
        
        
        if(hasValue(from.getPersonGrpId())){
            PersonGroup t = new PersonGroup();
            t.setId(from.getPersonGrpId());
            to.setPersonGrp(t);
        }
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
        return fromList.stream().map(SearchResultMapper::toEntity).collect(Collectors.toList());
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
    
    
    public static Specification<Person> toSpec(PersonSrhDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Person> toSpec(PersonSrhDTO searchDTO, Specification<Person> sp){
        String personGrpId = searchDTO.getPersonGrpId();
        if(hasValue(personGrpId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("personGrp").get("id"),personGrpId));
        }
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
        PersonGroup personGrp = searchDTO.getPersonGrp();
        if(hasValue(personGrp)){
            sp = sp.and((r,q,c) -> c.equal(r.get("personGrp"),personGrp));
        }
        return sp;
    }
    
    
    public static Specification<Person> toSpec(PersonSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Person> toSpec(PersonSrchDTO searchDTO, Specification<Person> sp){
        String personGrpId = searchDTO.getPersonGrpId();
        if(hasValue(personGrpId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("personGrp").get("id"),personGrpId));
        }
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
        return sp;
    }
    
    
    public static Specification<PersonGroup> toSpec(PersonGroupSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<PersonGroup> toSpec(PersonGroupSrchDTO searchDTO, Specification<PersonGroup> sp){
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
}

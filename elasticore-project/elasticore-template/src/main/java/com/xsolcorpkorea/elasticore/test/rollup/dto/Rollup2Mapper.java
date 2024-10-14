//ecd:1973876158H20241014192242_V1.0
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


public class Rollup2Mapper {

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

    
    public static void mapping(ExtPerson from, ExtPersonDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
    }
    
    
    public static void mapping(ExtPerson from, ExtPersonDTO to){
        mapping(from,to,false);
    }
    
    
    public static ExtPersonDTO toDTO(ExtPerson from){
        if(from==null) return null;
        ExtPersonDTO to = new ExtPersonDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ExtPersonDTO> toExtPersonDTOList(List<ExtPerson> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(Rollup2Mapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ExtPersonDTO> toExtPersonDTOList(List<ExtPerson> fromList, BiFunction<ExtPerson, ExtPersonDTO, ExtPersonDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ExtPersonDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ResidualMobillug from, ResidualMobillugDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getPeriod36()))
            to.setPeriod36(from.getPeriod36());
        if(!isSkipNull || hasValue(from.getPeriod48()))
            to.setPeriod48(from.getPeriod48());
        if(!isSkipNull || hasValue(from.getPeriod60()))
            to.setPeriod60(from.getPeriod60());
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getType()))
            to.setType(from.getType());
        if(!isSkipNull || hasValue(from.getDivision()))
            to.setDivision(from.getDivision());
        if(!isSkipNull || hasValue(from.getCreateDate()))
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || hasValue(from.getCreatedBy()))
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || hasValue(from.getLastModifiedBy()))
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || hasValue(from.getLastModifiedDate()))
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(ResidualMobillug from, ResidualMobillugDTO to){
        mapping(from,to,false);
    }
    
    
    public static ResidualMobillugDTO toDTO(ResidualMobillug from){
        if(from==null) return null;
        ResidualMobillugDTO to = new ResidualMobillugDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ResidualMobillugDTO> toResidualMobillugDTOList(List<ResidualMobillug> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(Rollup2Mapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ResidualMobillugDTO> toResidualMobillugDTOList(List<ResidualMobillug> fromList, BiFunction<ResidualMobillug, ResidualMobillugDTO, ResidualMobillugDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ResidualMobillugDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ExtPersonDTO from, ExtPerson to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getName()))
            to.setName(from.getName());
    }
    
    
    public static void mapping(ExtPersonDTO from, ExtPerson to){
        mapping(from,to,false);
    }
    
    
    public static ExtPerson toEntity(ExtPersonDTO from){
        if(from==null) return null;
        ExtPerson to = new ExtPerson();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ExtPerson> toExtPersonList(List<ExtPersonDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(Rollup2Mapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<ExtPerson> toExtPersonList(List<ExtPersonDTO> fromList, BiFunction<ExtPersonDTO, ExtPerson, ExtPerson> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ExtPerson to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ResidualMobillugDTO from, ResidualMobillug to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(!isSkipNull || hasValue(from.getPeriod36()))
            to.setPeriod36(from.getPeriod36());
        if(!isSkipNull || hasValue(from.getPeriod48()))
            to.setPeriod48(from.getPeriod48());
        if(!isSkipNull || hasValue(from.getPeriod60()))
            to.setPeriod60(from.getPeriod60());
        if(!isSkipNull || hasValue(from.getId()))
            to.setId(from.getId());
        if(!isSkipNull || hasValue(from.getType()))
            to.setType(from.getType());
        if(!isSkipNull || hasValue(from.getDivision()))
            to.setDivision(from.getDivision());
        if(!isSkipNull || hasValue(from.getCreateDate()))
            to.setCreateDate(from.getCreateDate());
        if(!isSkipNull || hasValue(from.getCreatedBy()))
            to.setCreatedBy(from.getCreatedBy());
        if(!isSkipNull || hasValue(from.getLastModifiedBy()))
            to.setLastModifiedBy(from.getLastModifiedBy());
        if(!isSkipNull || hasValue(from.getLastModifiedDate()))
            to.setLastModifiedDate(from.getLastModifiedDate());
    }
    
    
    public static void mapping(ResidualMobillugDTO from, ResidualMobillug to){
        mapping(from,to,false);
    }
    
    
    public static ResidualMobillug toEntity(ResidualMobillugDTO from){
        if(from==null) return null;
        ResidualMobillug to = new ResidualMobillug();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ResidualMobillug> toResidualMobillugList(List<ResidualMobillugDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(Rollup2Mapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<ResidualMobillug> toResidualMobillugList(List<ResidualMobillugDTO> fromList, BiFunction<ResidualMobillugDTO, ResidualMobillug, ResidualMobillug> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ResidualMobillug to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<ExtPerson> toSpec(ExtPersonSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<ExtPerson> toSpec(ExtPersonSrchDTO searchDTO, Specification<ExtPerson> sp){
        String id = searchDTO.getId();
        if(hasValue(id)){
            if(id.startsWith("%") || id.endsWith("%"))
              sp = sp.and((r,q,c) -> c.like(r.get("id"),id));
            else
              sp = sp.and((r,q,c) -> c.equal(r.get("id"),id));
        }
        String name = searchDTO.getName();
        if(hasValue(name)){
            if(name.startsWith("%") || name.endsWith("%"))
              sp = sp.and((r,q,c) -> c.like(r.get("name"),name));
            else
              sp = sp.and((r,q,c) -> c.equal(r.get("name"),name));
        }
        return sp;
    }
    
    
    public static Specification<BaseResidualInfo> toSpec(ResidualMobillugSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<BaseResidualInfo> toSpec(ResidualMobillugSrchDTO searchDTO, Specification<BaseResidualInfo> sp){
        Float period36 = searchDTO.getPeriod36();
        if(hasValue(period36)){
            sp = sp.and((r,q,c) -> c.equal(r.get("period36"),period36));
        }
        Float period48 = searchDTO.getPeriod48();
        if(hasValue(period48)){
            sp = sp.and((r,q,c) -> c.equal(r.get("period48"),period48));
        }
        Float period60 = searchDTO.getPeriod60();
        if(hasValue(period60)){
            sp = sp.and((r,q,c) -> c.equal(r.get("period60"),period60));
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

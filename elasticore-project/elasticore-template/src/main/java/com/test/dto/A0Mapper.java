//ecd:-831377405H20241219162527_V1.0
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

    
    public static void mapping(TestEmb from, TestEmbDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCondition(), to::setCondition, isSkipNull);
        setVal(from.getValue(), to::setValue, isSkipNull);
        setVal(from.getContent(), to::setContent, isSkipNull);
    }
    
    
    public static void mapping(TestEmb from, TestEmbDTO to){
        mapping(from,to,false);
    }
    
    
    public static TestEmbDTO toDTO(TestEmb from){
        if(from==null) return null;
        TestEmbDTO to = new TestEmbDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static void mapping(BaseEntity from, BaseEntityDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getEmb(), to::setEmb, isSkipNull, A0Mapper::toDTO);
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(BaseEntity from, BaseEntityDTO to){
        mapping(from,to,false);
    }
    
    
    public static BaseEntityDTO toDTO(BaseEntity from){
        if(from==null) return null;
        BaseEntityDTO to = new BaseEntityDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BaseEntityDTO> toBaseEntityDTOList(List<BaseEntity> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A0Mapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<BaseEntityDTO> toBaseEntityDTOList(List<BaseEntity> fromList, BiFunction<BaseEntity, BaseEntityDTO, BaseEntityDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BaseEntityDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TestEmbDTO from, TestEmb to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCondition(), to::setCondition, isSkipNull);
        setVal(from.getValue(), to::setValue, isSkipNull);
        setVal(from.getContent(), to::setContent, isSkipNull);
    }
    
    
    public static void mapping(TestEmbDTO from, TestEmb to){
        mapping(from,to,false);
    }
    
    
    public static TestEmb toEntity(TestEmbDTO from){
        if(from==null) return null;
        TestEmb to = new TestEmb();
        mapping(from, to);
        return to;
    }
    
    
    public static void mapping(BaseEntityDTO from, BaseEntity to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(BaseEntityDTO from, BaseEntity to){
        mapping(from,to,false);
    }
    
    
    public static BaseEntity toEntity(BaseEntityDTO from){
        if(from==null) return null;
        BaseEntity to = new BaseEntity();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BaseEntity> toBaseEntityList(List<BaseEntityDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(A0Mapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<BaseEntity> toBaseEntityList(List<BaseEntityDTO> fromList, BiFunction<BaseEntityDTO, BaseEntity, BaseEntity> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BaseEntity to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<BaseEntity> toSpec(BaseEntitySrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<BaseEntity> toSpec(BaseEntitySrchDTO searchDTO, Specification<BaseEntity> sp){
        String content = searchDTO.getContent();
        if(hasValue(content)){
            sp = sp.and((r,q,c) -> c.equal(r.get("emb").get("content"),content));
        }
        return sp;
    }
    
    
    public static Specification<TestEmb> toSpec(TestEmbSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<TestEmb> toSpec(TestEmbSrchDTO searchDTO, Specification<TestEmb> sp){
        sp=setSpec(sp, "condition", searchDTO.getCondition());
        Double value = searchDTO.getValue();
        if(hasValue(value)){
            sp = sp.and((r,q,c) -> c.equal(r.get("value"),value));
        }
        sp=setSpec(sp, "content", searchDTO.getContent());
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

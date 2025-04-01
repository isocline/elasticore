//ecd:-304197595H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.unitprice.dto;

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
import com.xyrokorea.xyroplug.domain.unitprice.entity.*;
import com.xyrokorea.xyroplug.domain.unitprice.dto.*;


import com.xyrokorea.xyroplug.domain.channel.enums.*;
import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class UnitpriceMapper {

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

    
    public static void mapping(UnitPrice from, UnitPriceDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getMsgType(), to::setMsgType, isSkipNull);
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getPrice(), to::setPrice, isSkipNull);
        setVal(from.getDescription(), to::setDescription, isSkipNull);
        setVal(from.getLengthMin(), to::setLengthMin, isSkipNull);
        setVal(from.getLengthMax(), to::setLengthMax, isSkipNull);
    }
    
    
    public static void mapping(UnitPrice from, UnitPriceDTO to){
        mapping(from,to,false);
    }
    
    
    public static UnitPriceDTO toDTO(UnitPrice from){
        if(from==null) return null;
        UnitPriceDTO to = new UnitPriceDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<UnitPriceDTO> toUnitPriceDTOList(List<UnitPrice> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(UnitpriceMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<UnitPriceDTO> toUnitPriceDTOList(List<UnitPrice> fromList, BiFunction<UnitPrice, UnitPriceDTO, UnitPriceDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                UnitPriceDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TestInUnitPrice from, TestInUnitPriceDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getMsgType(), to::setMsgType, isSkipNull);
        setVal(from.getId(), to::setId, isSkipNull);
    }
    
    
    public static void mapping(TestInUnitPrice from, TestInUnitPriceDTO to){
        mapping(from,to,false);
    }
    
    
    public static TestInUnitPriceDTO toDTO(TestInUnitPrice from){
        if(from==null) return null;
        TestInUnitPriceDTO to = new TestInUnitPriceDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TestInUnitPriceDTO> toTestInUnitPriceDTOList(List<TestInUnitPrice> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(UnitpriceMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<TestInUnitPriceDTO> toTestInUnitPriceDTOList(List<TestInUnitPrice> fromList, BiFunction<TestInUnitPrice, TestInUnitPriceDTO, TestInUnitPriceDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TestInUnitPriceDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(UnitPriceDTO from, UnitPrice to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getMsgType(), to::setMsgType, isSkipNull);
        setVal(from.getPrice(), to::setPrice, isSkipNull);
        setVal(from.getDescription(), to::setDescription, isSkipNull);
        setVal(from.getLengthMin(), to::setLengthMin, isSkipNull);
        setVal(from.getLengthMax(), to::setLengthMax, isSkipNull);
    }
    
    
    public static void mapping(UnitPriceDTO from, UnitPrice to){
        mapping(from,to,false);
    }
    
    
    public static UnitPrice toEntity(UnitPriceDTO from){
        if(from==null) return null;
        UnitPrice to = new UnitPrice();
        mapping(from, to);
        return to;
    }
    
    
    public static List<UnitPrice> toUnitPriceList(List<UnitPriceDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(UnitpriceMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<UnitPrice> toUnitPriceList(List<UnitPriceDTO> fromList, BiFunction<UnitPriceDTO, UnitPrice, UnitPrice> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                UnitPrice to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TestInUnitPriceDTO from, TestInUnitPrice to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getMsgType(), to::setMsgType, isSkipNull);
    }
    
    
    public static void mapping(TestInUnitPriceDTO from, TestInUnitPrice to){
        mapping(from,to,false);
    }
    
    
    public static TestInUnitPrice toEntity(TestInUnitPriceDTO from){
        if(from==null) return null;
        TestInUnitPrice to = new TestInUnitPrice();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TestInUnitPrice> toTestInUnitPriceList(List<TestInUnitPriceDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(UnitpriceMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<TestInUnitPrice> toTestInUnitPriceList(List<TestInUnitPriceDTO> fromList, BiFunction<TestInUnitPriceDTO, TestInUnitPrice, TestInUnitPrice> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TestInUnitPrice to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<UnitPrice> toSpec(UnitPriceSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<UnitPrice> toSpec(UnitPriceSrchDTO searchDTO, Specification<UnitPrice> sp){
        MessageType msgType = searchDTO.getMsgType();
        if(hasValue(msgType)){
            sp = sp.and((r,q,c) -> c.equal(r.get("msgType"),msgType));
        }
        sp=setSpec(sp, "id", searchDTO.getId());
        Integer price = searchDTO.getPrice();
        if(hasValue(price)){
            sp = sp.and((r,q,c) -> c.equal(r.get("price"),price));
        }
        sp=setSpec(sp, "description", searchDTO.getDescription());
        Integer lengthMin = searchDTO.getLengthMin();
        if(hasValue(lengthMin)){
            sp = sp.and((r,q,c) -> c.equal(r.get("lengthMin"),lengthMin));
        }
        Integer lengthMax = searchDTO.getLengthMax();
        if(hasValue(lengthMax)){
            sp = sp.and((r,q,c) -> c.equal(r.get("lengthMax"),lengthMax));
        }
        return sp;
    }
    
    
    public static Specification<TestInUnitPrice> toSpec(TestInUnitPriceSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<TestInUnitPrice> toSpec(TestInUnitPriceSrchDTO searchDTO, Specification<TestInUnitPrice> sp){
        MessageType msgType = searchDTO.getMsgType();
        if(hasValue(msgType)){
            sp = sp.and((r,q,c) -> c.equal(r.get("msgType"),msgType));
        }
        sp=setSpec(sp, "id", searchDTO.getId());
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

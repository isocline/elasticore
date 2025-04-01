//ecd:1578820238H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.channel.dto;

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
import com.xyrokorea.xyroplug.domain.channel.entity.*;
import com.xyrokorea.xyroplug.domain.channel.dto.*;
import com.xyrokorea.xyroplug.domain.channel.enums.*;

import com.xyrokorea.xyroplug.domain.channel.enums.*;
import io.elasticore.runtime.security.TransformPermissionChecker;

/**


 */


public class ChannelMapper {

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

    
    public static void mapping(TestInChannel from, TestInChannelDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getMsgType(), to::setMsgType, isSkipNull);
        setVal(from.getId(), to::setId, isSkipNull);
    }
    
    
    public static void mapping(TestInChannel from, TestInChannelDTO to){
        mapping(from,to,false);
    }
    
    
    public static TestInChannelDTO toDTO(TestInChannel from){
        if(from==null) return null;
        TestInChannelDTO to = new TestInChannelDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TestInChannelDTO> toTestInChannelDTOList(List<TestInChannel> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(ChannelMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<TestInChannelDTO> toTestInChannelDTOList(List<TestInChannel> fromList, BiFunction<TestInChannel, TestInChannelDTO, TestInChannelDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TestInChannelDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TestInChannelDTO from, TestInChannel to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getMsgType(), to::setMsgType, isSkipNull);
    }
    
    
    public static void mapping(TestInChannelDTO from, TestInChannel to){
        mapping(from,to,false);
    }
    
    
    public static TestInChannel toEntity(TestInChannelDTO from){
        if(from==null) return null;
        TestInChannel to = new TestInChannel();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TestInChannel> toTestInChannelList(List<TestInChannelDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(ChannelMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<TestInChannel> toTestInChannelList(List<TestInChannelDTO> fromList, BiFunction<TestInChannelDTO, TestInChannel, TestInChannel> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TestInChannel to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<TestInChannel> toSpec(TestInChannelSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<TestInChannel> toSpec(TestInChannelSrchDTO searchDTO, Specification<TestInChannel> sp){
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

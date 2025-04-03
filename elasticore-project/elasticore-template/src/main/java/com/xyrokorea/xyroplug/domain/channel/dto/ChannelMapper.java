//ecd:847955478H20250403210036_V1.0
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

    
    public static void mapping(Message from, MessageDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getStatus(), to::setStatus, isSkipNull);
        setVal(from.getMsgType(), to::setMsgType, isSkipNull);
        setVal(from.getMsgId(), to::setMsgId, isSkipNull);
        setVal(from.getCcId(), to::setCcId, isSkipNull);
        setVal(from.getSender(), to::setSender, isSkipNull);
        setVal(from.getRecipient(), to::setRecipient, isSkipNull);
        setVal(from.getUuid(), to::setUuid, isSkipNull);
        setVal(from.getContent(), to::setContent, isSkipNull);
        setVal(from.getErrMsg(), to::setErrMsg, isSkipNull);
        setVal(from.getRecvDatetime(), to::setRecvDatetime, isSkipNull);
        setVal(from.getRecvEndDatetime(), to::setRecvEndDatetime, isSkipNull);
        if(isSkip("Message","AuditEntity")) return;
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        if(isSkip("Message","BaseEntity")) return;
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(Message from, MessageDTO to){
        mapping(from,to,false);
    }
    
    
    public static MessageDTO toDTO(Message from){
        if(from==null) return null;
        MessageDTO to = new MessageDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<MessageDTO> toMessageDTOList(List<Message> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(ChannelMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<MessageDTO> toMessageDTOList(List<Message> fromList, BiFunction<Message, MessageDTO, MessageDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                MessageDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(MessageDTO from, Message to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getMsgId(), to::setMsgId, isSkipNull);
        setVal(from.getCcId(), to::setCcId, isSkipNull);
        setVal(from.getSender(), to::setSender, isSkipNull);
        setVal(from.getRecipient(), to::setRecipient, isSkipNull);
        setVal(from.getUuid(), to::setUuid, isSkipNull);
        setVal(from.getContent(), to::setContent, isSkipNull);
        setVal(from.getStatus(), to::setStatus, isSkipNull);
        setVal(from.getMsgType(), to::setMsgType, isSkipNull);
        setVal(from.getErrMsg(), to::setErrMsg, isSkipNull);
        setVal(from.getRecvDatetime(), to::setRecvDatetime, isSkipNull);
        setVal(from.getRecvEndDatetime(), to::setRecvEndDatetime, isSkipNull);
        setVal(from.getLastModifiedBy(), to::setLastModifiedBy, isSkipNull);
        setVal(from.getLastModifiedDate(), to::setLastModifiedDate, isSkipNull);
        setVal(from.getCreateIP(), to::setCreateIP, isSkipNull);
        setVal(from.getLastModifiedIP(), to::setLastModifiedIP, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(MessageDTO from, Message to){
        mapping(from,to,false);
    }
    
    
    public static Message toEntity(MessageDTO from){
        if(from==null) return null;
        Message to = new Message();
        mapping(from, to);
        return to;
    }
    
    
    public static List<Message> toMessageList(List<MessageDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(ChannelMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<Message> toMessageList(List<MessageDTO> fromList, BiFunction<MessageDTO, Message, Message> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                Message to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<Message> toSpec(MessageSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<Message> toSpec(MessageSrchDTO searchDTO, Specification<Message> sp){
        MessageStatus status = searchDTO.getStatus();
        if(hasValue(status)){
            sp = sp.and((r,q,c) -> c.equal(r.get("status"),status));
        }
        MessageType msgType = searchDTO.getMsgType();
        if(hasValue(msgType)){
            sp = sp.and((r,q,c) -> c.equal(r.get("msgType"),msgType));
        }
        Long msgId = searchDTO.getMsgId();
        if(hasValue(msgId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("msgId"),msgId));
        }
        sp=setSpec(sp, "ccId", searchDTO.getCcId());
        sp=setSpec(sp, "sender", searchDTO.getSender());
        sp=setSpec(sp, "recipient", searchDTO.getRecipient());
        sp=setSpec(sp, "uuid", searchDTO.getUuid());
        sp=setSpec(sp, "content", searchDTO.getContent());
        sp=setSpec(sp, "errMsg", searchDTO.getErrMsg());
        java.time.LocalDateTime recvDatetimeFrom = searchDTO.getRecvDatetimeFrom();
        java.time.LocalDateTime recvDatetimeTo = searchDTO.getRecvDatetimeTo();
        if(hasValue(recvDatetimeFrom) && hasValue(recvDatetimeTo)){
            sp = sp.and((r,q,c) -> c.between(r.get("recvDatetime"),recvDatetimeFrom,recvDatetimeTo));
        }
        else if(hasValue(recvDatetimeFrom)){
            sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get("recvDatetime"),recvDatetimeFrom));
        }
        else if(hasValue(recvDatetimeTo)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("recvDatetime"),recvDatetimeTo));
        }
        java.time.LocalDateTime recvEndDatetimeFrom = searchDTO.getRecvEndDatetimeFrom();
        java.time.LocalDateTime recvEndDatetimeTo = searchDTO.getRecvEndDatetimeTo();
        if(hasValue(recvEndDatetimeFrom) && hasValue(recvEndDatetimeTo)){
            sp = sp.and((r,q,c) -> c.between(r.get("recvEndDatetime"),recvEndDatetimeFrom,recvEndDatetimeTo));
        }
        else if(hasValue(recvEndDatetimeFrom)){
            sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get("recvEndDatetime"),recvEndDatetimeFrom));
        }
        else if(hasValue(recvEndDatetimeTo)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("recvEndDatetime"),recvEndDatetimeTo));
        }
        sp=setSpec(sp, "lastModifiedBy", searchDTO.getLastModifiedBy());
        java.time.LocalDateTime lastModifiedDateFrom = searchDTO.getLastModifiedDateFrom();
        java.time.LocalDateTime lastModifiedDateTo = searchDTO.getLastModifiedDateTo();
        if(hasValue(lastModifiedDateFrom) && hasValue(lastModifiedDateTo)){
            sp = sp.and((r,q,c) -> c.between(r.get("lastModifiedDate"),lastModifiedDateFrom,lastModifiedDateTo));
        }
        else if(hasValue(lastModifiedDateFrom)){
            sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get("lastModifiedDate"),lastModifiedDateFrom));
        }
        else if(hasValue(lastModifiedDateTo)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("lastModifiedDate"),lastModifiedDateTo));
        }
        sp=setSpec(sp, "createIP", searchDTO.getCreateIP());
        sp=setSpec(sp, "lastModifiedIP", searchDTO.getLastModifiedIP());
        java.time.LocalDateTime createDateFrom = searchDTO.getCreateDateFrom();
        java.time.LocalDateTime createDateTo = searchDTO.getCreateDateTo();
        if(hasValue(createDateFrom) && hasValue(createDateTo)){
            sp = sp.and((r,q,c) -> c.between(r.get("createDate"),createDateFrom,createDateTo));
        }
        else if(hasValue(createDateFrom)){
            sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get("createDate"),createDateFrom));
        }
        else if(hasValue(createDateTo)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("createDate"),createDateTo));
        }
        sp=setSpec(sp, "createdBy", searchDTO.getCreatedBy());
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

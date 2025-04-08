//ecd:-546198493H20250408103900_V1.0
package com.xyrokorea.xyroplug.domain.channel.entity;

import com.xyrokorea.xyroplug.domain.channel.enums.*;

import io.elasticore.springboot3.entity.*;
import org.springframework.data.jpa.domain.Specification;


/**
 * This class serves as a domain-specific unified metadata holder,
 * containing field names and type information for entities.
 *
 * Generated and managed by ElastiCORE.
 */

public class Q {

	public static QxBaseEntity BaseEntity=new QxBaseEntity();
	public static class QxBaseEntity {
	    private final FieldInfo F_createDate=new FieldInfo("createDate",java.time.LocalDateTime.class, null);
	    public final String createDate=F_createDate.getName();
	    public final FieldInfo getCreateDate() {return F_createDate;}
	    public Specification<BaseEntity> createDate(Op op,Object value) {return F_createDate.where(op,value);}
	    private final FieldInfo F_createdBy=new FieldInfo("createdBy",String.class, null);
	    public final String createdBy=F_createdBy.getName();
	    public final FieldInfo getCreatedBy() {return F_createdBy;}
	    public Specification<BaseEntity> createdBy(Op op,Object value) {return F_createdBy.where(op,value);}
	}
	
	public static QxAuditEntity AuditEntity=new QxAuditEntity();
	public static class QxAuditEntity {
	    private final FieldInfo F_lastModifiedBy=new FieldInfo("lastModifiedBy",String.class, null);
	    public final String lastModifiedBy=F_lastModifiedBy.getName();
	    public final FieldInfo getLastModifiedBy() {return F_lastModifiedBy;}
	    public Specification<AuditEntity> lastModifiedBy(Op op,Object value) {return F_lastModifiedBy.where(op,value);}
	    private final FieldInfo F_lastModifiedDate=new FieldInfo("lastModifiedDate",java.time.LocalDateTime.class, null);
	    public final String lastModifiedDate=F_lastModifiedDate.getName();
	    public final FieldInfo getLastModifiedDate() {return F_lastModifiedDate;}
	    public Specification<AuditEntity> lastModifiedDate(Op op,Object value) {return F_lastModifiedDate.where(op,value);}
	    private final FieldInfo F_createIP=new FieldInfo("createIP",String.class, null);
	    public final String createIP=F_createIP.getName();
	    public final FieldInfo getCreateIP() {return F_createIP;}
	    public Specification<AuditEntity> createIP(Op op,Object value) {return F_createIP.where(op,value);}
	    private final FieldInfo F_lastModifiedIP=new FieldInfo("lastModifiedIP",String.class, null);
	    public final String lastModifiedIP=F_lastModifiedIP.getName();
	    public final FieldInfo getLastModifiedIP() {return F_lastModifiedIP;}
	    public Specification<AuditEntity> lastModifiedIP(Op op,Object value) {return F_lastModifiedIP.where(op,value);}
	}
	
	public static QxMessage Message=new QxMessage();
	public static class QxMessage {
	    private final FieldInfo F_msgId=new FieldInfo("msgId",Long.class, null);
	    public final String msgId=F_msgId.getName();
	    public final FieldInfo getMsgId() {return F_msgId;}
	    public Specification<Message> msgId(Op op,Object value) {return F_msgId.where(op,value);}
	    private final FieldInfo F_ccId=new FieldInfo("ccId",String.class, null);
	    public final String ccId=F_ccId.getName();
	    public final FieldInfo getCcId() {return F_ccId;}
	    public Specification<Message> ccId(Op op,Object value) {return F_ccId.where(op,value);}
	    private final FieldInfo F_sender=new FieldInfo("sender",String.class, null);
	    public final String sender=F_sender.getName();
	    public final FieldInfo getSender() {return F_sender;}
	    public Specification<Message> sender(Op op,Object value) {return F_sender.where(op,value);}
	    private final FieldInfo F_recipient=new FieldInfo("recipient",String.class, null);
	    public final String recipient=F_recipient.getName();
	    public final FieldInfo getRecipient() {return F_recipient;}
	    public Specification<Message> recipient(Op op,Object value) {return F_recipient.where(op,value);}
	    private final FieldInfo F_uuid=new FieldInfo("uuid",String.class, null);
	    public final String uuid=F_uuid.getName();
	    public final FieldInfo getUuid() {return F_uuid;}
	    public Specification<Message> uuid(Op op,Object value) {return F_uuid.where(op,value);}
	    private final FieldInfo F_content=new FieldInfo("content",String.class, null);
	    public final String content=F_content.getName();
	    public final FieldInfo getContent() {return F_content;}
	    public Specification<Message> content(Op op,Object value) {return F_content.where(op,value);}
	    private final FieldInfo F_status=new FieldInfo("status",MessageStatus.class, null);
	    public final String status=F_status.getName();
	    public final FieldInfo getStatus() {return F_status;}
	    private final FieldInfo F_msgType=new FieldInfo("msgType",MessageType.class, null);
	    public final String msgType=F_msgType.getName();
	    public final FieldInfo getMsgType() {return F_msgType;}
	    private final FieldInfo F_errMsg=new FieldInfo("errMsg",String.class, null);
	    public final String errMsg=F_errMsg.getName();
	    public final FieldInfo getErrMsg() {return F_errMsg;}
	    public Specification<Message> errMsg(Op op,Object value) {return F_errMsg.where(op,value);}
	    private final FieldInfo F_recvDatetime=new FieldInfo("recvDatetime",java.time.LocalDateTime.class, null);
	    public final String recvDatetime=F_recvDatetime.getName();
	    public final FieldInfo getRecvDatetime() {return F_recvDatetime;}
	    public Specification<Message> recvDatetime(Op op,Object value) {return F_recvDatetime.where(op,value);}
	    private final FieldInfo F_recvEndDatetime=new FieldInfo("recvEndDatetime",java.time.LocalDateTime.class, null);
	    public final String recvEndDatetime=F_recvEndDatetime.getName();
	    public final FieldInfo getRecvEndDatetime() {return F_recvEndDatetime;}
	    public Specification<Message> recvEndDatetime(Op op,Object value) {return F_recvEndDatetime.where(op,value);}
	}
	
}

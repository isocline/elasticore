//ecd:-478393113H20250403200008_V1.0
package com.xyrokorea.xyroplug.domain.channel.entity;

import com.xyrokorea.xyroplug.domain.channel.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;
import com.xyrokorea.xyroplug.domain.channel.enums.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Message extends AuditEntity implements java.io.Serializable  {

	/*
	  메세지 아이디
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("메세지 아이디")
	@Column(name = "msg_id")
	private Long msgId;
	
	
	/*
	  고객 채널 아이디
	*/
	@Comment("고객 채널 아이디")
	@Column(name = "cc_id", length = 30)
	private String ccId;
	
	
	/*
	  발신자 정보(전화번호)
	*/
	@Comment("발신자 정보(전화번호)")
	@Column(name = "sender", length = 64)
	private String sender;
	
	
	@Column(name = "recipient", nullable = false, length = 64)
	private String recipient;
	
	
	/*
	  발신 메시지 고유아이디
	*/
	@Comment("발신 메시지 고유아이디")
	@Column(name = "uuid", length = 128)
	private String uuid;
	
	
	/*
	  메시지 내용
	*/
	@Comment("메시지 내용")
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	
	/*
	  메시지 상태
	*/
	@Comment("메시지 상태")
	@Column(length = 2)
	@Convert(converter = MessageStatus.EntityConverter.class)
	private MessageStatus status;
	
	
	/*
	  최종 전송 메세지 타입
	*/
	@Comment("최종 전송 메세지 타입")
	@Column(length = 2)
	@Convert(converter = MessageType.EntityConverter.class)
	private MessageType msgType;
	
	
	/*
	  에러시 에러 메세지
	*/
	@Comment("에러시 에러 메세지")
	@Column(name = "err_msg", length = 512)
	private String errMsg;
	
	
	/*
	  수신일시
	*/
	@Comment("수신일시")
	@Column(name = "recv_datetime")
	private java.time.LocalDateTime recvDatetime;
	
	
	/*
	  수신(통화) 종료일시
	*/
	@Comment("수신(통화) 종료일시")
	@Column(name = "recv_end_datetime")
	private java.time.LocalDateTime recvEndDatetime;
	
	
}

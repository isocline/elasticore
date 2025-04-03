//ecd:-965658086H20250403200008_V1.0
package com.xyrokorea.xyroplug.domain.channel.dto;

import com.xyrokorea.xyroplug.domain.channel.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.xyrokorea.xyroplug.domain.channel.enums.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class MessageDTO  implements java.io.Serializable  {

	/*
	  메시지 상태
	*/
	@Schema(description = "메시지 상태"  , example="PD: 대기 | OK: 전송완료 | FL: 전송실패")
	private MessageStatus status;
	
	/*
	  최종 전송 메세지 타입
	*/
	@Schema(description = "최종 전송 메세지 타입"  , example="SMS: 단문문자 | LMS: 장문문자 | CALL: 전화발신 | KAKAO: 카카오톡 | OBC: 전화발신 | FAX: 팩스")
	private MessageType msgType;
	
	/*
	  메세지 아이디
	*/
	@Schema(description = "메세지 아이디"  )
	private Long msgId;
	
	/*
	  고객 채널 아이디
	*/
	@Schema(description = "고객 채널 아이디"  )
	@Size(max=30)
	private String ccId;
	
	/*
	  발신자 정보(전화번호)
	*/
	@Schema(description = "발신자 정보(전화번호)"  )
	@Size(max=64)
	private String sender;
	
	@Schema(description = "recipient" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=64)
	private String recipient;
	
	/*
	  발신 메시지 고유아이디
	*/
	@Schema(description = "발신 메시지 고유아이디"  )
	@Size(max=128)
	private String uuid;
	
	/*
	  메시지 내용
	*/
	@Schema(description = "메시지 내용"  )
	private String content;
	
	/*
	  에러시 에러 메세지
	*/
	@Schema(description = "에러시 에러 메세지"  )
	@Size(max=512)
	private String errMsg;
	
	/*
	  수신일시
	*/
	@Schema(description = "수신일시"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime recvDatetime;
	
	/*
	  수신(통화) 종료일시
	*/
	@Schema(description = "수신(통화) 종료일시"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime recvEndDatetime;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDate;
	
	/*
	  시스템 입력 IP
	*/
	@Schema(description = "시스템 입력 IP"  )
	@Size(max=20)
	private String createIP;
	
	/*
	  시스템 수정 IP
	*/
	@Schema(description = "시스템 수정 IP"  )
	@Size(max=20)
	private String lastModifiedIP;
	
	@Schema(description = "createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	

}

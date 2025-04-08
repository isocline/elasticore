//ecd:-102640178H20250405141628_V1.0
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
 * MessageSrchDTO
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class MessageSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  메시지 상태
	*/
	@Schema(description = "메시지 상태 Field equals value. field:status"  , example="PD: 대기 | OK: 전송완료 | FL: 전송실패")
	private MessageStatus status;
	
	/*
	  최종 전송 메세지 타입
	*/
	@Schema(description = "최종 전송 메세지 타입 Field equals value. field:msgType"  , example="SMS: 단문문자 | LMS: 장문문자 | CALL: 전화발신 | KAKAO: 카카오톡 | OBC: 전화발신 | FAX: 팩스")
	private MessageType msgType;
	
	/*
	  메세지 아이디
	*/
	@Schema(description = "메세지 아이디 Field equals value. field:msgId"  )
	private Long msgId;
	
	/*
	  고객 채널 아이디
	*/
	@Schema(description = "고객 채널 아이디 Use 'like' if value has %, else 'equal' field:ccId"  )
	private String ccId;
	
	/*
	  발신자 정보(전화번호)
	*/
	@Schema(description = "발신자 정보(전화번호) Use 'like' if value has %, else 'equal' field:sender"  )
	private String sender;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:recipient"  )
	private String recipient;
	
	/*
	  발신 메시지 고유아이디
	*/
	@Schema(description = "발신 메시지 고유아이디 Use 'like' if value has %, else 'equal' field:uuid"  )
	private String uuid;
	
	/*
	  메시지 내용
	*/
	@Schema(description = "메시지 내용 Use 'like' if value has %, else 'equal' field:content"  )
	private String content;
	
	/*
	  에러시 에러 메세지
	*/
	@Schema(description = "에러시 에러 메세지 Use 'like' if value has %, else 'equal' field:errMsg"  )
	private String errMsg;
	
	/*
	  수신일시
	*/
	@Schema(description = "수신일시 Field is between two values (inclusive). field:recvDatetime"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime recvDatetimeFrom;
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime recvDatetimeTo;
	
	/*
	  수신(통화) 종료일시
	*/
	@Schema(description = "수신(통화) 종료일시 Field is between two values (inclusive). field:recvEndDatetime"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime recvEndDatetimeFrom;
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime recvEndDatetimeTo;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:lastModifiedBy"  )
	private String lastModifiedBy;
	
	@Schema(description = "Field is between two values (inclusive). field:lastModifiedDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDateFrom;
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDateTo;
	
	/*
	  시스템 입력 IP
	*/
	@Schema(description = "시스템 입력 IP Use 'like' if value has %, else 'equal' field:createIP"  )
	private String createIP;
	
	/*
	  시스템 수정 IP
	*/
	@Schema(description = "시스템 수정 IP Use 'like' if value has %, else 'equal' field:lastModifiedIP"  )
	private String lastModifiedIP;
	
	@Schema(description = "Field is between two values (inclusive). field:createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDateFrom;
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDateTo;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:createdBy"  )
	private String createdBy;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=50;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

//ecd:28876733H20240806171759_V0.8
package com.mobillug.gateone.biz.dto;

import com.mobillug.gateone.biz.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class LoginHistoryDTO  implements java.io.Serializable  {

	@Schema(description = "seq"  )
	private Long seq;
	
	@Schema(description = "userId" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String userId;
	
	/*
	  로그인 성공여부
	*/
	@Schema(description = "로그인 성공여부"  , example="Y: | N:")
	private Indicator successYN = Indicator.NO;
	
	/*
	  토큰
	*/
	@Schema(description = "토큰"  )
	@Size(max=1000)
	private String token;
	
	/*
	  agent 정보
	*/
	@Schema(description = "agent 정보"  )
	@Size(max=1000)
	private String agentInfo;
	
	/*
	  client IP.a
	*/
	@Schema(description = "client IP.a"  )
	@Size(max=36)
	private String clientIp;
	
	/*
	  token 만료일시
	*/
	@Schema(description = "token 만료일시"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime expireDateTime;
	
	/*
	  생성일시
	*/
	@Schema(description = "생성일시"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDateTime;
	
	@Schema(description = "createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDate;
	
};

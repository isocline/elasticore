//ecd:-1310992305H20240806171759_V0.8
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
public  class LoginUserSrchResultDTO  implements java.io.Serializable  {

	/*
	  사용자 아이디
	*/
	@Schema(description = "사용자 아이디"  )
	@Size(max=30)
	private String id;
	
	@Schema(description = "userId" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String userId;
	
	@Schema(description = "password" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=128)
	private String password;
	
	@Schema(description = "name" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=64)
	private String name;
	
	@Schema(description = "phone" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=16)
	private String phone;
	
	/*
	  이메일 주소
	*/
	@Schema(description = "이메일 주소"  )
	@Size(max=128)
	private String email;
	
	/*
	  아이디 상태 (정상, 패스워드 5회 실패, 정지)
	*/
	@Schema(description = "아이디 상태 (정상, 패스워드 5회 실패, 정지)"  , example="A: 정상 | F: 패스워드 5회 실패 | S: 정지")
	private UserStatus status;
	
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

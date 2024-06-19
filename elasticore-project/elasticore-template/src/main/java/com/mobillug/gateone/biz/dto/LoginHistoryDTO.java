//ecd:213011276H20240618012928_V0.8
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
	
	@Schema(description = "token"  )
	@Size(max=1000)
	private String token;
	
	@Schema(description = "agentInfo"  )
	@Size(max=1000)
	private String agentInfo;
	
	@Schema(description = "clientIp"  )
	@Size(max=36)
	private String clientIp;
	
	/*
	  token 만료일시
	*/
	@Schema(description = "token 만료일시"  )
	private java.time.LocalDateTime expireDateTime;
	
	/*
	  생성일시
	*/
	@Schema(description = "생성일시"  )
	private java.time.LocalDateTime createDateTime;
	
	@Schema(description = "createDate"  )
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  )
	private java.time.LocalDateTime lastModifiedDate;
	
};

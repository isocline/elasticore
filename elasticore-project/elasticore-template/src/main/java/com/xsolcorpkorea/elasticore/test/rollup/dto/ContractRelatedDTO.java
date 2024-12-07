//ecd:1749563929H20241207204629_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;

import com.xsolcorpkorea.elasticore.test.rollup.enums.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class ContractRelatedDTO  implements java.io.Serializable  {

	@Schema(description = "insuComCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insuComCd;
	
	@Schema(description = "policyNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String policyNo;
	
	/*
	  순번
	*/
	@Schema(description = "순번"  )
	@Size(max=5)
	private Integer seq;
	
	@Schema(description = "insTypeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=1)
	private String insTypeCd;
	
	@Schema(description = "insHolderNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String insHolderNm;
	
	/*
	  계약자 번호
	*/
	@Schema(description = "계약자 번호"  )
	@Size(max=13)
	private String insHolderNo;
	
	/*
	  계약 관계 코드
	*/
	@Schema(description = "계약 관계 코드"  )
	@Size(max=3)
	private String insRelCd;
	
	/*
	  계약자 나이
	*/
	@Schema(description = "계약자 나이"  )
	@Size(max=3)
	private String insAge;
	
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
	

}

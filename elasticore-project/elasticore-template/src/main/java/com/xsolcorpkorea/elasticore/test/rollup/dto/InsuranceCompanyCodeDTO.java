//ecd:-466913013H20241207204629_V1.0
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
public  class InsuranceCompanyCodeDTO  implements java.io.Serializable  {

	@Schema(description = "insCpCode" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insCpCode;
	
	@Schema(description = "insCpTypeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=2)
	private String insCpTypeCd;
	
	@Schema(description = "insCpNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=50)
	private String insCpNm;
	
	@Schema(description = "regDate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String regDate;
	
	@Schema(description = "startDate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String startDate;
	
	/*
	  종료일 (YYYYMMDD)
	*/
	@Schema(description = "종료일 (YYYYMMDD)"  )
	@Size(max=8)
	private String endDate;
	
	/*
	  담당자 이름
	*/
	@Schema(description = "담당자 이름"  )
	@Size(max=30)
	private String mgrNm;
	
	/*
	  계약 번호
	*/
	@Schema(description = "계약 번호"  )
	@Size(max=8)
	private String contNum;
	
	/*
	  계약 여부
	*/
	@Schema(description = "계약 여부"  , example="Y: | N:")
	private Indicator contYN;
	
	/*
	  관리 수량
	*/
	@Schema(description = "관리 수량"  )
	@Size(max=4)
	private Integer mgtCnt;
	
	/*
	  수수료 수량
	*/
	@Schema(description = "수수료 수량"  )
	@Size(max=4)
	private Integer feeCnt;
	
	/*
	  회사 팩스 번호
	*/
	@Schema(description = "회사 팩스 번호"  )
	@Size(max=14)
	private String companyFaxNo;
	
	/*
	  시작 수량
	*/
	@Schema(description = "시작 수량"  )
	@Size(max=4)
	private Integer startCnt;
	
	/*
	  종료 수량
	*/
	@Schema(description = "종료 수량"  )
	@Size(max=4)
	private Integer endCnt;
	
	@Schema(description = "pensionAdjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Float pensionAdjustRate;
	
	@Schema(description = "protectionAdjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Float protectionAdjustRate;
	
	@Schema(description = "adjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Float adjustRate;
	
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

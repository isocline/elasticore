//ecd:-1321863135H20241207204629_V1.0
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
public  class InsProductCodeDTO  implements java.io.Serializable  {

	@Schema(description = "insCpCode" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insCpCode;
	
	@Schema(description = "productCode" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String productCode;
	
	@Schema(description = "prodNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=500)
	private String prodNm;
	
	@Schema(description = "prodTypeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=3)
	private String prodTypeCd;
	
	@Schema(description = "prodGrpCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=5)
	private String prodGrpCd;
	
	@Schema(description = "insProdCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String insProdCd;
	
	@Schema(description = "saleStartDate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String saleStartDate;
	
	/*
	  판매 종료일 (YYYYMMDD)
	*/
	@Schema(description = "판매 종료일 (YYYYMMDD)"  )
	@Size(max=8)
	private String saleEndDate;
	
	/*
	  판매 여부
	*/
	@Schema(description = "판매 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator saleYN = Indicator.NO;
	
	/*
	  관리 수량
	*/
	@Schema(description = "관리 수량"  )
	@Size(max=4)
	private Integer mgtCnt;
	
	@Schema(description = "adjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Builder.Default
	private Integer adjustRate = 1;
	
	@Schema(description = "newAdjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Builder.Default
	private Float newAdjustRate = 100F;
	
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

//ecd:1841495106H20241207204629_V1.0
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
public  class CommissionMasterDTO  implements java.io.Serializable  {

	@Schema(description = "closYm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String closYm;
	
	@Schema(description = "insCpCode" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insCpCode;
	
	@Schema(description = "policyNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String policyNo;
	
	@Schema(description = "endPayCnt" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Integer endPayCnt;
	
	@Schema(description = "policStateCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=2)
	private String policStateCd;
	
	@Schema(description = "empNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String empNo;
	
	/*
	  직원 이름
	*/
	@Schema(description = "직원 이름"  )
	@Size(max=50)
	private String empName;
	
	/*
	  조직 직원 번호
	*/
	@Schema(description = "조직 직원 번호"  )
	@Size(max=15)
	private String orgEmpNo;
	
	/*
	  보험 상품 코드
	*/
	@Schema(description = "보험 상품 코드"  )
	@Size(max=8)
	private String insProdCd;
	
	/*
	  상품 코드
	*/
	@Schema(description = "상품 코드"  )
	@Size(max=20)
	private String productCode;
	
	/*
	  계약자 이름
	*/
	@Schema(description = "계약자 이름"  )
	@Size(max=10)
	private String polHolderNm;
	
	/*
	  계약일 (YYYYMMDD)
	*/
	@Schema(description = "계약일 (YYYYMMDD)"  )
	@Size(max=8)
	private String contYmd;
	
	/*
	  납입 주기 코드
	*/
	@Schema(description = "납입 주기 코드"  )
	@Size(max=6)
	private String payCyclCd;
	
	/*
	  납입 방식 코드
	*/
	@Schema(description = "납입 방식 코드"  )
	@Size(max=4)
	private String pmtyCd;
	
	/*
	  납입 방법 코드
	*/
	@Schema(description = "납입 방법 코드"  )
	@Size(max=3)
	private String payMethodCd;
	
	/*
	  납입일
	*/
	@Schema(description = "납입일"  )
	@Size(max=15)
	private String payYmd;
	
	/*
	  보험료
	*/
	@Schema(description = "보험료"  )
	private Long inPrem;
	
	/*
	  배분 횟수
	*/
	@Schema(description = "배분 횟수"  )
	private Integer distrCnt;
	
	/*
	  계약 수수료
	*/
	@Schema(description = "계약 수수료"  )
	private Long cnvnFre;
	
	/*
	  계약 전환 횟수
	*/
	@Schema(description = "계약 전환 횟수"  )
	private Integer cnvrtCnt;
	
	/*
	  실적 달성 비율
	*/
	@Schema(description = "실적 달성 비율"  )
	private Integer efficAchi;
	
	/*
	  납입 년월
	*/
	@Schema(description = "납입 년월"  )
	@Size(max=6)
	private String payYMM;
	
	/*
	  수수료 금액
	*/
	@Schema(description = "수수료 금액"  )
	private Long cmiFeeAmt;
	
	/*
	  환급 수수료 금액
	*/
	@Schema(description = "환급 수수료 금액"  )
	private Long payBackFeeAmt;
	
	/*
	  인센티브 수수료
	*/
	@Schema(description = "인센티브 수수료"  )
	private Long incentiveFee;
	
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

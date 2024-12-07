//ecd:1545976204H20241207204629_V1.0
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
public  class CommissionDetailsDTO  implements java.io.Serializable  {

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
	
	@Schema(description = "commiTypeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=2)
	private String commiTypeCd;
	
	@Schema(description = "cmiFeeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String cmiFeeCd;
	
	@Schema(description = "feeStateCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=2)
	private String feeStateCd;
	
	/*
	  환산 보험료
	*/
	@Schema(description = "환산 보험료"  )
	private Long cnvrFe;
	
	/*
	  수수료율
	*/
	@Schema(description = "수수료율"  )
	private Float cmiFeeRate;
	
	/*
	  환수 대상 금액
	*/
	@Schema(description = "환수 대상 금액"  )
	private Long payBackFeeAmt;
	
	/*
	  수수료
	*/
	@Schema(description = "수수료"  )
	private Long cmiFeeAmt;
	
	/*
	  계약 상태 코드
	*/
	@Schema(description = "계약 상태 코드"  )
	@Size(max=2)
	private String policStateCd;
	
	/*
	  전산 처리 일자
	*/
	@Schema(description = "전산 처리 일자"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime sysInputDate;
	
	/*
	  전산 처리자
	*/
	@Schema(description = "전산 처리자"  )
	@Size(max=20)
	private String sysInputUser;
	
	/*
	  사용자 IP
	*/
	@Schema(description = "사용자 IP"  )
	@Size(max=20)
	private String sysInputIP;
	
	/*
	  수수료 패턴 코드
	*/
	@Schema(description = "수수료 패턴 코드"  )
	@Size(max=6)
	private String payPtrnCode;
	

}

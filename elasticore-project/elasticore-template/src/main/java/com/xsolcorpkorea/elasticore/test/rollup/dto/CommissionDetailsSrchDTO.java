//ecd:161581266H20241207204629_V1.0
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
public  class CommissionDetailsSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:closYm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String closYm;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:insCpCode" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insCpCode;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:policyNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String policyNo;
	
	@Schema(description = "Field equals value. field:endPayCnt" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Integer endPayCnt;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:commiTypeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=2)
	private String commiTypeCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:cmiFeeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String cmiFeeCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:feeStateCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=2)
	private String feeStateCd;
	
	/*
	  환산 보험료
	*/
	@Schema(description = "환산 보험료 Field equals value. field:cnvrFe"  )
	private Long cnvrFe;
	
	/*
	  수수료율
	*/
	@Schema(description = "수수료율 Field equals value. field:cmiFeeRate"  )
	private Float cmiFeeRate;
	
	/*
	  환수 대상 금액
	*/
	@Schema(description = "환수 대상 금액 Field equals value. field:payBackFeeAmt"  )
	private Long payBackFeeAmt;
	
	/*
	  수수료
	*/
	@Schema(description = "수수료 Field equals value. field:cmiFeeAmt"  )
	private Long cmiFeeAmt;
	
	/*
	  계약 상태 코드
	*/
	@Schema(description = "계약 상태 코드 Use 'like' if value has %, else 'equal' field:policStateCd"  )
	@Size(max=2)
	private String policStateCd;
	
	/*
	  전산 처리 일자
	*/
	@Schema(description = "전산 처리 일자 Field equals value. field:sysInputDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime sysInputDate;
	
	/*
	  전산 처리자
	*/
	@Schema(description = "전산 처리자 Use 'like' if value has %, else 'equal' field:sysInputUser"  )
	@Size(max=20)
	private String sysInputUser;
	
	/*
	  사용자 IP
	*/
	@Schema(description = "사용자 IP Use 'like' if value has %, else 'equal' field:sysInputIP"  )
	@Size(max=20)
	private String sysInputIP;
	
	/*
	  수수료 패턴 코드
	*/
	@Schema(description = "수수료 패턴 코드 Use 'like' if value has %, else 'equal' field:payPtrnCode"  )
	@Size(max=6)
	private String payPtrnCode;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

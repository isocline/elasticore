//ecd:1609146722H20241207204629_V1.0
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
public  class CommissionMasterSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

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
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:policStateCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=2)
	private String policStateCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:empNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String empNo;
	
	/*
	  직원 이름
	*/
	@Schema(description = "직원 이름 Use 'like' if value has %, else 'equal' field:empName"  )
	@Size(max=50)
	private String empName;
	
	/*
	  조직 직원 번호
	*/
	@Schema(description = "조직 직원 번호 Use 'like' if value has %, else 'equal' field:orgEmpNo"  )
	@Size(max=15)
	private String orgEmpNo;
	
	/*
	  보험 상품 코드
	*/
	@Schema(description = "보험 상품 코드 Use 'like' if value has %, else 'equal' field:insProdCd"  )
	@Size(max=8)
	private String insProdCd;
	
	/*
	  상품 코드
	*/
	@Schema(description = "상품 코드 Use 'like' if value has %, else 'equal' field:productCode"  )
	@Size(max=20)
	private String productCode;
	
	/*
	  계약자 이름
	*/
	@Schema(description = "계약자 이름 Use 'like' if value has %, else 'equal' field:polHolderNm"  )
	@Size(max=10)
	private String polHolderNm;
	
	/*
	  계약일 (YYYYMMDD)
	*/
	@Schema(description = "계약일 (YYYYMMDD) Use 'like' if value has %, else 'equal' field:contYmd"  )
	@Size(max=8)
	private String contYmd;
	
	/*
	  납입 주기 코드
	*/
	@Schema(description = "납입 주기 코드 Use 'like' if value has %, else 'equal' field:payCyclCd"  )
	@Size(max=6)
	private String payCyclCd;
	
	/*
	  납입 방식 코드
	*/
	@Schema(description = "납입 방식 코드 Use 'like' if value has %, else 'equal' field:pmtyCd"  )
	@Size(max=4)
	private String pmtyCd;
	
	/*
	  납입 방법 코드
	*/
	@Schema(description = "납입 방법 코드 Use 'like' if value has %, else 'equal' field:payMethodCd"  )
	@Size(max=3)
	private String payMethodCd;
	
	/*
	  납입일
	*/
	@Schema(description = "납입일 Use 'like' if value has %, else 'equal' field:payYmd"  )
	@Size(max=15)
	private String payYmd;
	
	/*
	  보험료
	*/
	@Schema(description = "보험료 Field equals value. field:inPrem"  )
	private Long inPrem;
	
	/*
	  배분 횟수
	*/
	@Schema(description = "배분 횟수 Field equals value. field:distrCnt"  )
	private Integer distrCnt;
	
	/*
	  계약 수수료
	*/
	@Schema(description = "계약 수수료 Field equals value. field:cnvnFre"  )
	private Long cnvnFre;
	
	/*
	  계약 전환 횟수
	*/
	@Schema(description = "계약 전환 횟수 Field equals value. field:cnvrtCnt"  )
	private Integer cnvrtCnt;
	
	/*
	  실적 달성 비율
	*/
	@Schema(description = "실적 달성 비율 Field equals value. field:efficAchi"  )
	private Integer efficAchi;
	
	/*
	  납입 년월
	*/
	@Schema(description = "납입 년월 Use 'like' if value has %, else 'equal' field:payYMM"  )
	@Size(max=6)
	private String payYMM;
	
	/*
	  수수료 금액
	*/
	@Schema(description = "수수료 금액 Field equals value. field:cmiFeeAmt"  )
	private Long cmiFeeAmt;
	
	/*
	  환급 수수료 금액
	*/
	@Schema(description = "환급 수수료 금액 Field equals value. field:payBackFeeAmt"  )
	private Long payBackFeeAmt;
	
	/*
	  인센티브 수수료
	*/
	@Schema(description = "인센티브 수수료 Field equals value. field:incentiveFee"  )
	private Long incentiveFee;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

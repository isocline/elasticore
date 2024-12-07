//ecd:1300021670H20241207204629_V1.0
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
public  class CommissionContractDetailsSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

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
	@Size(max=2)
	private String cmiFeeCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:feeStateCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=2)
	private String feeStateCd;
	
	@Schema(description = "Field equals value. field:cnvFre" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Long cnvFre;
	
	@Schema(description = "Field equals value. field:cmiFeeRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Integer cmiFeeRate;
	
	@Schema(description = "Field equals value. field:payBackFeeAmt" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Long payBackFeeAmt;
	
	@Schema(description = "Field equals value. field:cmiFeeAmt" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Long cmiFeeAmt;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:empNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String empNo;
	
	/*
	  납입 패턴 코드
	*/
	@Schema(description = "납입 패턴 코드 Use 'like' if value has %, else 'equal' field:payPtrnCode"  )
	@Size(max=6)
	private String payPtrnCode;
	
	/*
	  상품 그룹 코드
	*/
	@Schema(description = "상품 그룹 코드 Use 'like' if value has %, else 'equal' field:prodGrpCd"  )
	@Size(max=6)
	private String prodGrpCd;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

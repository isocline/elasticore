//ecd:20884834H20241207204629_V1.0
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
public  class ContractPaymentSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:policyNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String policyNo;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:insuComCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insuComCd;
	
	/*
	  순번
	*/
	@Schema(description = "순번 Field equals value. field:seq"  )
	@Size(max=5)
	private Integer seq;
	
	/*
	  배포 횟수
	*/
	@Schema(description = "배포 횟수 Field equals value. field:distrCnt"  )
	@Size(max=3)
	private Integer distrCnt;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:payYMM" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String payYMM;
	
	/*
	  납입 방법 코드
	*/
	@Schema(description = "납입 방법 코드 Use 'like' if value has %, else 'equal' field:payMethodCd"  )
	@Size(max=4)
	private String payMethodCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:payYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String payYmd;
	
	@Schema(description = "Field equals value. field:sumPrem" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Long sumPrem;
	
	/*
	  수정 보험료
	*/
	@Schema(description = "수정 보험료 Field equals value. field:modifyPrem"  )
	private Long modifyPrem;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:payStateCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=3)
	private String payStateCd;
	
	/*
	  영수증 번호
	*/
	@Schema(description = "영수증 번호 Use 'like' if value has %, else 'equal' field:rectNo"  )
	@Size(max=30)
	private String rectNo;
	
	/*
	  요율 유형
	*/
	@Schema(description = "요율 유형 Use 'like' if value has %, else 'equal' field:rateType"  )
	@Size(max=4)
	private String rateType;
	
	@Schema(description = "Field equals value. field:payAmt" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Long payAmt;
	
	/*
	  작업일자
	*/
	@Schema(description = "작업일자 Use 'like' if value has %, else 'equal' field:jobDate"  )
	@Size(max=8)
	private String jobDate;
	
	/*
	  마감 여부
	*/
	@Schema(description = "마감 여부 Field equals value. field:closYn"  , example="Y: | N:")
	private Indicator closYn;
	
	/*
	  계약번호 (이전)
	*/
	@Schema(description = "계약번호 (이전) Use 'like' if value has %, else 'equal' field:policyNo_BK"  )
	@Size(max=30)
	private String policyNo_BK;
	
	/*
	  계약번호 (신규)
	*/
	@Schema(description = "계약번호 (신규) Use 'like' if value has %, else 'equal' field:policyNo_New"  )
	@Size(max=30)
	private String policyNo_New;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

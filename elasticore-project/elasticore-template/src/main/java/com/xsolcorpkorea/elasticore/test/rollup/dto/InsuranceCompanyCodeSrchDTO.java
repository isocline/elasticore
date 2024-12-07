//ecd:-1602762437H20241207204629_V1.0
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
public  class InsuranceCompanyCodeSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:insCpCode" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insCpCode;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:insCpTypeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=2)
	private String insCpTypeCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:insCpNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=50)
	private String insCpNm;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:regDate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String regDate;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:startDate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String startDate;
	
	/*
	  종료일 (YYYYMMDD)
	*/
	@Schema(description = "종료일 (YYYYMMDD) Use 'like' if value has %, else 'equal' field:endDate"  )
	@Size(max=8)
	private String endDate;
	
	/*
	  담당자 이름
	*/
	@Schema(description = "담당자 이름 Use 'like' if value has %, else 'equal' field:mgrNm"  )
	@Size(max=30)
	private String mgrNm;
	
	/*
	  계약 번호
	*/
	@Schema(description = "계약 번호 Use 'like' if value has %, else 'equal' field:contNum"  )
	@Size(max=8)
	private String contNum;
	
	/*
	  계약 여부
	*/
	@Schema(description = "계약 여부 Field equals value. field:contYN"  , example="Y: | N:")
	private Indicator contYN;
	
	/*
	  관리 수량
	*/
	@Schema(description = "관리 수량 Field equals value. field:mgtCnt"  )
	@Size(max=4)
	private Integer mgtCnt;
	
	/*
	  수수료 수량
	*/
	@Schema(description = "수수료 수량 Field equals value. field:feeCnt"  )
	@Size(max=4)
	private Integer feeCnt;
	
	/*
	  회사 팩스 번호
	*/
	@Schema(description = "회사 팩스 번호 Use 'like' if value has %, else 'equal' field:companyFaxNo"  )
	@Size(max=14)
	private String companyFaxNo;
	
	/*
	  시작 수량
	*/
	@Schema(description = "시작 수량 Field equals value. field:startCnt"  )
	@Size(max=4)
	private Integer startCnt;
	
	/*
	  종료 수량
	*/
	@Schema(description = "종료 수량 Field equals value. field:endCnt"  )
	@Size(max=4)
	private Integer endCnt;
	
	@Schema(description = "Field equals value. field:pensionAdjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Float pensionAdjustRate;
	
	@Schema(description = "Field equals value. field:protectionAdjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Float protectionAdjustRate;
	
	@Schema(description = "Field equals value. field:adjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Float adjustRate;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

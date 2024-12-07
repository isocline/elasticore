//ecd:-1506422012H20241207204629_V1.0
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
public  class InsProductCodeSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:insCpCode" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insCpCode;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:productCode" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String productCode;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:prodNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=500)
	private String prodNm;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:prodTypeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=3)
	private String prodTypeCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:prodGrpCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=5)
	private String prodGrpCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:insProdCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String insProdCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:saleStartDate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String saleStartDate;
	
	/*
	  판매 종료일 (YYYYMMDD)
	*/
	@Schema(description = "판매 종료일 (YYYYMMDD) Use 'like' if value has %, else 'equal' field:saleEndDate"  )
	@Size(max=8)
	private String saleEndDate;
	
	/*
	  판매 여부
	*/
	@Schema(description = "판매 여부 Field equals value. field:saleYN"  , example="Y: | N:")
	private Indicator saleYN;
	
	/*
	  관리 수량
	*/
	@Schema(description = "관리 수량 Field equals value. field:mgtCnt"  )
	@Size(max=4)
	private Integer mgtCnt;
	
	@Schema(description = "Field equals value. field:adjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Integer adjustRate;
	
	@Schema(description = "Field equals value. field:newAdjustRate" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Float newAdjustRate;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

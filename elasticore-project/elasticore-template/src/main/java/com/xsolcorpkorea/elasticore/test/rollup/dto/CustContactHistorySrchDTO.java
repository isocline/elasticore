//ecd:1152919897H20241207204629_V1.0
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
public  class CustContactHistorySrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:empNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String empNo;
	
	/*
	  접촉 시퀀스
	*/
	@Schema(description = "접촉 시퀀스 Field equals value. field:contactSeq"  )
	@Size(max=5)
	private Integer contactSeq;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:contactYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String contactYmd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:contactTime" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=12)
	private String contactTime;
	
	/*
	  접촉 코드
	*/
	@Schema(description = "접촉 코드 Use 'like' if value has %, else 'equal' field:contactCd"  )
	@Size(max=2)
	private String contactCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:custNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String custNm;
	
	/*
	  고객 시퀀스
	*/
	@Schema(description = "고객 시퀀스 Use 'like' if value has %, else 'equal' field:custSeq"  )
	@Size(max=13)
	private String custSeq;
	
	/*
	  고객 번호
	*/
	@Schema(description = "고객 번호 Use 'like' if value has %, else 'equal' field:custNo"  )
	@Size(max=13)
	private String custNo;
	
	/*
	  정책 번호
	*/
	@Schema(description = "정책 번호 Use 'like' if value has %, else 'equal' field:policyNo"  )
	@Size(max=30)
	private String policyNo;
	
	/*
	  오픈 여부
	*/
	@Schema(description = "오픈 여부 Field equals value. field:openYN"  , example="Y: | N:")
	private Indicator openYN;
	
	/*
	  접촉 제목
	*/
	@Schema(description = "접촉 제목 Use 'like' if value has %, else 'equal' field:contactTtl"  )
	@Size(max=100)
	private String contactTtl;
	
	/*
	  접촉 장소
	*/
	@Schema(description = "접촉 장소 Use 'like' if value has %, else 'equal' field:contactPlace"  )
	@Size(max=100)
	private String contactPlace;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:contactDesc" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=300)
	private String contactDesc;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

//ecd:1744897385H20241207204629_V1.0
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
public  class OrganizationSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:orgCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String orgCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:orgNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=300)
	private String orgNm;
	
	/*
	  조직 레벨 코드
	*/
	@Schema(description = "조직 레벨 코드 Use 'like' if value has %, else 'equal' field:orgLevelCd"  )
	@Size(max=15)
	private String orgLevelCd;
	
	/*
	  상위 조직 코드
	*/
	@Schema(description = "상위 조직 코드 Use 'like' if value has %, else 'equal' field:upOrgCd"  )
	@Size(max=15)
	private String upOrgCd;
	
	/*
	  정렬 순서
	*/
	@Schema(description = "정렬 순서 Field equals value. field:orderNo"  )
	private Long orderNo;
	
	/*
	  조직장 ID
	*/
	@Schema(description = "조직장 ID Use 'like' if value has %, else 'equal' field:orgBossId"  )
	@Size(max=15)
	private String orgBossId;
	
	/*
	  직통전화 1
	*/
	@Schema(description = "직통전화 1 Use 'like' if value has %, else 'equal' field:directPhone1"  )
	@Size(max=4)
	private String directPhone1;
	
	/*
	  직통전화 2
	*/
	@Schema(description = "직통전화 2 Use 'like' if value has %, else 'equal' field:directPhone2"  )
	@Size(max=4)
	private String directPhone2;
	
	/*
	  직통전화 3
	*/
	@Schema(description = "직통전화 3 Use 'like' if value has %, else 'equal' field:directPhone3"  )
	@Size(max=4)
	private String directPhone3;
	
	/*
	  팩스 전화 1
	*/
	@Schema(description = "팩스 전화 1 Use 'like' if value has %, else 'equal' field:faxPhone1"  )
	@Size(max=4)
	private String faxPhone1;
	
	/*
	  팩스 전화 2
	*/
	@Schema(description = "팩스 전화 2 Use 'like' if value has %, else 'equal' field:faxPhone2"  )
	@Size(max=4)
	private String faxPhone2;
	
	/*
	  팩스 전화 3
	*/
	@Schema(description = "팩스 전화 3 Use 'like' if value has %, else 'equal' field:faxPhone3"  )
	@Size(max=4)
	private String faxPhone3;
	
	/*
	  우편번호 1
	*/
	@Schema(description = "우편번호 1 Use 'like' if value has %, else 'equal' field:postNo1"  )
	@Size(max=7)
	private String postNo1;
	
	/*
	  우편번호 2
	*/
	@Schema(description = "우편번호 2 Use 'like' if value has %, else 'equal' field:postNo2"  )
	@Size(max=7)
	private String postNo2;
	
	/*
	  기본 주소
	*/
	@Schema(description = "기본 주소 Use 'like' if value has %, else 'equal' field:baseAddr"  )
	@Size(max=200)
	private String baseAddr;
	
	/*
	  상세 주소
	*/
	@Schema(description = "상세 주소 Use 'like' if value has %, else 'equal' field:detailAddr"  )
	@Size(max=300)
	private String detailAddr;
	
	/*
	  개설일자
	*/
	@Schema(description = "개설일자 Use 'like' if value has %, else 'equal' field:openYmd"  )
	@Size(max=8)
	private String openYmd;
	
	/*
	  폐쇄일자
	*/
	@Schema(description = "폐쇄일자 Use 'like' if value has %, else 'equal' field:closeYmd"  )
	@Size(max=8)
	private String closeYmd;
	
	/*
	  조직 유형 코드
	*/
	@Schema(description = "조직 유형 코드 Use 'like' if value has %, else 'equal' field:orgTypeCd"  )
	@Size(max=15)
	private String orgTypeCd;
	
	/*
	  증감 조직 코드
	*/
	@Schema(description = "증감 조직 코드 Use 'like' if value has %, else 'equal' field:increaseOrgCd"  )
	@Size(max=15)
	private String increaseOrgCd;
	
	/*
	  검색 여부
	*/
	@Schema(description = "검색 여부 Field equals value. field:searchYN"  , example="Y: | N:")
	private Indicator searchYN;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

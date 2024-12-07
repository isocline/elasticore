//ecd:-1768117236H20241207204629_V1.0
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
public  class EmpFamilySrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  사원번호
	*/
	@Schema(description = "사원번호 Use 'like' if value has %, else 'equal' field:empNo"  )
	@Size(max=15)
	private String empNo;
	
	/*
	  순번
	*/
	@Schema(description = "순번 Field equals value. field:seq"  )
	@Size(max=5)
	private Long seq;
	
	/*
	  주민번호
	*/
	@Schema(description = "주민번호 Use 'like' if value has %, else 'equal' field:juminNo"  )
	@Size(max=13)
	private String juminNo;
	
	/*
	  가족관계명
	*/
	@Schema(description = "가족관계명 Use 'like' if value has %, else 'equal' field:familyRelNm"  )
	@Size(max=30)
	private String familyRelNm;
	
	/*
	  이름
	*/
	@Schema(description = "이름 Use 'like' if value has %, else 'equal' field:name"  )
	@Size(max=20)
	private String name;
	
	/*
	  기타사항
	*/
	@Schema(description = "기타사항 Use 'like' if value has %, else 'equal' field:etc"  )
	@Size(max=50)
	private String etc;
	
	/*
	  동거여부
	*/
	@Schema(description = "동거여부 Field equals value. field:togetherYn"  , example="Y: | N:")
	private Indicator togetherYn;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

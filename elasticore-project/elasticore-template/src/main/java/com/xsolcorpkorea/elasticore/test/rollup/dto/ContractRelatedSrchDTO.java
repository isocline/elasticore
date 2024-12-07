//ecd:-1487622089H20241207204629_V1.0
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
public  class ContractRelatedSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:insuComCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insuComCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:policyNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String policyNo;
	
	/*
	  순번
	*/
	@Schema(description = "순번 Field equals value. field:seq"  )
	@Size(max=5)
	private Integer seq;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:insTypeCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=1)
	private String insTypeCd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:insHolderNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String insHolderNm;
	
	/*
	  계약자 번호
	*/
	@Schema(description = "계약자 번호 Use 'like' if value has %, else 'equal' field:insHolderNo"  )
	@Size(max=13)
	private String insHolderNo;
	
	/*
	  계약 관계 코드
	*/
	@Schema(description = "계약 관계 코드 Use 'like' if value has %, else 'equal' field:insRelCd"  )
	@Size(max=3)
	private String insRelCd;
	
	/*
	  계약자 나이
	*/
	@Schema(description = "계약자 나이 Use 'like' if value has %, else 'equal' field:insAge"  )
	@Size(max=3)
	private String insAge;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

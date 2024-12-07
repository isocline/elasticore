//ecd:1270132834H20241207204629_V1.0
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
public  class ContractSpecialClauseSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

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
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:speNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=50)
	private String speNm;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:startYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String startYmd;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:endYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String endYmd;
	
	@Schema(description = "Field equals value. field:basePrem" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Long basePrem;
	
	@Schema(description = "Field equals value. field:payPrem" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Long payPrem;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:speCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String speCd;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

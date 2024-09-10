//ecd:1932634335H20240903204849_V1.0
package com.mobillug.linkone.biz.dto;


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
public  class PremiumCalcResultSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  케이스 번호
	*/
	@Schema(description = "케이스 번호"  )
	private String caseNumber;
	
	@Schema(description = "id"  )
	private Long id;
	
	@Schema(description = "processId"  )
	private String processId;
	
	/*
	  상품코드
	*/
	@Schema(description = "상품코드"  )
	private String prodCd;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=100;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

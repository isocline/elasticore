//ecd:1068202257H20250310224803_V1.0
package com.test.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class AddressSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  우편번호
	*/
	@Schema(description = "우편번호 Use 'like' if value has %, else 'equal' field:postNo"  )
	@Size(max=5)
	private String postNo;
	
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
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

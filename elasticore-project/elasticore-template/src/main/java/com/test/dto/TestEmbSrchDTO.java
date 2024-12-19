//ecd:2120501057H20241219144053_V1.0
package com.test.dto;


import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class TestEmbSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  조건
	*/
	@Schema(description = "조건 Use 'like' if value has %, else 'equal' field:condition"  )
	private String condition;
	
	/*
	  값
	*/
	@Schema(description = "값 Field equals value. field:value"  )
	private Double value;
	
	/*
	  설명
	*/
	@Schema(description = "설명 Use 'like' if value has %, else 'equal' field:content"  )
	private String content;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

//ecd:1467704367H20250312162715_V1.0
package com.test.dto;

import com.test.enums.*;
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
public  class BaseSearchInput  implements java.io.Serializable,PageableObject  {

	@Schema(description = "pageNumber"  )
	@Builder.Default
	private int pageNumber = 0;
	
	@Schema(description = "pageSize"  )
	@Builder.Default
	private int pageSize = 50;
	
	@Schema(description = "sortCode"  )
	private String sortCode;
	
	@Schema(description = "sortColumn"  )
	private String sortColumn;
	
	@Schema(description = "sortAscending"  )
	private Boolean sortAscending;
	

}

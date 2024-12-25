//ecd:2004494033H20241224183051_V1.0
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
public  class ArticleSrchDTO  implements java.io.Serializable, SortableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:id"  )
	private String id;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

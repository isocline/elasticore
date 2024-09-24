//ecd:-952330881H20240924235117_V1.0
package com.xsolcorpkorea.elasticore.test.dto.dto;


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
public  class CustomerSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "id"  )
	private Long id;
	
	@Schema(description = "name"  )
	private String name;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=100;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

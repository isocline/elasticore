//ecd:-1031479370H20250204014854_V1.0
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
public  class EmployeeSrchDTO  implements java.io.Serializable, SortableObject  {

	@Schema(description = "Field equals value. field:companyCid"  )
	private String companyCid;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:id"  )
	private String id;
	
	/*
	  직원명
	*/
	@Schema(description = "직원명 Field matches pattern. field:name" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private String name;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

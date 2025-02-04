//ecd:-1182010565H20250204014854_V1.0
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
public  class CompanySrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  PK
	*/
	@Schema(description = "PK Use 'like' if value has %, else 'equal' field:cid"  )
	@Size(max=30)
	private String cid;
	
	/*
	  회사명
	*/
	@Schema(description = "회사명 Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

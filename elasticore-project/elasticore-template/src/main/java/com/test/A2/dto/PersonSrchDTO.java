//ecd:-1079086510H20241212190121_V1.0
package com.test.A2.dto;


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
public  class PersonSrchDTO  implements java.io.Serializable, SortableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:id"  )
	private String id;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:name"  )
	@Size(max=12)
	private String name;
	
	@Schema(description = "Field equals value. field:age"  )
	private Integer age;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

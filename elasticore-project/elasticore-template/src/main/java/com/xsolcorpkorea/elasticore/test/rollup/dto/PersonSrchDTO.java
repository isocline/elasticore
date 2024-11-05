//ecd:751181337H20241105131031_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;


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
public  class PersonSrchDTO  implements java.io.Serializable, SortableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:personGrpId"  )
	private String personGrpId;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:id"  )
	private String id;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

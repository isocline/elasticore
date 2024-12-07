//ecd:-1964895782H20241207204629_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;

import com.xsolcorpkorea.elasticore.test.rollup.enums.*;
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

	@Schema(description = "Field equals value. field:personGrpId"  )
	private String personGrpId;
	
	@Schema(description = ""  )
	private String testZZ;
	
	@Schema(description = ""  )
	private List<PersonGroupDTO> optList;
	
	@Schema(description = "Field equals value. field:addr"  )
	private String addr;
	
	@Schema(description = "Field equals value. field:testId"  )
	private String testId;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

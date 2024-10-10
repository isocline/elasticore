//ecd:-1595362343H20241010182122_V1.0
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
public  class ResidualMobillugSrchDTO  implements java.io.Serializable, SortableObject  {

	@Schema(description = "period36"  )
	private Float period36;
	
	@Schema(description = "period48"  )
	private Float period48;
	
	@Schema(description = "period60"  )
	private Float period60;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

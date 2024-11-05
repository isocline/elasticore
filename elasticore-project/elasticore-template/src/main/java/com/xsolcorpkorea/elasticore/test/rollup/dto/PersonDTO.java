//ecd:-1547327513H20241104095801_V1.0
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
public  class PersonDTO  implements java.io.Serializable  {

	@Schema(description = "personGrp"  )
	private PersonGroupDTO personGrp;
	
	@Schema(description = "personGrpId"  )
	private String personGrpId;
	
	@Schema(description = "id"  )
	private String id;
	
	@Schema(description = "name"  )
	private String name;
	
	@Schema(description = "ownerId"  )
	private String ownerId;
	

}

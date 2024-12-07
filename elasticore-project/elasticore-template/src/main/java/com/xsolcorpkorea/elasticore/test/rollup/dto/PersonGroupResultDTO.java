//ecd:-466786485H20241207204629_V1.0
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
public  class PersonGroupResultDTO  implements java.io.Serializable  {

	@Schema(description = "id"  )
	private String id;
	
	@Schema(description = "name"  )
	private String name;
	
	@Schema(description = "scope1"  )
	private Integer scope1;
	
	@Schema(description = "scope2"  )
	private Integer scope2;
	

}

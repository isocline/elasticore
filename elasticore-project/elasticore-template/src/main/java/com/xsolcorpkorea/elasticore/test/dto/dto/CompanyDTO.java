//ecd:451740603H20240925001224_V1.0
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
public  class CompanyDTO  implements java.io.Serializable  {

	@Schema(description = "test"  )
	private String test;
	
	@Schema(description = "id"  )
	private Long id;
	
	@Schema(description = "name"  )
	private String name;
	

}

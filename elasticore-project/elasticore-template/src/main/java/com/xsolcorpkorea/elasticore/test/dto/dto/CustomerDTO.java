//ecd:1852089897H20240925000554_V1.0
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
public  class CustomerDTO  implements java.io.Serializable  {

	@Schema(description = "id"  )
	private Long id;
	
	@Schema(description = "name"  )
	private String name;
	
	@Schema(description = "company"  )
	private CompanyDTO company;
	

}

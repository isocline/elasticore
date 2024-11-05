//ecd:1308131655H20241104230940_V1.0
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
public  class PersonGroupDTO  implements java.io.Serializable  {

	@Schema(description = "personList"  )
	private List<PersonDTO> personList;
	
	@Schema(description = "id"  )
	private String id;
	
	@Schema(description = "name"  )
	private String name;
	
	@Schema(description = "scope1"  )
	private Integer scope1;
	
	@Schema(description = "scope2"  )
	private Integer scope2;
	

}

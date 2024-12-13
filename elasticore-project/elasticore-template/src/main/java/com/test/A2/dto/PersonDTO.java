//ecd:-2138733218H20241213011350_V1.0
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
public  class PersonDTO  implements java.io.Serializable  {

	@Schema(description = "id"  )
	private String id;
	
	@Schema(description = "name"  )
	@Size(max=12)
	private String name;
	
	@Schema(description = "age"  )
	private Integer age;
	
	@Schema(description = "createDate"  )
	private String createDate;
	

}

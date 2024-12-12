//ecd:-1933196148H20241212190436_V1.0
package com.test.A1.dto;


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
public  class CompanyDTO  implements java.io.Serializable  {

	@Schema(description = "id"  )
	private String id;
	
	@Schema(description = "name"  )
	@Size(max=12)
	private String name;
	
	@Schema(description = "createDate"  )
	private String createDate;
	

}

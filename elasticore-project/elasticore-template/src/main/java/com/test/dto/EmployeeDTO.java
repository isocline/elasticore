//ecd:-657642443H20250204014924_V1.0
package com.test.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.test.dto.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class EmployeeDTO  implements java.io.Serializable  {

	@Schema(description = "company"  )
	private CompanyDTO company;
	
	@Schema(description = "companyCid"  )
	private String companyCid;
	
	@Schema(description = "id"  )
	private String id;
	
	/*
	  직원명
	*/
	@Schema(description = "직원명" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private String name;
	

}

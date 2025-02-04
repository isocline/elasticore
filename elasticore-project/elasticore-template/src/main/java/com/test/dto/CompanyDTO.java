//ecd:-1537933500H20250204014448_V1.0
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



/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class CompanyDTO  implements java.io.Serializable  {

	/*
	  PK
	*/
	@Schema(description = "PK"  )
	@Size(max=30)
	private String cid;
	
	/*
	  회사명
	*/
	@Schema(description = "회사명"  )
	private String name;
	

}

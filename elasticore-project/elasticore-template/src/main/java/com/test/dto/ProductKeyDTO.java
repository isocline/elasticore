//ecd:1699709662H20250117173850_V1.0
package com.test.dto;


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
public  class ProductKeyDTO  implements java.io.Serializable  {

	/*
	  PK
	*/
	@Schema(description = "PK"  )
	@Size(max=30)
	private String pid;
	

}

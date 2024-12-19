//ecd:-2100458152H20241219144053_V1.0
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
public  class TestEmbDTO  implements java.io.Serializable  {

	/*
	  조건
	*/
	@Schema(description = "조건"  )
	private String condition;
	
	/*
	  값
	*/
	@Schema(description = "값"  )
	private Double value;
	
	/*
	  설명
	*/
	@Schema(description = "설명"  )
	private String content;
	

}

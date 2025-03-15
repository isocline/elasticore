//ecd:-549355199H20250313130133_V1.0
package com.test.dto;

import com.test.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.test.enums.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class InsureInfoKeyDTO  implements java.io.Serializable  {

	/*
	  아이디
	*/
	@Schema(description = "아이디"  )
	@Size(max=12)
	private String id;
	
	@Schema(description = "id2"  )
	private Long id2;
	

}

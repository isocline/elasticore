//ecd:-1849467015H20250318164659_V1.0
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
public  class MsgInput  implements java.io.Serializable  {

	@Schema(description = "id"  )
	private Integer id;
	
	@Schema(description = "name"  )
	private String name;
	

}

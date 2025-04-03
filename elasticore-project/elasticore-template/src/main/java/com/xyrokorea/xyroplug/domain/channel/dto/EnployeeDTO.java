//ecd:-1663879786H20250402000028_V1.0
package com.xyrokorea.xyroplug.domain.channel.dto;

import com.xyrokorea.xyroplug.domain.channel.enums.*;
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
public  class EnployeeDTO  implements java.io.Serializable  {

	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	
	@Schema(description = "name"  )
	@Size(max=500)
	private String name;
	
	@Schema(description = "addr"  )
	@Size(max=100)
	private String addr;
	
	@Schema(description = "telNo"  )
	@Size(max=12)
	private String telNo;
	

}

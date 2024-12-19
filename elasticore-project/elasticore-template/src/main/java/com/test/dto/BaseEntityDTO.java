//ecd:-1594554355H20241219144053_V1.0
package com.test.dto;


import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class BaseEntityDTO  implements java.io.Serializable  {

	@Schema(description = "emb"  )
	private TestEmbDTO emb;
	
	@Schema(description = "id"  )
	private String id;
	
	@Schema(description = "name"  )
	private String name;
	

}

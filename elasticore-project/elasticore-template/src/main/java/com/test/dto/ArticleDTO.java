//ecd:114583899H20241224182557_V1.0
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
public  class ArticleDTO  implements java.io.Serializable  {

	@Schema(description = "id"  )
	private String id;
	
	@Schema(description = "name"  )
	private String name;
	

}

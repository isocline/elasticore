//ecd:726178256H20250422113010_V1.0
package io.elasticore.blueprint.domain.parts.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * CarPartDTO
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class CarPartDTO  implements java.io.Serializable  {

	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	
	@Schema(description = "number"  )
	@Size(max=30)
	private String number;
	
	@Schema(description = "nameId"  )
	@Size(max=10)
	private String nameId;
	
	/*
	  Part name
	*/
	@Schema(description = "Part name"  )
	@Size(max=500)
	private String name;
	
	/*
	  notice
	*/
	@Schema(description = "notice"  )
	@Size(max=500)
	private String notice;
	
	/*
	  그룹 설명
	*/
	@Schema(description = "그룹 설명"  )
	@Size(max=1000)
	private String description;
	
	@Schema(description = "positionNumber"  )
	@Size(max=10)
	private String positionNumber;
	
	@Schema(description = "url"  )
	@Size(max=512)
	private String url;
	

}

//ecd:779866142H20250422165427_V1.0
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
 * PartPositionDTO
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
public  class PartPositionDTO  implements java.io.Serializable  {

	/*
	  groupId
	*/
	@Schema(description = "groupId"  )
	@Size(max=50)
	private String id;
	
	@Schema(description = "number"  )
	@Size(max=10)
	private String number;
	
	@Schema(description = "coordinates"  )
	private Integer[] coordinates;
	

}

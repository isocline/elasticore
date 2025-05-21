//ecd:167709203H20250420230457_V1.0
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
 * CarInfoOutput
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
public  class CarInfoOutput  implements java.io.Serializable  {

	/*
	  브랜드명
	*/
	@Schema(description = "브랜드명"  )
	private String brand;
	
	/*
	  설명
	*/
	@Schema(description = "설명"  )
	private String description;
	
	/*
	  모델 ID
	*/
	@Schema(description = "모델 ID"  )
	private String modelId;
	

}

//ecd:-1225556167H20250422093122_V1.0
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
 * SuggestGroupKeyDTO
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
public  class SuggestGroupKeyDTO  implements java.io.Serializable  {

	/*
	  고유 카탈로그 식별자
	*/
	@Schema(description = "고유 카탈로그 식별자" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String catalogId;
	
	/*
	  search id
	*/
	@Schema(description = "search id"  )
	@Size(max=16)
	private String sid;
	

}

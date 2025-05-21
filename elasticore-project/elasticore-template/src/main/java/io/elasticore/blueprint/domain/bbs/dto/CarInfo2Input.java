//ecd:-2021414917H20250521114808_V1.0
package io.elasticore.blueprint.domain.bbs.dto;

import io.elasticore.blueprint.domain.bbs.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import io.elasticore.blueprint.domain.bbs.enums.*;
import io.elasticore.blueprint.domain.bbs.dto.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * CarInfo2Input
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
public  class CarInfo2Input  implements java.io.Serializable  {

	/*
	  브랜드명
	*/
	@Schema(description = "브랜드명"  )
	private String brand;
	
	@Schema(description = "inputType"  )
	private CarInfo23Input inputType;
	
	@Schema(description = "boType"  , example="PB: 공개 | PV: 개인")
	private BoardType boType;
	

}

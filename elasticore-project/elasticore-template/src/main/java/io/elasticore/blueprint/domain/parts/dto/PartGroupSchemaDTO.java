//ecd:498088394H20250429191649_V1.0
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
import io.elasticore.blueprint.domain.parts.dto.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * PartGroupSchemaDTO
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
public  class PartGroupSchemaDTO  implements java.io.Serializable  {

	/*
	  리스트 그룹 ID
	*/
	@Schema(description = "리스트 그룹 ID"  )
	private String groupId;
	
	/*
	  이미지 경로
	*/
	@Schema(description = "이미지 경로"  )
	private String img;
	
	/*
	  리스트 이름
	*/
	@Schema(description = "리스트 이름"  )
	private String name;
	
	/*
	  설명
	*/
	@Schema(description = "설명"  )
	private String description;
	
	/*
	  부품 이름 리스트
	*/
	@Schema(description = "부품 이름 리스트"  )
	private List<PartNameDTO> partNames;
	

}

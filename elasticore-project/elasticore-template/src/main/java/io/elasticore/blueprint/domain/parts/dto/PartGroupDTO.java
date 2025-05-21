//ecd:-1410069343H20250425124038_V1.0
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
 * PartGroupDTO
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
public  class PartGroupDTO  implements java.io.Serializable  {

	@Schema(description = "parts"  )
	private List<CarPartDTO> parts;
	
	@Schema(description = "positions"  )
	private List<PartPositionDTO> positions;
	
	/*
	  부품 그룹 ID
	*/
	@Schema(description = "부품 그룹 ID" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=50)
	private String id;
	
	/*
	  부품 그룹 이름
	*/
	@Schema(description = "부품 그룹 이름"  )
	@Size(max=100)
	private String name;
	
	@Schema(description = "hasSubgroups" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Builder.Default
	private Boolean hasSubgroups = false;
	
	/*
	  그룹 이미지
	*/
	@Schema(description = "그룹 이미지"  )
	@Size(max=255)
	private String img;
	
	/*
	  그룹 설명
	*/
	@Schema(description = "그룹 설명"  )
	@Size(max=1000)
	private String description;
	
	/*
	  상위 그룹 ID
	*/
	@Schema(description = "상위 그룹 ID"  )
	@Size(max=36)
	private String parentId;
	
	/*
	  연결된 차량 ID
	*/
	@Schema(description = "연결된 차량 ID" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=36)
	private String carId;
	
	/*
	  차량 등록 기준
	*/
	@Schema(description = "차량 등록 기준"  )
	@Size(max=500)
	private String criteria;
	
	@Schema(description = "brand"  )
	@Size(max=100)
	private String brand;
	
	@Schema(description = "imgDescription"  )
	@Size(max=1000)
	private String imgDescription;
	

}

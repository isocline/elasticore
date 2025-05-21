//ecd:-1617346211H20250422181248_V1.0
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
 * PartGroupInfoDTO
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
public  class PartGroupInfoDTO  implements java.io.Serializable  {

	@Schema(description = "partGroups"  )
	private List<PartGroupDTO> partGroups;
	
	@Schema(description = "positions"  )
	private List<PartPositionDTO> positions;
	
	/*
	  차량 ID (UUID)
	*/
	@Schema(description = "차량 ID (UUID)" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=36)
	private String carId;
	
	/*
	  부품 그룹 ID
	*/
	@Schema(description = "부품 그룹 ID" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=50)
	private String groupId;
	
	@Schema(description = "img"  )
	@Size(max=512)
	private String img;
	
	@Schema(description = "imgDescription"  )
	@Size(max=1000)
	private String imgDescription;
	
	@Schema(description = "brand"  )
	@Size(max=100)
	private String brand;
	

}

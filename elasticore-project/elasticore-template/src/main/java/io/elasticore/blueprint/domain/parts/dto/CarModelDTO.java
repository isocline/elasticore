//ecd:-561835564H20250411121609_V1.0
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
 * CarModelDTO
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
public  class CarModelDTO  implements java.io.Serializable  {

	@Schema(description = "catalog"  )
	private CatalogDTO catalog;
	
	@Schema(description = "catalogCatalogId"  )
	private String catalogCatalogId;
	
	/*
	  차량 모델 ID (UUID)
	*/
	@Schema(description = "차량 모델 ID (UUID)" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=36)
	private String id;
	
	/*
	  모델명
	*/
	@Schema(description = "모델명" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=100)
	private String name;
	
	/*
	  모델 이미지 경로(URL 또는 파일명)
	*/
	@Schema(description = "모델 이미지 경로(URL 또는 파일명)"  )
	@Size(max=255)
	private String img;
	

}

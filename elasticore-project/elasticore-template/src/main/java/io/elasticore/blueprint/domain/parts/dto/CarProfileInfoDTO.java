//ecd:118055390H20250417100604_V1.0
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
 * CarProfileInfoDTO
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
public  class CarProfileInfoDTO  implements java.io.Serializable  {

	@Schema(description = "carId"  )
	private String carId;
	
	/*
	  VIN
	*/
	@Schema(description = "VIN" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=36)
	private String vin;
	
	/*
	  frame
	*/
	@Schema(description = "frame"  )
	@Size(max=100)
	private String frame;
	
	@Schema(description = "parameters"  )
	private List<ParamInfoDTO> parameters;
	
	/*
	  차량 ID (UUID)
	*/
	@Schema(description = "차량 ID (UUID)" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=36)
	private String id;
	
	/*
	  차량 이름 또는 사용자 정의명
	*/
	@Schema(description = "차량 이름 또는 사용자 정의명" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=300)
	private String name;
	
	/*
	  차량 상세 설명
	*/
	@Schema(description = "차량 상세 설명"  )
	@Size(max=500)
	private String description;
	
	/*
	  연결된 모델 ID
	*/
	@Schema(description = "연결된 모델 ID" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=36)
	private String modelId;
	
	/*
	  모델 이름 (조회용)
	*/
	@Schema(description = "모델 이름 (조회용)"  )
	@Size(max=500)
	private String modelName;
	
	/*
	  차량 등록 기준
	*/
	@Schema(description = "차량 등록 기준"  )
	@Size(max=500)
	private String criteria;
	
	/*
	  차량 브랜드명
	*/
	@Schema(description = "차량 브랜드명"  )
	@Size(max=50)
	private String brand;
	
	/*
	  부품 그룹 트리 지원 여부
	*/
	@Schema(description = "부품 그룹 트리 지원 여부"  )
	@Builder.Default
	private Boolean groupTreeAvailables = false;
	

}

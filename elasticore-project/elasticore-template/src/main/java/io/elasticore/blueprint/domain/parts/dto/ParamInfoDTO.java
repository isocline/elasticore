//ecd:-510078025H20250412200533_V1.0
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
 * ParamInfoDTO
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
public  class ParamInfoDTO  implements java.io.Serializable  {

	/*
	  파라미터 고유 ID (UUID)
	*/
	@Schema(description = "파라미터 고유 ID (UUID)" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=36)
	private String idx;
	
	/*
	  고유키
	*/
	@Schema(description = "고유키" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=50)
	private String key;
	
	/*
	  파라미터 명칭
	*/
	@Schema(description = "파라미터 명칭" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=100)
	private String name;
	
	/*
	  파라미터 값
	*/
	@Schema(description = "파라미터 값"  )
	@Size(max=200)
	private String value;
	
	/*
	  연결된 차량 ID
	*/
	@Schema(description = "연결된 차량 ID" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=36)
	private String carId;
	
	/*
	  정렬 순서
	*/
	@Schema(description = "정렬 순서"  )
	@Size(max=5)
	private Integer sortOrder;
	

}

//ecd:-466984947H20250411151033_V1.0
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
import io.elasticore.blueprint.domain.parts.entity.*;


/**
 * CarInfoSrchDTO
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class CarInfoSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  차량 ID (UUID)
	*/
	@Schema(description = "차량 ID (UUID) Use 'like' if value has %, else 'equal' field:id"  )
	private String id;
	
	/*
	  차량 이름 또는 사용자 정의명
	*/
	@Schema(description = "차량 이름 또는 사용자 정의명 Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	/*
	  차량 상세 설명
	*/
	@Schema(description = "차량 상세 설명 Use 'like' if value has %, else 'equal' field:description"  )
	private String description;
	
	/*
	  연결된 모델 ID
	*/
	@Schema(description = "연결된 모델 ID Use 'like' if value has %, else 'equal' field:modelId"  )
	private String modelId;
	
	/*
	  모델 이름 (조회용)
	*/
	@Schema(description = "모델 이름 (조회용) Use 'like' if value has %, else 'equal' field:modelName"  )
	private String modelName;
	
	/*
	  차량 식별 번호 (Vehicle Identification Number)
	*/
	@Schema(description = "차량 식별 번호 (Vehicle Identification Number) Use 'like' if value has %, else 'equal' field:vin"  )
	private String vin;
	
	/*
	  차체 번호
	*/
	@Schema(description = "차체 번호 Use 'like' if value has %, else 'equal' field:frame"  )
	private String frame;
	
	/*
	  차량 등록 기준
	*/
	@Schema(description = "차량 등록 기준 Use 'like' if value has %, else 'equal' field:criteria"  )
	private String criteria;
	
	/*
	  차량 브랜드명
	*/
	@Schema(description = "차량 브랜드명 Use 'like' if value has %, else 'equal' field:brand"  )
	private String brand;
	
	/*
	  부품 그룹 트리 지원 여부
	*/
	@Schema(description = "부품 그룹 트리 지원 여부 Field equals value. field:groupTreeAvailables"  )
	private Boolean groupTreeAvailables;
	
	/*
	  파라미터
	*/
	@Schema(description = "파라미터 Field equals value. field:parameters"  )
	private ParamInfo parametersItem;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

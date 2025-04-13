//ecd:-1852833925H20250412203017_V1.0
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



/**
 * ParamInfoSrchDTO
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
public  class ParamInfoSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  파라미터 고유 ID (UUID)
	*/
	@Schema(description = "파라미터 고유 ID (UUID) Use 'like' if value has %, else 'equal' field:idx"  )
	private String idx;
	
	/*
	  고유키
	*/
	@Schema(description = "고유키 Use 'like' if value has %, else 'equal' field:key"  )
	private String key;
	
	/*
	  파라미터 명칭
	*/
	@Schema(description = "파라미터 명칭 Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	/*
	  파라미터 값
	*/
	@Schema(description = "파라미터 값 Use 'like' if value has %, else 'equal' field:value"  )
	private String value;
	
	/*
	  연결된 차량 ID
	*/
	@Schema(description = "연결된 차량 ID Use 'like' if value has %, else 'equal' field:carId"  )
	private String carId;
	
	/*
	  정렬 순서
	*/
	@Schema(description = "정렬 순서 Field equals value. field:sortOrder"  )
	private Integer sortOrder;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

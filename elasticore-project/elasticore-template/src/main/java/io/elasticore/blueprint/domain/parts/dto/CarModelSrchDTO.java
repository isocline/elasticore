//ecd:516754422H20250411111444_V1.0
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
 * CarModelSrchDTO
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
public  class CarModelSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Field equals value. field:catalogCatalogId"  )
	private String catalogCatalogId;
	
	/*
	  차량 모델 ID (UUID)
	*/
	@Schema(description = "차량 모델 ID (UUID) Use 'like' if value has %, else 'equal' field:id"  )
	private String id;
	
	/*
	  모델명
	*/
	@Schema(description = "모델명 Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	/*
	  모델 이미지 경로(URL 또는 파일명)
	*/
	@Schema(description = "모델 이미지 경로(URL 또는 파일명) Use 'like' if value has %, else 'equal' field:img"  )
	private String img;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

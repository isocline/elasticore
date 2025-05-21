//ecd:946170351H20250425124038_V1.0
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
 * PartGroupSrchDTO
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
public  class PartGroupSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  부품 그룹 ID
	*/
	@Schema(description = "부품 그룹 ID Use 'like' if value has %, else 'equal' field:id"  )
	private String id;
	
	/*
	  부품 그룹 이름
	*/
	@Schema(description = "부품 그룹 이름 Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	@Schema(description = "Field equals value. field:hasSubgroups"  )
	private Boolean hasSubgroups;
	
	/*
	  그룹 이미지
	*/
	@Schema(description = "그룹 이미지 Use 'like' if value has %, else 'equal' field:img"  )
	private String img;
	
	/*
	  그룹 설명
	*/
	@Schema(description = "그룹 설명 Use 'like' if value has %, else 'equal' field:description"  )
	private String description;
	
	/*
	  상위 그룹 ID
	*/
	@Schema(description = "상위 그룹 ID Use 'like' if value has %, else 'equal' field:parentId"  )
	private String parentId;
	
	/*
	  연결된 차량 ID
	*/
	@Schema(description = "연결된 차량 ID Use 'like' if value has %, else 'equal' field:carId"  )
	private String carId;
	
	/*
	  차량 등록 기준
	*/
	@Schema(description = "차량 등록 기준 Use 'like' if value has %, else 'equal' field:criteria"  )
	private String criteria;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:brand"  )
	private String brand;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:imgDescription"  )
	private String imgDescription;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

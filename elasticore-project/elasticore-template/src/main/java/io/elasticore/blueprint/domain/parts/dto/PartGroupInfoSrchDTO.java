//ecd:1402085068H20250422181249_V1.0
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
 * PartGroupInfoSrchDTO
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
public  class PartGroupInfoSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  차량 ID (UUID)
	*/
	@Schema(description = "차량 ID (UUID) Use 'like' if value has %, else 'equal' field:carId"  )
	private String carId;
	
	/*
	  부품 그룹 ID
	*/
	@Schema(description = "부품 그룹 ID Use 'like' if value has %, else 'equal' field:groupId"  )
	private String groupId;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:img"  )
	private String img;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:imgDescription"  )
	private String imgDescription;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:brand"  )
	private String brand;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

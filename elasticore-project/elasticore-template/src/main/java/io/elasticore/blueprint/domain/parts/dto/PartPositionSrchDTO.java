//ecd:-483361535H20250422165427_V1.0
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
 * PartPositionSrchDTO
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
public  class PartPositionSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  groupId
	*/
	@Schema(description = "groupId Use 'like' if value has %, else 'equal' field:id"  )
	private String id;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:number"  )
	private String number;
	
	@Schema(description = "Field equals value. field:coordinates"  )
	private Integer[] coordinates;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

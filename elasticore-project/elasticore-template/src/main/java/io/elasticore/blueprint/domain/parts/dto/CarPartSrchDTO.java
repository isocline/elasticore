//ecd:998722854H20250422113010_V1.0
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
 * CarPartSrchDTO
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
public  class CarPartSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:id"  )
	private String id;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:number"  )
	private String number;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:nameId"  )
	private String nameId;
	
	/*
	  Part name
	*/
	@Schema(description = "Part name Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	/*
	  notice
	*/
	@Schema(description = "notice Use 'like' if value has %, else 'equal' field:notice"  )
	private String notice;
	
	/*
	  그룹 설명
	*/
	@Schema(description = "그룹 설명 Use 'like' if value has %, else 'equal' field:description"  )
	private String description;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:positionNumber"  )
	private String positionNumber;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:url"  )
	private String url;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

//ecd:-1177667296H20250411111444_V1.0
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
 * CatalogSrchDTO
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
public  class CatalogSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  고유 카탈로그 식별자
	*/
	@Schema(description = "고유 카탈로그 식별자 Use 'like' if value has %, else 'equal' field:catalogId"  )
	private String catalogId;
	
	/*
	  카탈로그 이름
	*/
	@Schema(description = "카탈로그 이름 Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

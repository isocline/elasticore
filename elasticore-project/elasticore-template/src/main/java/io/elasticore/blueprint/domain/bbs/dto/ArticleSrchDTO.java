//ecd:-481984770H20250409124907_V1.0
package io.elasticore.blueprint.domain.bbs.dto;

import io.elasticore.blueprint.domain.bbs.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import io.elasticore.blueprint.domain.bbs.enums.*;


/**
 * ArticleSrchDTO
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
public  class ArticleSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  게시판 타입
	*/
	@Schema(description = "게시판 타입 Field matches any value in the given list. field:boardType"  , example="PB: 공개 | PV: 개인")
	private List<BoardType> boardType;
	
	@Schema(description = "Field is between two values (inclusive). field:createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDateFrom;
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDateTo;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=50;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

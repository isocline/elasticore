//ecd:2137760533H20250409143721_V1.0
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
import io.elasticore.blueprint.domain.bbs.entity.*;


/**
 * BoardSrchDTO
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
public  class BoardSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  게시판 타입
	*/
	@Schema(description = "게시판 타입 Field equals value. field:boardType"  , example="PB: 공개 | PV: 개인")
	private BoardType boardType;
	
	/*
	  게시판 아이디
	*/
	@Schema(description = "게시판 아이디 Field equals value. field:bid"  )
	private Long bid;
	
	/*
	  게시판 명
	*/
	@Schema(description = "게시판 명 Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	@Schema(description = "Field equals value. field:articles"  )
	private Article articlesItem;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:lastModifiedBy"  )
	private String lastModifiedBy;
	
	@Schema(description = "Field is between two values (inclusive). field:lastModifiedDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDateFrom;
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDateTo;
	
	/*
	  시스템 입력 IP
	*/
	@Schema(description = "시스템 입력 IP Use 'like' if value has %, else 'equal' field:createIP"  )
	private String createIP;
	
	/*
	  시스템 수정 IP
	*/
	@Schema(description = "시스템 수정 IP Use 'like' if value has %, else 'equal' field:lastModifiedIP"  )
	private String lastModifiedIP;
	
	@Schema(description = "Field is between two values (inclusive). field:createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDateFrom;
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDateTo;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:createdBy"  )
	private String createdBy;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=50;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

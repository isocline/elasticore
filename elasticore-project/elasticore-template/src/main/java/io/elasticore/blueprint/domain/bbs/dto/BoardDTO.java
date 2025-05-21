//ecd:-1136327321H20250519113437_V1.0
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
import io.elasticore.blueprint.domain.bbs.dto.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * BoardDTO
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class BoardDTO  implements java.io.Serializable  {

	/*
	  게시판 타입
	*/
	@Schema(description = "게시판 타입"  , example="PB: 공개 | PV: 개인")
	private BoardType boardType;
	
	@Schema(description = "articles"  )
	private List<ArticleDTO> articles;
	
	/*
	  게시판 아이디
	*/
	@Schema(description = "게시판 아이디"  )
	private Long bid;
	
	/*
	  게시판 명
	*/
	@Schema(description = "게시판 명"  )
	private String name;
	
	/*
	  테스트
	*/
	@Schema(description = "테스트"  , example="PB: 공개 | PV: 개인")
	private List<BoardType> boardTypeList;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDate;
	
	/*
	  시스템 입력 IP
	*/
	@Schema(description = "시스템 입력 IP"  )
	@Size(max=20)
	private String createIP;
	
	/*
	  시스템 수정 IP
	*/
	@Schema(description = "시스템 수정 IP"  )
	@Size(max=20)
	private String lastModifiedIP;
	
	@Schema(description = "createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	

}

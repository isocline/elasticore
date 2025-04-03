//ecd:-1597130133H20250402174055_V1.0
package com.xyrokorea.xyroplug.domain.channel.dto;

import com.xyrokorea.xyroplug.domain.channel.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.xyrokorea.xyroplug.domain.channel.dto.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class ArticleDTO  implements java.io.Serializable  {

	@Schema(description = "board"  )
	private BoardDTO board;
	
	@Schema(description = "boardId"  )
	private Long boardId;
	
	/*
	  아이디
	*/
	@Schema(description = "아이디"  )
	private Long id;
	
	@Schema(description = "title" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=512)
	private String title;
	
	/*
	  본문
	*/
	@Schema(description = "본문"  )
	private String content;
	
	/*
	  작성자
	*/
	@Schema(description = "작성자"  )
	private String writer;
	
	/*
	  읽은 수
	*/
	@Schema(description = "읽은 수"  )
	private Long readCount;
	
	/*
	  유저수
	*/
	@Schema(description = "유저수"  )
	private Integer userCount;
	
	/*
	  혼자글
	*/
	@Schema(description = "혼자글"  )
	private Boolean privateYN;
	

}

//ecd:698509937H20250403172325_V1.0
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



/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class ArticleSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Field equals value. field:boardId"  )
	private Long boardId;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:title"  )
	private String title;
	
	/*
	  본문
	*/
	@Schema(description = "본문 Use 'like' if value has %, else 'equal' field:content"  )
	private String content;
	
	/*
	  작성자
	*/
	@Schema(description = "작성자 Use 'like' if value has %, else 'equal' field:writer"  )
	private String writer;
	
	/*
	  읽은 수
	*/
	@Schema(description = "읽은 수 Field equals value. field:readCount"  )
	private Long readCount;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

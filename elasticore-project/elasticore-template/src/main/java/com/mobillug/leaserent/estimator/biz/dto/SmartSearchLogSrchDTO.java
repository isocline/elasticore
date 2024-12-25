//ecd:1364060645H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.dto;

import com.mobillug.leaserent.estimator.biz.enums.*;
import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class SmartSearchLogSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  로그 아이디
	*/
	@Schema(description = "로그 아이디 Field equals value. field:logId"  )
	private Long logId;
	
	/*
	  검색어
	*/
	@Schema(description = "검색어 Use 'like' if value has %, else 'equal' field:searchQuery"  )
	@Size(max=2000)
	private String searchQuery;
	
	/*
	  검색 결과
	*/
	@Schema(description = "검색 결과 Use 'like' if value has %, else 'equal' field:searchResult"  )
	private String searchResult;
	
	/*
	  수행 시간 (밀리세컨드)
	*/
	@Schema(description = "수행 시간 (밀리세컨드) Field equals value. field:executionTime"  )
	private Long executionTime;
	
	/*
	  응답 건수
	*/
	@Schema(description = "응답 건수 Field equals value. field:resultCount"  )
	private Integer resultCount;
	
	/*
	  생성일시
	*/
	@Schema(description = "생성일시 Field equals value. field:createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@org.springframework.data.annotation.CreatedDate
	private java.time.LocalDateTime createDate;
	
	/*
	  사용자
	*/
	@Schema(description = "사용자 Use 'like' if value has %, else 'equal' field:createdBy"  )
	@Size(max=20)
	@org.springframework.data.annotation.CreatedBy
	private String createdBy;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

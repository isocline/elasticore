//ecd:-1432235192H20241223210702_V1.0
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
public  class SmartSearchLogDTO  implements java.io.Serializable  {

	/*
	  로그 아이디
	*/
	@Schema(description = "로그 아이디"  )
	private Long logId;
	
	/*
	  검색어
	*/
	@Schema(description = "검색어"  )
	@Size(max=2000)
	private String searchQuery;
	
	/*
	  검색 결과
	*/
	@Schema(description = "검색 결과"  )
	private String searchResult;
	
	/*
	  수행 시간 (밀리세컨드)
	*/
	@Schema(description = "수행 시간 (밀리세컨드)"  )
	private Long executionTime;
	
	/*
	  응답 건수
	*/
	@Schema(description = "응답 건수"  )
	private Integer resultCount;
	
	/*
	  생성일시
	*/
	@Schema(description = "생성일시"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDate;
	
	/*
	  사용자
	*/
	@Schema(description = "사용자"  )
	@Size(max=20)
	private String createdBy;
	

}

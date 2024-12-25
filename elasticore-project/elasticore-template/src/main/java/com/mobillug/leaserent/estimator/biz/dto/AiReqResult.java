//ecd:795048839H20241223210702_V1.0
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
public  class AiReqResult  implements java.io.Serializable  {

	/*
	  차량관련 메세지
	*/
	@Schema(description = "차량관련 메세지"  )
	private String keyword;
	
	/*
	  추가 웹 검색이 필요한지 여부
	*/
	@Schema(description = "추가 웹 검색이 필요한지 여부"  )
	private Boolean isNeedSearch;
	
	/*
	  추가 처리 결과 메세지
	*/
	@Schema(description = "추가 처리 결과 메세지"  )
	private String extMsg;
	
	/*
	  확장 SQL 조건 구문
	*/
	@Schema(description = "확장 SQL 조건 구문"  )
	private String whereSql;
	
	/*
	  정렬 관련 정보
	*/
	@Schema(description = "정렬 관련 정보"  )
	private String sortSql;
	
	/*
	  최대 조회갯수
	*/
	@Schema(description = "최대 조회갯수"  )
	private Integer maxCount;
	

}

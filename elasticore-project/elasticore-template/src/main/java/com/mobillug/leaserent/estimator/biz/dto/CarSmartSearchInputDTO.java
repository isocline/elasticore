//ecd:-2014648218H20241223210702_V1.0
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
public  class CarSmartSearchInputDTO  implements java.io.Serializable  {

	/*
	  검색 기본 키워드 (차량명)
	*/
	@Schema(description = "검색 기본 키워드 (차량명)"  )
	private String keyword;
	
	/*
	  무심사인 경우:L
	*/
	@Schema(description = "무심사인 경우:L"  )
	private String mode;
	
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
	  최대 출력 갯수
	*/
	@Schema(description = "최대 출력 갯수"  )
	private Integer maxCount;
	

}

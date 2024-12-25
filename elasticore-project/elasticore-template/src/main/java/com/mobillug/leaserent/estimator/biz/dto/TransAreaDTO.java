//ecd:655126346H20241223210702_V1.0
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
public  class TransAreaDTO  implements java.io.Serializable  {

	@Schema(description = "seq"  )
	private Long seq;
	
	/*
	  그룹 아이디 (CM:공통, 또는 차량코드)
	*/
	@Schema(description = "그룹 아이디 (CM:공통, 또는 차량코드)"  )
	@Size(max=20)
	private String grpCd;
	
	/*
	  도시 이이디
	*/
	@Schema(description = "도시 이이디"  )
	private String cityIdx;
	
	/*
	  지역 아이디
	*/
	@Schema(description = "지역 아이디"  )
	private String areaIdx;
	
	/*
	  지역명
	*/
	@Schema(description = "지역명"  )
	private String name;
	

}

//ecd:140101232H20241206183853_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;


import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public  class BaseCarInfoDTO  implements java.io.Serializable  {

	@Schema(description = "brandId"  )
	@Size(max=12)
	private String brandId;
	
	@Schema(description = "carId"  )
	@Size(max=12)
	private String carId;
	
	/*
	  면세 여부 // 예
	*/
	@Schema(description = "면세 여부 // 예"  )
	private Boolean taxExempt;
	
	/*
	  차량 기본가
	*/
	@Schema(description = "차량 기본가"  )
	private Long carBasePrice;
	
	/*
	  36개월 예상 렌트료
	*/
	@Schema(description = "36개월 예상 렌트료"  )
	private Long payment36M;
	
	/*
	  48개월 예상 렌트료
	*/
	@Schema(description = "48개월 예상 렌트료"  )
	private Long payment48M;
	
	/*
	  60개월 예상 렌트료
	*/
	@Schema(description = "60개월 예상 렌트료"  )
	private Long payment60M;
	

}

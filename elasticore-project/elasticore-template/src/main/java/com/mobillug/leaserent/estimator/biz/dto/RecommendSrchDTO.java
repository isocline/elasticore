//ecd:-1262433647H20241223210702_V1.0
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
public  class RecommendSrchDTO  implements java.io.Serializable  {

	/*
	  월 렌트료 최저가
	*/
	@Schema(description = "월 렌트료 최저가"  )
	private Long paymentFrom;
	
	/*
	  월 렌트료 최고가
	*/
	@Schema(description = "월 렌트료 최고가"  )
	private Long paymentTo;
	
	@Schema(description = "estimateType"  )
	private String estimateType;
	

}

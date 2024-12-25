//ecd:2105207581H20241223210702_V1.0
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
public  class RentDetailsDTO  implements java.io.Serializable  {

	/*
	  선납금
	*/
	@Schema(description = "선납금"  )
	private Long advancedPaymentPrice;
	
	/*
	  보증금
	*/
	@Schema(description = "보증금"  )
	private Long depositPrice;
	
	/*
	  AG 수수료 퍼센트
	*/
	@Schema(description = "AG 수수료 퍼센트"  )
	private Float agFeePercent;
	
	/*
	  CM 수수료 퍼센트
	*/
	@Schema(description = "CM 수수료 퍼센트"  )
	private Float cmFeePercent;
	

}

//ecd:2038640447H20241223210702_V1.0
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
public  class EstimateTypeInfo  implements java.io.Serializable  {

	/*
	  무심사렌트 가능여부
	*/
	@Schema(description = "무심사렌트 가능여부"  )
	private Boolean ieEnableLowRent;
	
	/*
	  무심사리스 가능여부
	*/
	@Schema(description = "무심사리스 가능여부"  )
	private Boolean ieEnableLowLease;
	
	/*
	  일반렌트 가능여부
	*/
	@Schema(description = "일반렌트 가능여부"  )
	@Builder.Default
	private Boolean isEnableRent = true;
	
	/*
	  일반리스 가능여부
	*/
	@Schema(description = "일반리스 가능여부"  )
	@Builder.Default
	private Boolean isEnableLease = true;
	

}

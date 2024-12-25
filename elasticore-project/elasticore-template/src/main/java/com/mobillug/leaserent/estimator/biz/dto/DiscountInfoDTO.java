//ecd:-1045775994H20241223210702_V1.0
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
import com.mobillug.leaserent.estimator.biz.dto.*;


/**


*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class DiscountInfoDTO  implements java.io.Serializable  {

	@Schema(description = "baseCarInfo"  )
	private BaseCarInfoDTO baseCarInfo;
	
	@Schema(description = "baseCarInfoCarId"  )
	private String baseCarInfoCarId;
	
	/*
	  할인 금액(율)
	*/
	@Schema(description = "할인 금액(율)"  )
	private Long discountPrice;
	
	/*
	  할인율
	*/
	@Schema(description = "할인율"  )
	private Float discountRate;
	
	/*
	  옵션명
	*/
	@Schema(description = "옵션명"  )
	private String optName;
	

}

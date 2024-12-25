//ecd:401188688H20241223210702_V1.0
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
public  class CarSmartSearchListDTO  implements java.io.Serializable  {

	@Schema(description = "results"  )
	private List<CarInfoSrchResultDTO> results;
	
	@Schema(description = "msg"  )
	private String msg;
	
	@Schema(description = "query"  )
	private CarSmartSearchInputDTO query;
	

}

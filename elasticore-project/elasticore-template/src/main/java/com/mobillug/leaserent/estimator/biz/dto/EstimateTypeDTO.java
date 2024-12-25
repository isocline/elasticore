//ecd:-1759132452H20241223210702_V1.0
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
public  class EstimateTypeDTO  implements java.io.Serializable  {

	@Schema(description = "baseCarId"  )
	private String baseCarId;
	
	@Schema(description = "lineupInfoId"  )
	private String lineupInfoId;
	
	@Schema(description = "seriesInfoId"  )
	private String seriesInfoId;
	
	@Schema(description = "brandInfoId"  )
	private String brandInfoId;
	
	@Schema(description = "estimateType"  )
	private String estimateType;
	

}

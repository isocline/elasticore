//ecd:-496341394H20241223210702_V1.0
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
public  class CarFullNameInfoDTO  implements java.io.Serializable  {

	@Schema(description = "brandInfoId"  )
	private String brandInfoId;
	
	@Schema(description = "brandName"  )
	private String brandName;
	
	@Schema(description = "brandImgUrl"  )
	private String brandImgUrl;
	
	@Schema(description = "seriesInfoId"  )
	private String seriesInfoId;
	
	@Schema(description = "seriesName"  )
	private String seriesName;
	
	@Schema(description = "seriesImgUrl"  )
	private String seriesImgUrl;
	
	@Schema(description = "lineupInfoId"  )
	private String lineupInfoId;
	
	@Schema(description = "lineupName"  )
	private String lineupName;
	
	@Schema(description = "carId"  )
	private String carId;
	
	@Schema(description = "carName"  )
	private String carName;
	

}

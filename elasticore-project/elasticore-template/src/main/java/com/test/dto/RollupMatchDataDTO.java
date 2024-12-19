//ecd:-1365682507H20241219173128_V1.0
package com.test.dto;


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
public  class RollupMatchDataDTO  implements java.io.Serializable  {

	@Schema(description = "failList"  )
	@Builder.Default
	private List<java.util.Map<String,Object>> failList = new ArrayList<>();
	
	@Schema(description = "successList"  )
	@Builder.Default
	private List<com.mobillug.leaserent.estimator.formula.entity.BaseFormulaInfo> successList = new ArrayList<>();
	
	@Schema(description = "baseCarNewList"  )
	@Builder.Default
	private List<com.mobillug.leaserent.estimator.biz.entity.BaseCarInfo> baseCarNewList = new ArrayList<>();
	
	@Schema(description = "brandMap"  )
	@Builder.Default
	private java.util.HashMap<String,com.mobillug.leaserent.estimator.biz.dto.BrandInfoDTO> brandMap = new HashMap<>();
	
	@Schema(description = "seriesMap"  )
	@Builder.Default
	private java.util.HashMap<String,List<com.mobillug.leaserent.estimator.biz.dto.SeriesInfoDTO>> seriesMap = new HashMap<>();
	
	@Schema(description = "carModelMap"  )
	@Builder.Default
	private java.util.HashMap<String,List<CarModelDTO>> carModelMap = new HashMap<>();
	

}

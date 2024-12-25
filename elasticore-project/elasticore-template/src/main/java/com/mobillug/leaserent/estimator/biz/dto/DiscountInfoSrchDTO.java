//ecd:-1998375611H20241223210702_V1.0
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
public  class DiscountInfoSrchDTO  implements java.io.Serializable, SortableObject  {

	@Schema(description = "Field equals value. field:baseCarInfoCarId"  )
	private String baseCarInfoCarId;
	
	/*
	  할인 금액(율)
	*/
	@Schema(description = "할인 금액(율) Field equals value. field:discountPrice"  )
	private Long discountPrice;
	
	/*
	  할인율
	*/
	@Schema(description = "할인율 Field equals value. field:discountRate"  )
	private Float discountRate;
	
	/*
	  옵션명
	*/
	@Schema(description = "옵션명 Use 'like' if value has %, else 'equal' field:optName"  )
	private String optName;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

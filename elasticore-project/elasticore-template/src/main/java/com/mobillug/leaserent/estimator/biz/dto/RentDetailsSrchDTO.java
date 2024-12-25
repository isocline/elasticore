//ecd:-368721921H20241223210702_V1.0
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
public  class RentDetailsSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  선납금
	*/
	@Schema(description = "선납금 Field equals value. field:advancedPaymentPrice"  )
	private Long advancedPaymentPrice;
	
	/*
	  보증금
	*/
	@Schema(description = "보증금 Field equals value. field:depositPrice"  )
	private Long depositPrice;
	
	/*
	  AG 수수료 퍼센트
	*/
	@Schema(description = "AG 수수료 퍼센트 Field equals value. field:agFeePercent"  )
	private Float agFeePercent;
	
	/*
	  CM 수수료 퍼센트
	*/
	@Schema(description = "CM 수수료 퍼센트 Field equals value. field:cmFeePercent"  )
	private Float cmFeePercent;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

//ecd:-1520835491H20241223210702_V1.0
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
public  class CustomerInfoSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  고객 이름
	*/
	@Schema(description = "고객 이름 Use 'like' if value has %, else 'equal' field:customerName"  )
	@Size(max=20)
	private String customerName;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

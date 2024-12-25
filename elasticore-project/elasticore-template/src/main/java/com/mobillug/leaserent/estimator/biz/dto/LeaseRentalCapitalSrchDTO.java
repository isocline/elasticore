//ecd:1024000081H20241223210702_V1.0
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
public  class LeaseRentalCapitalSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  캐피탈 아이디
	*/
	@Schema(description = "캐피탈 아이디 Use 'like' if value has %, else 'equal' field:id"  )
	@Size(max=12)
	private String id;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	/*
	  차량 정보 적용일시 예
	*/
	@Schema(description = "차량 정보 적용일시 예 Field is less than or equal to value. field:effectiveDate"  )
	@Size(max=8)
	private String effectiveDate;
	
	/*
	  차량 정보 종료일시,null 또 공백시 설정 안됨. 예
	*/
	@Schema(description = "차량 정보 종료일시,null 또 공백시 설정 안됨. 예 Field is greater than value. field:endDate"  )
	@Size(max=8)
	private String endDate;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

//ecd:245655101H20241223210702_V1.0
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
public  class CarOptionSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  순번 아이디
	*/
	@Schema(description = "순번 아이디 Use 'like' if value has %, else 'equal' field:id"  )
	@Size(max=12)
	private String id;
	
	/*
	  옵션명 // 예
	*/
	@Schema(description = "옵션명 // 예 Use 'like' if value has %, else 'equal' field:optionName"  )
	@Size(max=500)
	private String optionName;
	
	/*
	  옵션 코드 // 예
	*/
	@Schema(description = "옵션 코드 // 예 Use 'like' if value has %, else 'equal' field:optionCode"  )
	@Size(max=10)
	private String optionCode;
	
	/*
	  옵션 가격
	*/
	@Schema(description = "옵션 가격 Field equals value. field:optionPrice"  )
	private Long optionPrice;
	
	/*
	  필요 옵션 // 예
	*/
	@Schema(description = "필요 옵션 // 예 Field equals value. field:requiredOption"  )
	@Size(max=4000)
	private String[] requiredOption;
	
	/*
	  옵션 설명
	*/
	@Schema(description = "옵션 설명 Use 'like' if value has %, else 'equal' field:optionDescription"  )
	private String optionDescription;
	
	/*
	  중복 불가 옵션 // 예
	*/
	@Schema(description = "중복 불가 옵션 // 예 Field equals value. field:duplicatedOptionCode"  )
	@Size(max=4000)
	private String[] duplicatedOptionCode;
	
	/*
	  차량 모델코드
	*/
	@Schema(description = "차량 모델코드 Use 'like' if value has %, else 'equal' field:carModelCode"  )
	@Size(max=10)
	private String carModelCode;
	
	/*
	  옵션 구분 // 예
	*/
	@Schema(description = "옵션 구분 // 예 Field equals value. field:isTuneAcc"  )
	private Boolean isTuneAcc;
	
	/*
	  temp
	*/
	@Schema(description = "temp Use 'like' if value has %, else 'equal' field:restriction"  )
	private String restriction;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

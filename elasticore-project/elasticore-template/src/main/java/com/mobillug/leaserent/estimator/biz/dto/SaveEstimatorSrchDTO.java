//ecd:1628564779H20241223210702_V1.0
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
import com.mobillug.leaserent.estimator.biz.enums.*;


/**


*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class SaveEstimatorSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Field equals value. field:carInfoCarId"  )
	private String carInfoCarId;
	
	@Schema(description = "Field equals value. field:innerColorId"  )
	private String innerColorId;
	
	@Schema(description = "Field equals value. field:outsideColorId"  )
	private String outsideColorId;
	
	/*
	  순번 아이디
	*/
	@Schema(description = "순번 아이디 Use 'like' if value has %, else 'equal' field:id"  )
	@Size(max=12)
	private String id;
	
	/*
	  기본 차량가격 (+색상)
	*/
	@Schema(description = "기본 차량가격 (+색상) Field equals value. field:baseCarPrice"  )
	private Long baseCarPrice;
	
	/*
	  옵션가격
	*/
	@Schema(description = "옵션가격 Field equals value. field:optionPrice"  )
	private Long optionPrice;
	
	/*
	  선택 car ID
	*/
	@Schema(description = "선택 car ID Use 'like' if value has %, else 'equal' field:carId"  )
	private String carId;
	
	/*
	  내장 색상명칭
	*/
	@Schema(description = "내장 색상명칭 Use 'like' if value has %, else 'equal' field:innerColorName"  )
	@Size(max=100)
	private String innerColorName;
	
	/*
	  추가 옵션 , ex
	*/
	@Schema(description = "추가 옵션 , ex Field equals value. field:extraOpts"  , example="TT: \"썬팅\" | BB: \"블랙박스\"")
	private SaleExtraOption extraOptsItem;
	
	/*
	  탁송비
	*/
	@Schema(description = "탁송비 Field equals value. field:consignmentPrice"  )
	private Long consignmentPrice;
	
	/*
	  메모
	*/
	@Schema(description = "메모 Use 'like' if value has %, else 'equal' field:memo"  )
	@Size(max=150)
	private String memo;
	
	/*
	  견적분류  무심사렌트 LR / 무심사리스 LL / 일반렌트 RT  / 일반리스 LS
	*/
	@Schema(description = "견적분류  무심사렌트 LR / 무심사리스 LL / 일반렌트 RT  / 일반리스 LS Use 'like' if value has %, else 'equal' field:estimateType"  )
	@Size(max=2)
	private String estimateType;
	
	/*
	  월 렌트료
	*/
	@Schema(description = "월 렌트료 Field equals value. field:monthRentalPrice"  )
	private Long monthRentalPrice;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

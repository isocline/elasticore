//ecd:-1453563092H20241223210702_V1.0
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
public  class RcmdCarInfoSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  선택 내부 색상
	*/
	@Schema(description = "선택 내부 색상 Field equals value. field:innerColorId"  )
	private String innerColorId;
	
	/*
	  선택 외부 색상
	*/
	@Schema(description = "선택 외부 색상 Field equals value. field:outsideColorId"  )
	private String outsideColorId;
	
	@Schema(description = ""  )
	private List<CarOptionDTO> optionList;
	
	/*
	  렌트 R /리스 L /렌트리스 RL
	*/
	@Schema(description = "렌트 R /리스 L /렌트리스 RL Field equals value. field:estimateType"  )
	@Size(max=2)
	private String estimateType;
	
	/*
	  선택 car ID
	*/
	@Schema(description = "선택 car ID Field equals value. field:carId"  )
	private String carId;
	
	/*
	  36개월 할부 월 렌트료
	*/
	@Schema(description = "36개월 할부 월 렌트료 Field is between two values (inclusive). field:payment"  )
	private Long paymentFrom;
	
	private Long paymentTo;
	
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

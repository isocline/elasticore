//ecd:-2076159193H20241223210702_V1.0
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
public  class CarOptionDTO  implements java.io.Serializable  {

	/*
	  순번 아이디
	*/
	@Schema(description = "순번 아이디"  )
	@Size(max=12)
	private String id;
	
	/*
	  옵션명 // 예
	*/
	@Schema(description = "옵션명 // 예"  )
	@Size(max=500)
	private String optionName;
	
	/*
	  옵션 코드 // 예
	*/
	@Schema(description = "옵션 코드 // 예"  )
	@Size(max=10)
	private String optionCode;
	
	/*
	  옵션 가격
	*/
	@Schema(description = "옵션 가격"  )
	private Long optionPrice;
	
	/*
	  필요 옵션 // 예
	*/
	@Schema(description = "필요 옵션 // 예"  )
	@Size(max=4000)
	private String[] requiredOption;
	
	/*
	  옵션 설명
	*/
	@Schema(description = "옵션 설명"  )
	private String optionDescription;
	
	/*
	  중복 불가 옵션 // 예
	*/
	@Schema(description = "중복 불가 옵션 // 예"  )
	@Size(max=4000)
	private String[] duplicatedOptionCode;
	
	/*
	  차량 모델코드
	*/
	@Schema(description = "차량 모델코드"  )
	@Size(max=10)
	private String carModelCode;
	
	/*
	  옵션 구분 // 예
	*/
	@Schema(description = "옵션 구분 // 예"  )
	private Boolean isTuneAcc;
	
	/*
	  temp
	*/
	@Schema(description = "temp"  )
	private String restriction;
	

}

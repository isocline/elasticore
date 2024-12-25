//ecd:-546087234H20241223210702_V1.0
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
import com.mobillug.leaserent.estimator.biz.enums.*;


/**


*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class SaveEstimatorDTO  implements java.io.Serializable  {

	/*
	  옵션정보
	*/
	@Schema(description = "옵션정보"  )
	private List<CarOptionDTO> optionList;
	
	/*
	  내부 외부 색상
	*/
	@Schema(description = "내부 외부 색상"  )
	private ColorInfoDTO innerColor;
	
	/*
	  선택 외부 색상
	*/
	@Schema(description = "선택 외부 색상"  )
	private ColorInfoDTO outsideColor;
	
	/*
	  차량 상위 상세 정보
	*/
	@Schema(description = "차량 상위 상세 정보"  )
	private CarFullNameInfoDTO carMetaInfo;
	
	@Schema(description = "customerInfo"  )
	private CustomerInfoDTO customerInfo;
	
	/*
	  순번 아이디
	*/
	@Schema(description = "순번 아이디"  )
	@Size(max=12)
	private String id;
	
	/*
	  기본 차량가격 (+색상)
	*/
	@Schema(description = "기본 차량가격 (+색상)"  )
	private Long baseCarPrice;
	
	/*
	  옵션가격
	*/
	@Schema(description = "옵션가격"  )
	private Long optionPrice;
	
	/*
	  선택 car ID
	*/
	@Schema(description = "선택 car ID"  )
	private String carId;
	
	/*
	  내장 색상명칭
	*/
	@Schema(description = "내장 색상명칭"  )
	@Size(max=100)
	private String innerColorName;
	
	/*
	  추가 옵션 , ex
	*/
	@Schema(description = "추가 옵션 , ex"  , example="TT: \"썬팅\" | BB: \"블랙박스\"")
	private List<SaleExtraOption> extraOpts;
	
	/*
	  탁송비
	*/
	@Schema(description = "탁송비"  )
	private Long consignmentPrice;
	
	/*
	  메모
	*/
	@Schema(description = "메모"  )
	@Size(max=150)
	private String memo;
	
	/*
	  견적분류  무심사렌트 LR / 무심사리스 LL / 일반렌트 RT  / 일반리스 LS
	*/
	@Schema(description = "견적분류  무심사렌트 LR / 무심사리스 LL / 일반렌트 RT  / 일반리스 LS"  )
	@Size(max=2)
	private String estimateType;
	
	/*
	  월 렌트료
	*/
	@Schema(description = "월 렌트료"  )
	private Long monthRentalPrice;
	
	@Schema(description = "createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDate;
	

}

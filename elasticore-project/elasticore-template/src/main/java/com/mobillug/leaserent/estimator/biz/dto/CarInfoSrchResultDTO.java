//ecd:1808058995H20241223210702_V1.0
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
public  class CarInfoSrchResultDTO  implements java.io.Serializable  {

	@Schema(description = "carId"  )
	@Size(max=12)
	private String carId;
	
	/*
	  차량 기본가
	*/
	@Schema(description = "차량 기본가"  )
	private Long carBasePrice;
	
	/*
	  차량 풀네임
	*/
	@Schema(description = "차량 풀네임" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=500)
	private String carFullName;
	
	/*
	  차량 모델코드
	*/
	@Schema(description = "차량 모델코드"  )
	@Size(max=10)
	private String carModelCode;
	
	/*
	  차량 타입
	*/
	@Schema(description = "차량 타입"  )
	private String classifyName;
	
	/*
	  배기량
	*/
	@Schema(description = "배기량"  )
	private Integer displacement;
	
	/*
	  연비
	*/
	@Schema(description = "연비"  )
	private Float efficiency;
	
	/*
	  연료타입
	*/
	@Schema(description = "연료타입"  )
	private String fuelName;
	
	/*
	  인승
	*/
	@Schema(description = "인승"  )
	private Integer seater;
	
	/*
	  면세 여부
	*/
	@Schema(description = "면세 여부"  )
	private Boolean taxExempt;
	
	/*
	  트림
	*/
	@Schema(description = "트림"  )
	@Size(max=100)
	private String trim;
	
	/*
	  공차중량
	*/
	@Schema(description = "공차중량"  )
	private Integer curbWeight;
	
	/*
	  최고출력
	*/
	@Schema(description = "최고출력"  )
	private Integer highOutput;
	
	/*
	  최대토크
	*/
	@Schema(description = "최대토크"  )
	private Float maxTorque;
	
	/*
	  전고
	*/
	@Schema(description = "전고"  )
	private Integer overallHeight;
	
	/*
	  전장
	*/
	@Schema(description = "전장"  )
	private Integer overallLength;
	
	/*
	  전폭
	*/
	@Schema(description = "전폭"  )
	private Integer overallWidth;
	
	/*
	  전륜 윤거
	*/
	@Schema(description = "전륜 윤거"  )
	private Integer treadFront;
	
	/*
	  후륜 윤거
	*/
	@Schema(description = "후륜 윤거"  )
	private Integer treadRear;
	
	/*
	  휠베이스
	*/
	@Schema(description = "휠베이스"  )
	private Integer wheelbase;
	
	/*
	  36개월 예상 렌트료 (기본 렌트료)
	*/
	@Schema(description = "36개월 예상 렌트료 (기본 렌트료)"  )
	private Long payment36m;
	
	/*
	  48개월 예상 렌트료
	*/
	@Schema(description = "48개월 예상 렌트료"  )
	private Long payment48m;
	
	/*
	  60개월 예상 렌트료
	*/
	@Schema(description = "60개월 예상 렌트료"  )
	private Long payment60m;
	
	/*
	  즉축 가능 재고량
	*/
	@Schema(description = "즉축 가능 재고량"  )
	private String totalStockQuantity;
	
	@Schema(description = "recommendTypes"  )
	private String recommendTypes;
	
	@Schema(description = "estimateTypes"  )
	private String estimateTypes;
	
	/*
	  죽시출고되는 차량이 있는 캐피탈사의 ID 목록이 들어간다. 여러 캐피탈사 정보가 설정되는 경우 공백으로 설정
	*/
	@Schema(description = "죽시출고되는 차량이 있는 캐피탈사의 ID 목록이 들어간다. 여러 캐피탈사 정보가 설정되는 경우 공백으로 설정"  )
	private String capitalIds;
	
	/*
	  죽시출고되는 차량이 있는 캐피탈사의 이름 목록이 들어간다. 여러 캐피탈사 정보가 설정되는 경우 공백으로 설정
	*/
	@Schema(description = "죽시출고되는 차량이 있는 캐피탈사의 이름 목록이 들어간다. 여러 캐피탈사 정보가 설정되는 경우 공백으로 설정"  )
	private String capitalNames;
	
	/*
	  차량 상위 상세 정보
	*/
	@Schema(description = "차량 상위 상세 정보"  )
	private CarFullNameInfoDTO carMetaInfo;
	
	@Schema(description = "estimateType"  )
	private EstimateTypeInfo estimateType;
	
	public EstimateTypeInfo getEstimateType() {
	    return com.mobillug.leaserent.estimator.common.utils.DTOUtils.getEstimateTypeInfo(carId);
	}
	

}

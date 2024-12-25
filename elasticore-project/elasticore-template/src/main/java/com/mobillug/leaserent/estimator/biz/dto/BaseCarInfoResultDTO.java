//ecd:1751502870H20241223210702_V1.0
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
public  class BaseCarInfoResultDTO  implements java.io.Serializable  {

	@Schema(description = "estimateType"  )
	private EstimateTypeInfo estimateType;
	
	public EstimateTypeInfo getEstimateType() {
	    return com.mobillug.leaserent.estimator.common.utils.DTOUtils.getEstimateTypeInfo(carId);
	}
	
	/*
	  차량 아이디
	*/
	@Schema(description = "차량 아이디"  )
	@Size(max=12)
	private String carId;
	
	/*
	  rollup 대응 discriminator 타입정보
	*/
	@Schema(description = "rollup 대응 discriminator 타입정보"  )
	private String type;
	
	/*
	  면세 여부 // 예
	*/
	@Schema(description = "면세 여부 // 예"  )
	private Boolean taxExempt;
	
	/*
	  차량 기본가
	*/
	@Schema(description = "차량 기본가"  )
	private Long carBasePrice;
	
	/*
	  인승
	*/
	@Schema(description = "인승"  )
	@Builder.Default
	private Integer seater = 0;
	
	/*
	  트림 // 예
	*/
	@Schema(description = "트림 // 예"  )
	@Size(max=100)
	private String trim;
	
	/*
	  차량 풀네임 // 예
	*/
	@Schema(description = "차량 풀네임 // 예" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=500)
	private String carFullName;
	
	/*
	  국가코드
	*/
	@Schema(description = "국가코드"  )
	@Size(max=2)
	private String nation;
	
	/*
	  차량 모델코드 // 예
	*/
	@Schema(description = "차량 모델코드 // 예"  )
	@Size(max=10)
	private String carModelCode;
	
	/*
	  배기량
	*/
	@Schema(description = "배기량"  )
	@Builder.Default
	private Integer displacement = 0;
	
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
	  차량 타입
	*/
	@Schema(description = "차량 타입"  )
	private String classifyName;
	
	/*
	  연료타입
	*/
	@Schema(description = "연료타입"  , example="HY: 하이브리드 | EL: 전기 | HD: 수소 | GA: 가솔린 | LP: LPG | DS: 디젤")
	private FuelType fuelType;
	
	/*
	  차량타입
	*/
	@Schema(description = "차량타입"  , example="TPV: 화물 (트럭/픽업+밴) | SMD: 준중형 | LSV: 대형SUV | SPT: 스포츠카/슈퍼카 | VAN: 승합 (박스카/승합/버스) | LRG: 대형 | SLG: 준대형 | SSV: 소형SUV | MED: 중형 | MSV: 중형SUV | SML: 소형 | CMP: 경차/경박스카 | MPV: MPV")
	private CarClassType carClassType;
	
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
	  전고
	*/
	@Schema(description = "전고"  )
	private Integer overallHeight;
	
	/*
	  휠베이스
	*/
	@Schema(description = "휠베이스"  )
	private Integer wheelbase;
	
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
	  36개월 예상 렌트료
	*/
	@Schema(description = "36개월 예상 렌트료"  )
	private Long payment36M;
	
	/*
	  48개월 예상 렌트료
	*/
	@Schema(description = "48개월 예상 렌트료"  )
	private Long payment48M;
	
	/*
	  60개월 예상 렌트료
	*/
	@Schema(description = "60개월 예상 렌트료"  )
	private Long payment60M;
	
	/*
	  차량 정보 적용일시 예
	*/
	@Schema(description = "차량 정보 적용일시 예"  )
	@Size(max=8)
	private String effectiveDate;
	
	/*
	  차량 정보 종료일시,null 또 공백시 설정 안됨. 예
	*/
	@Schema(description = "차량 정보 종료일시,null 또 공백시 설정 안됨. 예"  )
	@Size(max=8)
	@Builder.Default
	private String endDate = "99991231";
	
	/*
	  트랜잭션 아이디
	*/
	@Schema(description = "트랜잭션 아이디"  )
	@Size(max=12)
	private String txId;
	
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

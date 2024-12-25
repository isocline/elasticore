//ecd:-908024900H20241223210702_V1.0
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
public  class RcmdCarInfoDTO  implements java.io.Serializable  {

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
	
	@Schema(description = "outsideColor"  )
	private ColorInfoDTO outsideColor;
	
	/*
	  캐피탈
	*/
	@Schema(description = "캐피탈"  )
	private LeaseRentalCapitalDTO capital;
	
	/*
	  순번 아이디
	*/
	@Schema(description = "순번 아이디"  )
	@Size(max=12)
	private String id;
	
	/*
	  차량 정보 타입 (추천, 즉시출고)
	*/
	@Schema(description = "차량 정보 타입 (추천, 즉시출고)"  , example="RM: 추천차량 | IS: 즉시출고")
	private RecommendType recommendType;
	
	/*
	  렌트 R /리스 L /렌트리스 RL
	*/
	@Schema(description = "렌트 R /리스 L /렌트리스 RL"  )
	@Size(max=2)
	private String estimateType;
	
	/*
	  선택 car ID
	*/
	@Schema(description = "선택 car ID"  )
	private String carId;
	
	/*
	  기본 차량가격
	*/
	@Schema(description = "기본 차량가격"  )
	private Long baseCarPrice;
	
	/*
	  탁송비
	*/
	@Schema(description = "탁송비"  )
	private Long consignmentPrice;
	
	/*
	  36개월 할부 월 렌트료
	*/
	@Schema(description = "36개월 할부 월 렌트료"  )
	private Long payment;
	
	/*
	  추가 부연 설명
	*/
	@Schema(description = "추가 부연 설명"  )
	private String extraDesc;
	
	/*
	  만기 인수가
	*/
	@Schema(description = "만기 인수가"  )
	private Long takeover;
	
	/*
	  총비용
	*/
	@Schema(description = "총비용"  )
	private Long totalAmount;
	
	/*
	  약정거리
	*/
	@Schema(description = "약정거리"  )
	private Long distance;
	
	/*
	  취득원가
	*/
	@Schema(description = "취득원가"  )
	private Long won;
	
	/*
	  보증금
	*/
	@Schema(description = "보증금"  )
	private Long deposit;
	
	/*
	  선납금
	*/
	@Schema(description = "선납금"  )
	private Long preExp;
	
	/*
	  면책금
	*/
	@Schema(description = "면책금"  )
	private String em;
	
	/*
	  대물
	*/
	@Schema(description = "대물"  )
	private String ins;
	
	/*
	  위약금
	*/
	@Schema(description = "위약금"  )
	private String penalty;
	
	/*
	  재고량
	*/
	@Schema(description = "재고량"  )
	private Integer stockQuantity;
	
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

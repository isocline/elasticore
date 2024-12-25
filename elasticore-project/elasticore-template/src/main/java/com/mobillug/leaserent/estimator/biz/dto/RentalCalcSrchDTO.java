//ecd:2109825127H20241223210702_V1.0
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
public  class RentalCalcSrchDTO  implements java.io.Serializable  {

	/*
	  차량 디테일 트림코드 (5자리)
	*/
	@Schema(description = "차량 디테일 트림코드 (5자리)" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@NotBlank
	private String trimCode;
	
	/*
	  상품타입, L:리스 / R:렌트
	*/
	@Schema(description = "상품타입, L:리스 / R:렌트"  )
	private String prodType;
	
	/*
	  수수료, 0~10, 1단위 입력
	*/
	@Schema(description = "수수료, 0~10, 1단위 입력"  )
	private Integer fee;
	
	/*
	  이용기간, 36,48,60
	*/
	@Schema(description = "이용기간, 36,48,60"  )
	private Integer period;
	
	/*
	  보증금, 0,10,20,30,40, 표기단위 %
	*/
	@Schema(description = "보증금, 0,10,20,30,40, 표기단위 %"  )
	private Integer deposit;
	
	/*
	  선납금, 0,10,20,30,40, 표기단위 %
	*/
	@Schema(description = "선납금, 0,10,20,30,40, 표기단위 %"  )
	private Integer prepaid;
	
	/*
	  옵션가격, 0~9999999, 옵션값의 합
	*/
	@Schema(description = "옵션가격, 0~9999999, 옵션값의 합"  )
	private Integer optionPrice;
	
	/*
	  할인, 할인금액 (수입차)/할인→only대리점
	*/
	@Schema(description = "할인, 할인금액 (수입차)/할인→only대리점"  )
	private Integer discount;
	
	/*
	  약정거리, 1만km,2만km,3만km,4만km,5만km,무제한
	*/
	@Schema(description = "약정거리, 1만km,2만km,3만km,4만km,5만km,무제한"  )
	private String distance;
	
	@Schema(description = "tintFrontYN"  , example="Y: 포함 | N: 미포함")
	private InclusionType tintFrontYN;
	
	@Schema(description = "tintSideYN"  , example="Y: 포함 | N: 미포함")
	private InclusionType tintSideYN;
	
	@Schema(description = "blackboxYN"  , example="Y: 포함 | N: 미포함")
	private InclusionType blackboxYN;
	
	/*
	  탁송료, 0~9999999, 기본값 0
	*/
	@Schema(description = "탁송료, 0~9999999, 기본값 0"  )
	private Integer factorTransFee;
	
	/*
	  기본값=특판/특판->only렌트/수입차->only대리점
	*/
	@Schema(description = "기본값=특판/특판->only렌트/수입차->only대리점"  )
	private String release;
	
	@Schema(description = "carTaxYN"  , example="Y: 포함 | N: 제외")
	private InclusionTexType carTaxYN;
	
	/*
	  공채, 0~99, 표기단위 % (5~8 설정 추천)
	*/
	@Schema(description = "공채, 0~99, 표기단위 % (5~8 설정 추천)"  )
	private Integer bond;
	
	/*
	  탁송지역, 지역코드
	*/
	@Schema(description = "탁송지역, 지역코드"  )
	private String transArea;
	
	/*
	  보험연령(세), 21세,26세
	*/
	@Schema(description = "보험연령(세), 21세,26세"  )
	private Integer insAge;
	
	/*
	  보험대물, 1억,2억,3억
	*/
	@Schema(description = "보험대물, 1억,2억,3억"  )
	private String insAdd;
	
	@Schema(description = "maintenanceType"  , example="01: 정비제외 | 02: 순회정비")
	private MaintenanceType maintenanceType;
	
	/*
	  면책금, 30만,50만
	*/
	@Schema(description = "면책금, 30만,50만"  )
	private String em;
	
	/*
	  업체코드
	*/
	@Schema(description = "업체코드"  )
	private String companyCode;
	

}

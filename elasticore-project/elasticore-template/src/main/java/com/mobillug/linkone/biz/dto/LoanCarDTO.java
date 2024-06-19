//ecd:1838285891H20240618012928_V0.8
package com.mobillug.linkone.biz.dto;

import com.mobillug.linkone.biz.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class LoanCarDTO  implements java.io.Serializable  {

	/*
	  파트너사 코드
	*/
	@Schema(description = "파트너사 코드"  )
	private String rentComId;
	
	/*
	  파트너사 코드
	*/
	@Schema(description = "파트너사 코드"  )
	private String rentComName;
	
	@Schema(description = "processHistory"  )
	private List<LoanCarProcessDTO> processHistory;
	
	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	
	@Schema(description = "lcCode"  )
	@Size(max=50)
	private String lcCode;
	
	/*
	  대차작업상태
	*/
	@Schema(description = "대차작업상태"  , example="RQ: 요청 | CF: 확정 | DL: 배송 | RT: 반납 | CG: 변경요청 | FL: 불가")
	private StatusType statusType;
	
	/*
	  요청상담사
	*/
	@Schema(description = "요청상담사"  )
	private String empNo;
	
	/*
	  사고유형
	*/
	@Schema(description = "사고유형"  , example="01: 피해 | 02: 정비 | 03: 가해 | 04: 단독 | 08: 미정 | 09: 기타")
	private RentKindType rentKind;
	
	@Schema(description = "rentCarCode"  )
	@Size(max=12)
	private String rentCarCode;
	
	@Schema(description = "rentCarNm"  )
	@Size(max=200)
	private String rentCarNm;
	
	@Schema(description = "rentCost"  )
	@Size(max=50)
	private String rentCost;
	
	@Schema(description = "insurNm"  )
	@Size(max=100)
	private String insurNm;
	
	@Schema(description = "indeMoney"  )
	@Size(max=50)
	private String indeMoney;
	
	/*
	  신청일 yyyyMMdd
	*/
	@Schema(description = "신청일 yyyyMMdd"  )
	private String regDate;
	
	/*
	  신청시간 hhmi
	*/
	@Schema(description = "신청시간 hhmi"  )
	private String regTime;
	
	/*
	  제휴사
	*/
	@Schema(description = "제휴사"  )
	private String joinCode;
	
	@Schema(description = "custNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private String custNm;
	
	@Schema(description = "custTel" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private String custTel;
	
	/*
	  VIP여부
	*/
	@Schema(description = "VIP여부"  , example="Y: | N:")
	private Indicator vipYn = Indicator.NO;
	
	@Schema(description = "etcDesc"  )
	@Size(max=1000)
	private String etcDesc;
	
	/*
	  요청일자 yyyymmdd
	*/
	@Schema(description = "요청일자 yyyymmdd"  )
	private String reqDate;
	
	/*
	  요청시간 hhmi
	*/
	@Schema(description = "요청시간 hhmi"  )
	private String reqTime;
	
	/*
	  요청장소우편번호
	*/
	@Schema(description = "요청장소우편번호"  )
	private String reqZip;
	
	/*
	  요청장소주소
	*/
	@Schema(description = "요청장소주소"  )
	private String reqAddr;
	
	/*
	  요청장소상세주소
	*/
	@Schema(description = "요청장소상세주소"  )
	private String reqDaddr;
	
	/*
	  차량탁송여부 Y:신청 / N:미신청
	*/
	@Schema(description = "차량탁송여부 Y:신청 / N:미신청"  , example="Y: | N:")
	private Indicator consignYn = Indicator.NO;
	
	/*
	  탁송장소(공장명)
	*/
	@Schema(description = "탁송장소(공장명)"  )
	private String consignOfiiceNm;
	
	/*
	  탁송장소(우편번호)
	*/
	@Schema(description = "탁송장소(우편번호)"  )
	private String consignZip;
	
	/*
	  탁송장소(주소)
	*/
	@Schema(description = "탁송장소(주소)"  )
	private String consignAddr;
	
	/*
	  탁송장소(상세주소)
	*/
	@Schema(description = "탁송장소(상세주소)"  )
	private String consignDaddr;
	
	/*
	  탁송장소(전화번호)
	*/
	@Schema(description = "탁송장소(전화번호)"  )
	private String consignTel;
	
	@Schema(description = "lcReason"  )
	private String lcReason;
	
	/*
	  문자파싱 성공여부
	*/
	@Schema(description = "문자파싱 성공여부"  , example="Y: | N:")
	private Indicator smsParseType = Indicator.NO;
	
	@Schema(description = "content"  )
	private String content;
	
	@Schema(description = "partnerEtcNm"  )
	@Size(max=50)
	private String partnerEtcNm;
	
	@Schema(description = "createDate"  )
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  )
	private java.time.LocalDateTime lastModifiedDate;
	
};

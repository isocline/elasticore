//ecd:-481164003H20240805175914_V0.8
package com.mobillug.linkone.apisvc.dto;

import com.mobillug.linkone.apisvc.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public  class Re100  implements java.io.Serializable  {

	/*
	  요청상담사
	*/
	@Schema(description = "요청상담사"  )
	private String empNo;
	
	/*
	  대차용도 (01:사고/02:정비/09:기타)
	*/
	@Schema(description = "대차용도 (01:사고/02:정비/09:기타)"  , example="01: 사고 | 02: 정비 | 09: 기타")
	private RentKindType rentKind;
	
	/*
	  대차차량코드
	*/
	@Schema(description = "대차차량코드"  )
	private String rentCarCode;
	
	/*
	  대차차량명
	*/
	@Schema(description = "대차차량명"  )
	private String rentCarNm;
	
	/*
	  대차비용
	*/
	@Schema(description = "대차비용"  )
	private String rentCost;
	
	/*
	  보험사 대차용도 - 사고
	*/
	@Schema(description = "보험사 대차용도 - 사고"  )
	private String insurNm;
	
	/*
	  면책금 대차용도 - 사고
	*/
	@Schema(description = "면책금 대차용도 - 사고"  )
	private String indeMoney;
	
	/*
	  신청일
	*/
	@Schema(description = "신청일"  )
	private String regDate;
	
	/*
	  신청시간
	*/
	@Schema(description = "신청시간"  )
	private String regTime;
	
	/*
	  제휴사
	*/
	@Schema(description = "제휴사"  )
	private String joincode;
	
	/*
	  고객명 AES-256 암호화
	*/
	@Schema(description = "고객명 AES-256 암호화"  )
	private String custNm;
	
	/*
	  연락처 AES-256 암호화
	*/
	@Schema(description = "연락처 AES-256 암호화"  )
	private String custTel;
	
	/*
	  VIP여부
	*/
	@Schema(description = "VIP여부"  , example="Y: | N:")
	private Indicator vipYn;
	
	/*
	  특이사항
	*/
	@Schema(description = "특이사항"  )
	private String bigo;
	
	/*
	  요청일자
	*/
	@Schema(description = "요청일자"  )
	private String reqDate;
	
	/*
	  요청시간
	*/
	@Schema(description = "요청시간"  )
	private String reqTime;
	
	/*
	  요청장소우편번호 AES-256 암호화
	*/
	@Schema(description = "요청장소우편번호 AES-256 암호화"  )
	private String reqZip;
	
	/*
	  요청장소주소 AES-256 암호화
	*/
	@Schema(description = "요청장소주소 AES-256 암호화"  )
	private String reqAddr;
	
	/*
	  요청장소상세주소 AES-256 암호화
	*/
	@Schema(description = "요청장소상세주소 AES-256 암호화"  )
	private String reqDaddr;
	
	/*
	  차량탁송여부 Y:신청 / N:미신청
	*/
	@Schema(description = "차량탁송여부 Y:신청 / N:미신청"  , example="Y: | N:")
	private Indicator consignYn;
	
	/*
	  탁송장소(공장명)
	*/
	@Schema(description = "탁송장소(공장명)"  )
	private String consignOfiiceNm;
	
	/*
	  탁송장소(우편번호) AES-256 암호화
	*/
	@Schema(description = "탁송장소(우편번호) AES-256 암호화"  )
	private String consignZip;
	
	/*
	  탁송장소(주소) AES-256 암호화
	*/
	@Schema(description = "탁송장소(주소) AES-256 암호화"  )
	private String consignAddr;
	
	/*
	  탁송장소(상세주소) AES-256 암호화
	*/
	@Schema(description = "탁송장소(상세주소) AES-256 암호화"  )
	private String consignDaddr;
	
	/*
	  탁송장소(전화번호) AES-256 암호화
	*/
	@Schema(description = "탁송장소(전화번호) AES-256 암호화"  )
	private String consignTel;
	
	/*
	  전문번호
	*/
	@Schema(description = "전문번호"  )
	private String packetId;
	
	/*
	  전문 key 값
	*/
	@Schema(description = "전문 key 값"  )
	private String masterKey;
	
	/*
	  접수일자
	*/
	@Schema(description = "접수일자"  )
	private String sendYmd;
	
	/*
	  접수시간
	*/
	@Schema(description = "접수시간"  )
	private String sendHms;
	
	/*
	  대차접수번호 (master)
	*/
	@Schema(description = "대차접수번호 (master)"  )
	private String receptNo;
	
	/*
	  전송코드
	*/
	@Schema(description = "전송코드"  )
	private String errorCd;
	
	/*
	  전송내용
	*/
	@Schema(description = "전송내용"  )
	private String errorText;
	


};

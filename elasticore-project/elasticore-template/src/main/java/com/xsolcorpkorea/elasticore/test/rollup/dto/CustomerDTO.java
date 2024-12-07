//ecd:-2028249036H20241207204629_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;

import com.xsolcorpkorea.elasticore.test.rollup.enums.*;
import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class CustomerDTO  implements java.io.Serializable  {

	@Schema(description = "custNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=13)
	private String custNo;
	
	/*
	  고객 코드
	*/
	@Schema(description = "고객 코드"  )
	@Size(max=5)
	private String custCd;
	
	/*
	  조직 사원 번호
	*/
	@Schema(description = "조직 사원 번호"  )
	@Size(max=15)
	private String orgEmpNo;
	
	@Schema(description = "custNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=50)
	private String custNm;
	
	/*
	  직통 전화 1
	*/
	@Schema(description = "직통 전화 1"  )
	@Size(max=4)
	private String directPhone1;
	
	/*
	  직통 전화 2
	*/
	@Schema(description = "직통 전화 2"  )
	@Size(max=4)
	private String directPhone2;
	
	/*
	  직통 전화 3
	*/
	@Schema(description = "직통 전화 3"  )
	@Size(max=4)
	private String directPhone3;
	
	/*
	  학력 코드
	*/
	@Schema(description = "학력 코드"  )
	@Size(max=4)
	private String academicCd;
	
	/*
	  직업명
	*/
	@Schema(description = "직업명"  )
	@Size(max=100)
	private String jobNm;
	
	/*
	  휴대폰 1
	*/
	@Schema(description = "휴대폰 1"  )
	@Size(max=4)
	private String mobilePhone1;
	
	/*
	  휴대폰 2
	*/
	@Schema(description = "휴대폰 2"  )
	@Size(max=4)
	private String mobilePhone2;
	
	/*
	  휴대폰 3
	*/
	@Schema(description = "휴대폰 3"  )
	@Size(max=70)
	private String mobilePhone3;
	
	/*
	  남여구분
	*/
	@Schema(description = "남여구분"  , example="1: 남 | 2: 여")
	private GenderCode gender;
	
	/*
	  생년 코드
	*/
	@Schema(description = "생년 코드"  )
	@Size(max=5)
	private String birthCd;
	
	@Schema(description = "birthYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String birthYmd;
	
	/*
	  이메일
	*/
	@Schema(description = "이메일"  )
	@Size(max=100)
	private String email;
	
	/*
	  자택 우편번호 1
	*/
	@Schema(description = "자택 우편번호 1"  )
	@Size(max=7)
	private String homePostNo1;
	
	/*
	  자택 우편번호 2
	*/
	@Schema(description = "자택 우편번호 2"  )
	@Size(max=7)
	private String homePostNo2;
	
	/*
	  자택 기본 주소
	*/
	@Schema(description = "자택 기본 주소"  )
	@Size(max=200)
	private String homeBaseAddr;
	
	/*
	  자택 상세 주소
	*/
	@Schema(description = "자택 상세 주소"  )
	@Size(max=300)
	private String homeDetailAddr;
	
	/*
	  기념일 코드
	*/
	@Schema(description = "기념일 코드"  , example="BT: 생일 | WD: 결홈기념일")
	private MemorialCode memorialCd;
	
	/*
	  기념일 날짜
	*/
	@Schema(description = "기념일 날짜"  )
	@Size(max=8)
	private String memorialYmd;
	
	/*
	  그룹 코드
	*/
	@Schema(description = "그룹 코드"  )
	@Size(max=13)
	private String groupCd;
	
	/*
	  그룹명
	*/
	@Schema(description = "그룹명"  )
	@Size(max=100)
	private String groupNm;
	
	/*
	  추천인명
	*/
	@Schema(description = "추천인명"  )
	@Size(max=28)
	private String recommNm;
	
	/*
	  채널 코드
	*/
	@Schema(description = "채널 코드"  )
	@Size(max=2)
	private String channelCd;
	
	/*
	  회사명
	*/
	@Schema(description = "회사명"  )
	@Size(max=100)
	private String companyNm;
	
	/*
	  회사 전화 1
	*/
	@Schema(description = "회사 전화 1"  )
	@Size(max=4)
	private String jobPhone1;
	
	/*
	  회사 전화 2
	*/
	@Schema(description = "회사 전화 2"  )
	@Size(max=4)
	private String jobPhone2;
	
	/*
	  회사 전화 3
	*/
	@Schema(description = "회사 전화 3"  )
	@Size(max=70)
	private String jobPhone3;
	
	/*
	  회사 우편번호 1
	*/
	@Schema(description = "회사 우편번호 1"  )
	@Size(max=7)
	private String jobPostNo1;
	
	/*
	  회사 우편번호 2
	*/
	@Schema(description = "회사 우편번호 2"  )
	@Size(max=7)
	private String jobPostNo2;
	
	/*
	  회사 기본 주소
	*/
	@Schema(description = "회사 기본 주소"  )
	@Size(max=200)
	private String jobBaseAddr;
	
	/*
	  회사 상세 주소
	*/
	@Schema(description = "회사 상세 주소"  )
	@Size(max=300)
	private String jobDetailAddr;
	
	/*
	  보험사 코드
	*/
	@Schema(description = "보험사 코드"  )
	@Size(max=20)
	private String insuComCd;
	
	/*
	  보험 상품명
	*/
	@Schema(description = "보험 상품명"  )
	@Size(max=500)
	private String insuProdNm;
	
	/*
	  계약 시작일
	*/
	@Schema(description = "계약 시작일"  )
	@Size(max=8)
	private String startYmd;
	
	/*
	  계약 종료일
	*/
	@Schema(description = "계약 종료일"  )
	@Size(max=8)
	private String endYmd;
	
	/*
	  계약 여부
	*/
	@Schema(description = "계약 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator contYn = Indicator.NO;
	
	/*
	  차량 번호
	*/
	@Schema(description = "차량 번호"  )
	@Size(max=30)
	private String plateNo;
	
	/*
	  정책 번호
	*/
	@Schema(description = "정책 번호"  )
	@Size(max=30)
	private String policyNo;
	
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
	
	/*
	  시스템 입력 IP
	*/
	@Schema(description = "시스템 입력 IP"  )
	@Size(max=20)
	private String createIP;
	
	/*
	  시스템 수정 IP
	*/
	@Schema(description = "시스템 수정 IP"  )
	@Size(max=20)
	private String lastModifiedIP;
	

}

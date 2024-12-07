//ecd:-1262837884H20241207204629_V1.0
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
public  class EmployeeDTO  implements java.io.Serializable  {

	@Schema(description = "empNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String empNo;
	
	/*
	  주민등록번호
	*/
	@Schema(description = "주민등록번호"  )
	@Size(max=13)
	private String juminNo;
	
	/*
	  한국 이름
	*/
	@Schema(description = "한국 이름"  )
	@Size(max=50)
	private String korNm;
	
	/*
	  영어 이름
	*/
	@Schema(description = "영어 이름"  )
	@Size(max=50)
	private String engNm;
	
	/*
	  조직 코드
	*/
	@Schema(description = "조직 코드"  )
	@Size(max=20)
	private String orgCd;
	
	/*
	  직위 코드
	*/
	@Schema(description = "직위 코드"  )
	@Size(max=20)
	private String jobPositionCd;
	
	/*
	  직급 코드
	*/
	@Schema(description = "직급 코드"  )
	@Size(max=20)
	private String jobGradeCd;
	
	/*
	  직책 코드
	*/
	@Schema(description = "직책 코드"  )
	@Size(max=20)
	private String jobTitleCd;
	
	/*
	  경력 코드
	*/
	@Schema(description = "경력 코드"  )
	@Size(max=20)
	private String careerCd;
	
	/*
	  투자 여부
	*/
	@Schema(description = "투자 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator inVtmentYn = Indicator.YES;
	
	/*
	  입사 일자 (YYYYMMDD)
	*/
	@Schema(description = "입사 일자 (YYYYMMDD)"  )
	@Size(max=8)
	private String enterYmd;
	
	/*
	  퇴사 일자 (YYYYMMDD)
	*/
	@Schema(description = "퇴사 일자 (YYYYMMDD)"  )
	@Size(max=8)
	private String retireYmd;
	
	/*
	  발령 일자 (YYYYMMDD)
	*/
	@Schema(description = "발령 일자 (YYYYMMDD)"  )
	@Size(max=8)
	private String orderYmd;
	
	/*
	  퇴사 사유
	*/
	@Schema(description = "퇴사 사유"  )
	@Size(max=100)
	private String retireReason;
	
	/*
	  생년월일 (YYYYMMDD)
	*/
	@Schema(description = "생년월일 (YYYYMMDD)"  )
	@Size(max=8)
	private String birthYmd;
	
	/*
	  휴대전화 1
	*/
	@Schema(description = "휴대전화 1"  )
	@Size(max=4)
	private String mobilePhone1;
	
	/*
	  휴대전화 2
	*/
	@Schema(description = "휴대전화 2"  )
	@Size(max=4)
	private String mobilePhone2;
	
	/*
	  휴대전화 3
	*/
	@Schema(description = "휴대전화 3"  )
	@Size(max=70)
	private String mobilePhone3;
	
	/*
	  직통전화 1
	*/
	@Schema(description = "직통전화 1"  )
	@Size(max=4)
	private String directPhone1;
	
	/*
	  직통전화 2
	*/
	@Schema(description = "직통전화 2"  )
	@Size(max=4)
	private String directPhone2;
	
	/*
	  직통전화 3
	*/
	@Schema(description = "직통전화 3"  )
	@Size(max=70)
	private String directPhone3;
	
	/*
	  이메일
	*/
	@Schema(description = "이메일"  )
	@Size(max=100)
	private String email;
	
	/*
	  우편번호 1
	*/
	@Schema(description = "우편번호 1"  )
	@Size(max=3)
	private String postNo1;
	
	/*
	  우편번호 2
	*/
	@Schema(description = "우편번호 2"  )
	@Size(max=3)
	private String postNo2;
	
	/*
	  기본 주소
	*/
	@Schema(description = "기본 주소"  )
	@Size(max=200)
	private String baseAddr;
	
	/*
	  상세 주소
	*/
	@Schema(description = "상세 주소"  )
	@Size(max=300)
	private String detailAddr;
	
	/*
	  결혼 여부
	*/
	@Schema(description = "결혼 여부"  , example="Y: | N:")
	private Indicator marryYn;
	
	/*
	  혈액형 코드
	*/
	@Schema(description = "혈액형 코드"  )
	@Size(max=2)
	private String bloodCd;
	
	/*
	  종교 코드
	*/
	@Schema(description = "종교 코드"  )
	@Size(max=2)
	private String religionCd;
	
	/*
	  취미명
	*/
	@Schema(description = "취미명"  )
	@Size(max=200)
	private String hobbyNm;
	
	/*
	  헌혈 번호
	*/
	@Schema(description = "헌혈 번호"  )
	@Size(max=1000)
	private String ctbno;
	
	/*
	  생체 정보 유형
	*/
	@Schema(description = "생체 정보 유형"  )
	@Size(max=1)
	private String bioTypeCd;
	
	/*
	  생명보험 가입 여부
	*/
	@Schema(description = "생명보험 가입 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator lifeInsuYn = Indicator.NO;
	
	/*
	  연금보험 가입 여부
	*/
	@Schema(description = "연금보험 가입 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator pnpnSvn = Indicator.NO;
	
	/*
	  은행 코드
	*/
	@Schema(description = "은행 코드"  )
	@Size(max=20)
	private String bankCd;
	
	/*
	  계좌 번호
	*/
	@Schema(description = "계좌 번호"  )
	@Size(max=20)
	private String accountNo;
	
	/*
	  사진 파일명
	*/
	@Schema(description = "사진 파일명"  )
	@Size(max=150)
	private String photoFileNm;
	
	/*
	  이메일 사용 여부
	*/
	@Schema(description = "이메일 사용 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator emailUseYn = Indicator.NO;
	
	/*
	  전자통신 우편 여부
	*/
	@Schema(description = "전자통신 우편 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator eCommP = Indicator.NO;
	
	@Schema(description = "includeNumber" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Builder.Default
	private Integer includeNumber = 0;
	
	@Schema(description = "advancedPayment"  , example="Y: | N:")
	@Builder.Default
	private Indicator advancedPayment = Indicator.NO;
	
	/*
	  장학금 여부
	*/
	@Schema(description = "장학금 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator scholarship = Indicator.NO;
	
	/*
	  장학금 시작일 (YYYYMM)
	*/
	@Schema(description = "장학금 시작일 (YYYYMM)"  )
	@Size(max=6)
	private String scholarshipStart;
	
	/*
	  장학금 종료일 (YYYYMM)
	*/
	@Schema(description = "장학금 종료일 (YYYYMM)"  )
	@Size(max=6)
	private String scholarshipEnd;
	
	/*
	  육아 휴직 여부
	*/
	@Schema(description = "육아 휴직 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator childCare = Indicator.NO;
	
	/*
	  임명 여부
	*/
	@Schema(description = "임명 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator appoint = Indicator.NO;
	
	/*
	  임명 월 (YYYYMM)
	*/
	@Schema(description = "임명 월 (YYYYMM)"  )
	@Size(max=6)
	private String appointMonth;
	
	/*
	  소개 정책
	*/
	@Schema(description = "소개 정책"  )
	@Size(max=1)
	private String introductionPolicy;
	
	/*
	  복귀 월 (YYYYMM)
	*/
	@Schema(description = "복귀 월 (YYYYMM)"  )
	@Size(max=6)
	private String revertMonth;
	
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

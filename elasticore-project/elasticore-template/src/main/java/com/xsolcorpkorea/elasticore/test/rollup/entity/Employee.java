//ecd:1930724443H20241207204628_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.entity;

import com.xsolcorpkorea.elasticore.test.rollup.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.*;
import java.time.*;
import javax.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Employee extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "emp_no", nullable = false, length = 15)
	private String empNo;
	
	
	/*
	  주민등록번호
	*/
	@Comment("주민등록번호")
	@Column(name = "jumin_no", length = 13)
	private String juminNo;
	
	
	/*
	  한국 이름
	*/
	@Comment("한국 이름")
	@Column(name = "kor_nm", length = 50)
	private String korNm;
	
	
	/*
	  영어 이름
	*/
	@Comment("영어 이름")
	@Column(name = "eng_nm", length = 50)
	private String engNm;
	
	
	/*
	  조직 코드
	*/
	@Comment("조직 코드")
	@Column(name = "org_cd", length = 20)
	private String orgCd;
	
	
	/*
	  직위 코드
	*/
	@Comment("직위 코드")
	@Column(name = "job_position_cd", length = 20)
	private String jobPositionCd;
	
	
	/*
	  직급 코드
	*/
	@Comment("직급 코드")
	@Column(name = "job_grade_cd", length = 20)
	private String jobGradeCd;
	
	
	/*
	  직책 코드
	*/
	@Comment("직책 코드")
	@Column(name = "job_title_cd", length = 20)
	private String jobTitleCd;
	
	
	/*
	  경력 코드
	*/
	@Comment("경력 코드")
	@Column(name = "career_cd", length = 20)
	private String careerCd;
	
	
	/*
	  투자 여부
	*/
	@Comment("투자 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator inVtmentYn = Indicator.YES;
	
	
	/*
	  입사 일자 (YYYYMMDD)
	*/
	@Comment("입사 일자 (YYYYMMDD)")
	@Column(name = "enter_ymd", length = 8)
	private String enterYmd;
	
	
	/*
	  퇴사 일자 (YYYYMMDD)
	*/
	@Comment("퇴사 일자 (YYYYMMDD)")
	@Column(name = "retire_ymd", length = 8)
	private String retireYmd;
	
	
	/*
	  발령 일자 (YYYYMMDD)
	*/
	@Comment("발령 일자 (YYYYMMDD)")
	@Column(name = "order_ymd", length = 8)
	private String orderYmd;
	
	
	/*
	  퇴사 사유
	*/
	@Comment("퇴사 사유")
	@Column(name = "retire_reason", length = 100)
	private String retireReason;
	
	
	/*
	  생년월일 (YYYYMMDD)
	*/
	@Comment("생년월일 (YYYYMMDD)")
	@Column(name = "birth_ymd", length = 8)
	private String birthYmd;
	
	
	/*
	  휴대전화 1
	*/
	@Comment("휴대전화 1")
	@Column(name = "mobile_phone1", length = 4)
	private String mobilePhone1;
	
	
	/*
	  휴대전화 2
	*/
	@Comment("휴대전화 2")
	@Column(name = "mobile_phone2", length = 4)
	private String mobilePhone2;
	
	
	/*
	  휴대전화 3
	*/
	@Comment("휴대전화 3")
	@Column(name = "mobile_phone3", length = 70)
	private String mobilePhone3;
	
	
	/*
	  직통전화 1
	*/
	@Comment("직통전화 1")
	@Column(name = "direct_phone1", length = 4)
	private String directPhone1;
	
	
	/*
	  직통전화 2
	*/
	@Comment("직통전화 2")
	@Column(name = "direct_phone2", length = 4)
	private String directPhone2;
	
	
	/*
	  직통전화 3
	*/
	@Comment("직통전화 3")
	@Column(name = "direct_phone3", length = 70)
	private String directPhone3;
	
	
	/*
	  이메일
	*/
	@Comment("이메일")
	@Column(name = "email", length = 100)
	private String email;
	
	
	/*
	  우편번호 1
	*/
	@Comment("우편번호 1")
	@Column(name = "post_no1", length = 3)
	private String postNo1;
	
	
	/*
	  우편번호 2
	*/
	@Comment("우편번호 2")
	@Column(name = "post_no2", length = 3)
	private String postNo2;
	
	
	/*
	  기본 주소
	*/
	@Comment("기본 주소")
	@Column(name = "base_addr", length = 200)
	private String baseAddr;
	
	
	/*
	  상세 주소
	*/
	@Comment("상세 주소")
	@Column(name = "detail_addr", length = 300)
	private String detailAddr;
	
	
	/*
	  결혼 여부
	*/
	@Comment("결혼 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator marryYn;
	
	
	/*
	  혈액형 코드
	*/
	@Comment("혈액형 코드")
	@Column(name = "blood_cd", length = 2)
	private String bloodCd;
	
	
	/*
	  종교 코드
	*/
	@Comment("종교 코드")
	@Column(name = "religion_cd", length = 2)
	private String religionCd;
	
	
	/*
	  취미명
	*/
	@Comment("취미명")
	@Column(name = "hobby_nm", length = 200)
	private String hobbyNm;
	
	
	/*
	  헌혈 번호
	*/
	@Comment("헌혈 번호")
	@Column(name = "ctbno", length = 1000)
	private String ctbno;
	
	
	/*
	  생체 정보 유형
	*/
	@Comment("생체 정보 유형")
	@Column(name = "bio_type_cd", length = 1)
	private String bioTypeCd;
	
	
	/*
	  생명보험 가입 여부
	*/
	@Comment("생명보험 가입 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator lifeInsuYn = Indicator.NO;
	
	
	/*
	  연금보험 가입 여부
	*/
	@Comment("연금보험 가입 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator pnpnSvn = Indicator.NO;
	
	
	/*
	  은행 코드
	*/
	@Comment("은행 코드")
	@Column(name = "bank_cd", length = 20)
	private String bankCd;
	
	
	/*
	  계좌 번호
	*/
	@Comment("계좌 번호")
	@Column(name = "account_no", length = 20)
	private String accountNo;
	
	
	/*
	  사진 파일명
	*/
	@Comment("사진 파일명")
	@Column(name = "photo_file_nm", length = 150)
	private String photoFileNm;
	
	
	/*
	  이메일 사용 여부
	*/
	@Comment("이메일 사용 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator emailUseYn = Indicator.NO;
	
	
	/*
	  전자통신 우편 여부
	*/
	@Comment("전자통신 우편 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator eCommP = Indicator.NO;
	
	
	@Column(name = "include_number", nullable = false)
	private Integer includeNumber = 0;
	
	
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator advancedPayment = Indicator.NO;
	
	
	/*
	  장학금 여부
	*/
	@Comment("장학금 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator scholarship = Indicator.NO;
	
	
	/*
	  장학금 시작일 (YYYYMM)
	*/
	@Comment("장학금 시작일 (YYYYMM)")
	@Column(name = "scholarship_start", length = 6)
	private String scholarshipStart;
	
	
	/*
	  장학금 종료일 (YYYYMM)
	*/
	@Comment("장학금 종료일 (YYYYMM)")
	@Column(name = "scholarship_end", length = 6)
	private String scholarshipEnd;
	
	
	/*
	  육아 휴직 여부
	*/
	@Comment("육아 휴직 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator childCare = Indicator.NO;
	
	
	/*
	  임명 여부
	*/
	@Comment("임명 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator appoint = Indicator.NO;
	
	
	/*
	  임명 월 (YYYYMM)
	*/
	@Comment("임명 월 (YYYYMM)")
	@Column(name = "appoint_month", length = 6)
	private String appointMonth;
	
	
	/*
	  소개 정책
	*/
	@Comment("소개 정책")
	@Column(name = "introduction_policy", length = 1)
	private String introductionPolicy;
	
	
	/*
	  복귀 월 (YYYYMM)
	*/
	@Comment("복귀 월 (YYYYMM)")
	@Column(name = "revert_month", length = 6)
	private String revertMonth;
	
	
}

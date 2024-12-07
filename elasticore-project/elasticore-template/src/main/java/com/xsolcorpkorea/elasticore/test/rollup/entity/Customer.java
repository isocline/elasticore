//ecd:-453780021H20241207204629_V1.0
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
public  class Customer extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "cust_no", nullable = false, length = 13)
	private String custNo;
	
	
	/*
	  고객 코드
	*/
	@Comment("고객 코드")
	@Column(name = "cust_cd", length = 5)
	private String custCd;
	
	
	/*
	  조직 사원 번호
	*/
	@Comment("조직 사원 번호")
	@Column(name = "org_emp_no", length = 15)
	private String orgEmpNo;
	
	
	@Column(name = "cust_nm", nullable = false, length = 50)
	private String custNm;
	
	
	/*
	  직통 전화 1
	*/
	@Comment("직통 전화 1")
	@Column(name = "direct_phone1", length = 4)
	private String directPhone1;
	
	
	/*
	  직통 전화 2
	*/
	@Comment("직통 전화 2")
	@Column(name = "direct_phone2", length = 4)
	private String directPhone2;
	
	
	/*
	  직통 전화 3
	*/
	@Comment("직통 전화 3")
	@Column(name = "direct_phone3", length = 4)
	private String directPhone3;
	
	
	/*
	  학력 코드
	*/
	@Comment("학력 코드")
	@Column(name = "academic_cd", length = 4)
	private String academicCd;
	
	
	/*
	  직업명
	*/
	@Comment("직업명")
	@Column(name = "job_nm", length = 100)
	private String jobNm;
	
	
	/*
	  휴대폰 1
	*/
	@Comment("휴대폰 1")
	@Column(name = "mobile_phone1", length = 4)
	private String mobilePhone1;
	
	
	/*
	  휴대폰 2
	*/
	@Comment("휴대폰 2")
	@Column(name = "mobile_phone2", length = 4)
	private String mobilePhone2;
	
	
	/*
	  휴대폰 3
	*/
	@Comment("휴대폰 3")
	@Column(name = "mobile_phone3", length = 70)
	private String mobilePhone3;
	
	
	/*
	  남여구분
	*/
	@Comment("남여구분")
	@Convert(converter = GenderCode.EntityConverter.class)
	private GenderCode gender;
	
	
	/*
	  생년 코드
	*/
	@Comment("생년 코드")
	@Column(name = "birth_cd", length = 5)
	private String birthCd;
	
	
	@Column(name = "birth_ymd", nullable = false, length = 8)
	private String birthYmd;
	
	
	/*
	  이메일
	*/
	@Comment("이메일")
	@Column(name = "email", length = 100)
	private String email;
	
	
	/*
	  자택 우편번호 1
	*/
	@Comment("자택 우편번호 1")
	@Column(name = "home_post_no1", length = 7)
	private String homePostNo1;
	
	
	/*
	  자택 우편번호 2
	*/
	@Comment("자택 우편번호 2")
	@Column(name = "home_post_no2", length = 7)
	private String homePostNo2;
	
	
	/*
	  자택 기본 주소
	*/
	@Comment("자택 기본 주소")
	@Column(name = "home_base_addr", length = 200)
	private String homeBaseAddr;
	
	
	/*
	  자택 상세 주소
	*/
	@Comment("자택 상세 주소")
	@Column(name = "home_detail_addr", length = 300)
	private String homeDetailAddr;
	
	
	/*
	  기념일 코드
	*/
	@Comment("기념일 코드")
	@Convert(converter = MemorialCode.EntityConverter.class)
	private MemorialCode memorialCd;
	
	
	/*
	  기념일 날짜
	*/
	@Comment("기념일 날짜")
	@Column(name = "memorial_ymd", length = 8)
	private String memorialYmd;
	
	
	/*
	  그룹 코드
	*/
	@Comment("그룹 코드")
	@Column(name = "group_cd", length = 13)
	private String groupCd;
	
	
	/*
	  그룹명
	*/
	@Comment("그룹명")
	@Column(name = "group_nm", length = 100)
	private String groupNm;
	
	
	/*
	  추천인명
	*/
	@Comment("추천인명")
	@Column(name = "recomm_nm", length = 28)
	private String recommNm;
	
	
	/*
	  채널 코드
	*/
	@Comment("채널 코드")
	@Column(name = "channel_cd", length = 2)
	private String channelCd;
	
	
	/*
	  회사명
	*/
	@Comment("회사명")
	@Column(name = "company_nm", length = 100)
	private String companyNm;
	
	
	/*
	  회사 전화 1
	*/
	@Comment("회사 전화 1")
	@Column(name = "job_phone1", length = 4)
	private String jobPhone1;
	
	
	/*
	  회사 전화 2
	*/
	@Comment("회사 전화 2")
	@Column(name = "job_phone2", length = 4)
	private String jobPhone2;
	
	
	/*
	  회사 전화 3
	*/
	@Comment("회사 전화 3")
	@Column(name = "job_phone3", length = 70)
	private String jobPhone3;
	
	
	/*
	  회사 우편번호 1
	*/
	@Comment("회사 우편번호 1")
	@Column(name = "job_post_no1", length = 7)
	private String jobPostNo1;
	
	
	/*
	  회사 우편번호 2
	*/
	@Comment("회사 우편번호 2")
	@Column(name = "job_post_no2", length = 7)
	private String jobPostNo2;
	
	
	/*
	  회사 기본 주소
	*/
	@Comment("회사 기본 주소")
	@Column(name = "job_base_addr", length = 200)
	private String jobBaseAddr;
	
	
	/*
	  회사 상세 주소
	*/
	@Comment("회사 상세 주소")
	@Column(name = "job_detail_addr", length = 300)
	private String jobDetailAddr;
	
	
	/*
	  보험사 코드
	*/
	@Comment("보험사 코드")
	@Column(name = "insu_com_cd", length = 20)
	private String insuComCd;
	
	
	/*
	  보험 상품명
	*/
	@Comment("보험 상품명")
	@Column(name = "insu_prod_nm", length = 500)
	private String insuProdNm;
	
	
	/*
	  계약 시작일
	*/
	@Comment("계약 시작일")
	@Column(name = "start_ymd", length = 8)
	private String startYmd;
	
	
	/*
	  계약 종료일
	*/
	@Comment("계약 종료일")
	@Column(name = "end_ymd", length = 8)
	private String endYmd;
	
	
	/*
	  계약 여부
	*/
	@Comment("계약 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator contYn = Indicator.NO;
	
	
	/*
	  차량 번호
	*/
	@Comment("차량 번호")
	@Column(name = "plate_no", length = 30)
	private String plateNo;
	
	
	/*
	  정책 번호
	*/
	@Comment("정책 번호")
	@Column(name = "policy_no", length = 30)
	private String policyNo;
	
	
}

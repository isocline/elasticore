//ecd:44498168H20240805175914_V0.8
package com.mobillug.linkone.apisvc.entity;

import com.mobillug.linkone.apisvc.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public  class ReData100 extends ReCommon implements java.io.Serializable  {

	/*
	  아이디
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("아이디")
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "create_date", updatable = false)
	@CreatedDate
	private java.time.LocalDateTime createDate;
	
	
	/*
	  요청상담사
	*/
	@Comment("요청상담사")
	@Column(name = "emp_no")
	private String empNo;
	
	
	/*
	  대차용도 (01:사고/02:정비/09:기타)
	*/
	@Comment("대차용도 (01:사고/02:정비/09:기타)")
	@Convert(converter = RentKindType.EntityConverter.class)
	private RentKindType rentKind;
	
	
	/*
	  대차차량코드
	*/
	@Comment("대차차량코드")
	@Column(name = "rent_car_code", length = 32)
	private String rentCarCode;
	
	
	/*
	  대차차량명
	*/
	@Comment("대차차량명")
	@Column(name = "rent_car_nm", length = 64)
	private String rentCarNm;
	
	
	/*
	  대차비용
	*/
	@Comment("대차비용")
	@Column(name = "rent_cost")
	private String rentCost;
	
	
	/*
	  보험사 대차용도 - 사고
	*/
	@Comment("보험사 대차용도 - 사고")
	@Column(name = "insur_nm", length = 64)
	private String insurNm;
	
	
	/*
	  면책금 대차용도 - 사고
	*/
	@Comment("면책금 대차용도 - 사고")
	@Column(name = "inde_money")
	private String indeMoney;
	
	
	/*
	  신청일
	*/
	@Comment("신청일")
	@Column(name = "reg_date", length = 10)
	private String regDate;
	
	
	/*
	  신청시간
	*/
	@Comment("신청시간")
	@Column(name = "reg_time", length = 8)
	private String regTime;
	
	
	/*
	  제휴사
	*/
	@Comment("제휴사")
	@Column(name = "joincode")
	private String joincode;
	
	
	/*
	  고객명 AES-256 암호화
	*/
	@Comment("고객명 AES-256 암호화")
	@Column(name = "cust_nm", length = 64)
	private String custNm;
	
	
	/*
	  연락처 AES-256 암호화
	*/
	@Comment("연락처 AES-256 암호화")
	@Column(name = "cust_tel", length = 15)
	private String custTel;
	
	
	/*
	  VIP여부
	*/
	@Comment("VIP여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator vipYn;
	
	
	/*
	  특이사항
	*/
	@Comment("특이사항")
	@Column(name = "bigo")
	private String bigo;
	
	
	/*
	  요청일자
	*/
	@Comment("요청일자")
	@Column(name = "req_date", length = 10)
	private String reqDate;
	
	
	/*
	  요청시간
	*/
	@Comment("요청시간")
	@Column(name = "req_time", length = 8)
	private String reqTime;
	
	
	/*
	  요청장소우편번호 AES-256 암호화
	*/
	@Comment("요청장소우편번호 AES-256 암호화")
	@Column(name = "req_zip", length = 7)
	private String reqZip;
	
	
	/*
	  요청장소주소 AES-256 암호화
	*/
	@Comment("요청장소주소 AES-256 암호화")
	@Column(name = "req_addr", length = 256)
	private String reqAddr;
	
	
	/*
	  요청장소상세주소 AES-256 암호화
	*/
	@Comment("요청장소상세주소 AES-256 암호화")
	@Column(name = "req_daddr")
	private String reqDaddr;
	
	
	/*
	  차량탁송여부 Y:신청 / N:미신청
	*/
	@Comment("차량탁송여부 Y:신청 / N:미신청")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator consignYn;
	
	
	/*
	  탁송장소(공장명)
	*/
	@Comment("탁송장소(공장명)")
	@Column(name = "consign_ofiice_nm", length = 64)
	private String consignOfiiceNm;
	
	
	/*
	  탁송장소(우편번호) AES-256 암호화
	*/
	@Comment("탁송장소(우편번호) AES-256 암호화")
	@Column(name = "consign_zip", length = 7)
	private String consignZip;
	
	
	/*
	  탁송장소(주소) AES-256 암호화
	*/
	@Comment("탁송장소(주소) AES-256 암호화")
	@Column(name = "consign_addr", length = 256)
	private String consignAddr;
	
	
	/*
	  탁송장소(상세주소) AES-256 암호화
	*/
	@Comment("탁송장소(상세주소) AES-256 암호화")
	@Column(name = "consign_daddr")
	private String consignDaddr;
	
	
	/*
	  탁송장소(전화번호) AES-256 암호화
	*/
	@Comment("탁송장소(전화번호) AES-256 암호화")
	@Column(name = "consign_tel", length = 15)
	private String consignTel;
	
	
	/*
	  상태 체크 (테스트)
	*/
	@Comment("상태 체크 (테스트)")
	@Column(name = "chk_cd")
	private String chkCd = "S";
	
	
	/*
	  다음 IF 코드
	*/
	@Comment("다음 IF 코드")
	@Column(name = "next_if_cd")
	private String nextIfCd;
	
	
};

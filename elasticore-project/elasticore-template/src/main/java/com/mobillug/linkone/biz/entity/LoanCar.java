//ecd:731177230H20240618012928_V0.8
package com.mobillug.linkone.biz.entity;

import com.mobillug.linkone.biz.enums.*;
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
import javax.persistence.*;
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
public  class LoanCar extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "id", length = 30)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.aventrix.jnanoid.jnanoid.NanoIdUtils.randomNanoId();
	}
	
	
	@Column(name = "lc_code", length = 50)
	private String lcCode;
	
	
	/*
	  대차작업상태
	*/
	@Comment("대차작업상태")
	@Convert(converter = StatusType.EntityConverter.class)
	private StatusType statusType;
	
	
	/*
	  요청상담사
	*/
	@Comment("요청상담사")
	@Column(name = "emp_no")
	private String empNo;
	
	
	/*
	  사고유형
	*/
	@Comment("사고유형")
	@Convert(converter = RentKindType.EntityConverter.class)
	private RentKindType rentKind;
	
	
	@Column(name = "rent_car_code", length = 12)
	private String rentCarCode;
	
	
	@Column(name = "rent_car_nm", length = 200)
	private String rentCarNm;
	
	
	@Column(name = "rent_cost", length = 50)
	private String rentCost;
	
	
	@Column(name = "insur_nm", length = 100)
	private String insurNm;
	
	
	@Column(name = "inde_money", length = 50)
	private String indeMoney;
	
	
	/*
	  신청일 yyyyMMdd
	*/
	@Comment("신청일 yyyyMMdd")
	@Column(name = "reg_date", length = 8)
	private String regDate;
	
	
	/*
	  신청시간 hhmi
	*/
	@Comment("신청시간 hhmi")
	@Column(name = "reg_time", length = 6)
	private String regTime;
	
	
	/*
	  제휴사
	*/
	@Comment("제휴사")
	@Column(name = "join_code", length = 32)
	private String joinCode;
	
	
	@Column(name = "cust_nm", nullable = false, length = 64)
	private String custNm;
	
	
	@Column(name = "cust_tel", nullable = false, length = 15)
	private String custTel;
	
	
	/*
	  VIP여부
	*/
	@Comment("VIP여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator vipYn = Indicator.NO;
	
	
	@Column(name = "etc_desc", length = 1000)
	private String etcDesc;
	
	
	/*
	  요청일자 yyyymmdd
	*/
	@Comment("요청일자 yyyymmdd")
	@Column(name = "req_date", length = 8)
	private String reqDate;
	
	
	/*
	  요청시간 hhmi
	*/
	@Comment("요청시간 hhmi")
	@Column(name = "req_time", length = 6)
	private String reqTime;
	
	
	/*
	  요청장소우편번호
	*/
	@Comment("요청장소우편번호")
	@Column(name = "req_zip", length = 7)
	private String reqZip;
	
	
	/*
	  요청장소주소
	*/
	@Comment("요청장소주소")
	@Column(name = "req_addr", length = 256)
	private String reqAddr;
	
	
	/*
	  요청장소상세주소
	*/
	@Comment("요청장소상세주소")
	@Column(name = "req_daddr")
	private String reqDaddr;
	
	
	/*
	  차량탁송여부 Y:신청 / N:미신청
	*/
	@Comment("차량탁송여부 Y:신청 / N:미신청")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator consignYn = Indicator.NO;
	
	
	/*
	  탁송장소(공장명)
	*/
	@Comment("탁송장소(공장명)")
	@Column(name = "consign_ofiice_nm", length = 64)
	private String consignOfiiceNm;
	
	
	/*
	  탁송장소(우편번호)
	*/
	@Comment("탁송장소(우편번호)")
	@Column(name = "consign_zip", length = 7)
	private String consignZip;
	
	
	/*
	  탁송장소(주소)
	*/
	@Comment("탁송장소(주소)")
	@Column(name = "consign_addr", length = 256)
	private String consignAddr;
	
	
	/*
	  탁송장소(상세주소)
	*/
	@Comment("탁송장소(상세주소)")
	@Column(name = "consign_daddr")
	private String consignDaddr;
	
	
	/*
	  탁송장소(전화번호)
	*/
	@Comment("탁송장소(전화번호)")
	@Column(name = "consign_tel", length = 15)
	private String consignTel;
	
	
	@Column(name = "lc_reason", columnDefinition = "TEXT")
	private String lcReason;
	
	
	/*
	  문자파싱 성공여부
	*/
	@Comment("문자파싱 성공여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator smsParseType = Indicator.NO;
	
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	
	/*
	  제휴고객사
	*/
	@Comment("제휴고객사")
	@ManyToOne
	@JoinColumn(columnDefinition = "partnerCust_id")
	private CommonCode partnerCust;
	
	
	@Column(name = "partner_etc_nm", length = 50)
	private String partnerEtcNm;
	
	
	/*
	  담당 렌트카 업체
	*/
	@Comment("담당 렌트카 업체")
	@ManyToOne
	@JoinColumn(columnDefinition = "rentCompany_id")
	private Company rentCompany;
	
	
	@OneToMany(fetch = FetchType.LAZY )
	private List<LoanCarProcess> processHistory;
	
	
};

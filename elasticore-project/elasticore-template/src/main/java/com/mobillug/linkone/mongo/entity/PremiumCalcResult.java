//ecd:-848827246H20240911110602_V1.0
package com.mobillug.linkone.mongo.entity;


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



/**


*/

@org.springframework.data.mongodb.core.mapping.Document(collection ="PremiumCalcResult")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class PremiumCalcResult  implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "process_id")
	private String processId;
	
	
	/*
	  상품코드
	*/
	@Comment("상품코드")
	@Column(name = "prod_cd")
	private String prodCd;
	
	
	/*
	  연령
	*/
	@Comment("연령")
	@Column(name = "age")
	private String age;
	
	
	/*
	  성별
	*/
	@Comment("성별")
	@Column(name = "gender", length = 1)
	private String gender;
	
	
	/*
	  보장기간타입
	*/
	@Comment("보장기간타입")
	@Column(name = "insr_prd_tp")
	private String insrPrdTp;
	
	
	/*
	  보장기간값
	*/
	@Comment("보장기간값")
	@Column(name = "insr_prd_val")
	private String insrPrdVal;
	
	
	/*
	  납입기간값
	*/
	@Comment("납입기간값")
	@Column(name = "prem_paymt_val")
	private String premPaymtVal;
	
	
	/*
	  납입기간타입
	*/
	@Comment("납입기간타입")
	@Column(name = "prem_paymt_tp")
	private String premPaymtTp;
	
	
	/*
	  고객할인구분코드
	*/
	@Comment("고객할인구분코드")
	@Column(name = "disc_class_cd")
	private String discClassCd;
	
	
	/*
	  이율적용일자
	*/
	@Comment("이율적용일자")
	@Column(name = "rate_eff_date", length = 10)
	private String rateEffDate;
	
	
	/*
	  납입주기
	*/
	@Comment("납입주기")
	@Column(name = "premium_method")
	private String premiumMethod;
	
	
	/*
	  보험료_계산식
	*/
	@Comment("보험료_계산식")
	@Column(name = "premium")
	private Double premium;
	
	
	/*
	  가입금액단위
	*/
	@Comment("가입금액단위")
	@Column(name = "premium_unit")
	private Double premiumUnit;
	
	
}

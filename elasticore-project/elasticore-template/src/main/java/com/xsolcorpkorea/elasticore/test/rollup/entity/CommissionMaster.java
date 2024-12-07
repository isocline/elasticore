//ecd:-1986223371H20241207204629_V1.0
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
@IdClass(CommissionMasterIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CommissionMaster extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "clos_ym", nullable = false, length = 6)
	private String closYm;
	
	
	@Id
	@Column(name = "ins_cp_code", nullable = false, length = 6)
	private String insCpCode;
	
	
	@Id
	@Column(name = "policy_no", nullable = false, length = 30)
	private String policyNo;
	
	
	@Id
	@Column(name = "end_pay_cnt", nullable = false)
	private Integer endPayCnt;
	
	
	@Id
	@Column(name = "polic_state_cd", nullable = false, length = 2)
	private String policStateCd;
	
	
	@Column(name = "emp_no", nullable = false, length = 15)
	private String empNo;
	
	
	/*
	  직원 이름
	*/
	@Comment("직원 이름")
	@Column(name = "emp_name", length = 50)
	private String empName;
	
	
	/*
	  조직 직원 번호
	*/
	@Comment("조직 직원 번호")
	@Column(name = "org_emp_no", length = 15)
	private String orgEmpNo;
	
	
	/*
	  보험 상품 코드
	*/
	@Comment("보험 상품 코드")
	@Column(name = "ins_prod_cd", length = 8)
	private String insProdCd;
	
	
	/*
	  상품 코드
	*/
	@Comment("상품 코드")
	@Column(name = "product_code", length = 20)
	private String productCode;
	
	
	/*
	  계약자 이름
	*/
	@Comment("계약자 이름")
	@Column(name = "pol_holder_nm", length = 10)
	private String polHolderNm;
	
	
	/*
	  계약일 (YYYYMMDD)
	*/
	@Comment("계약일 (YYYYMMDD)")
	@Column(name = "cont_ymd", length = 8)
	private String contYmd;
	
	
	/*
	  납입 주기 코드
	*/
	@Comment("납입 주기 코드")
	@Column(name = "pay_cycl_cd", length = 6)
	private String payCyclCd;
	
	
	/*
	  납입 방식 코드
	*/
	@Comment("납입 방식 코드")
	@Column(name = "pmty_cd", length = 4)
	private String pmtyCd;
	
	
	/*
	  납입 방법 코드
	*/
	@Comment("납입 방법 코드")
	@Column(name = "pay_method_cd", length = 3)
	private String payMethodCd;
	
	
	/*
	  납입일
	*/
	@Comment("납입일")
	@Column(name = "pay_ymd", length = 15)
	private String payYmd;
	
	
	/*
	  보험료
	*/
	@Comment("보험료")
	@Column(name = "in_prem")
	private Long inPrem;
	
	
	/*
	  배분 횟수
	*/
	@Comment("배분 횟수")
	@Column(name = "distr_cnt")
	private Integer distrCnt;
	
	
	/*
	  계약 수수료
	*/
	@Comment("계약 수수료")
	@Column(name = "cnvn_fre")
	private Long cnvnFre;
	
	
	/*
	  계약 전환 횟수
	*/
	@Comment("계약 전환 횟수")
	@Column(name = "cnvrt_cnt")
	private Integer cnvrtCnt;
	
	
	/*
	  실적 달성 비율
	*/
	@Comment("실적 달성 비율")
	@Column(name = "effic_achi")
	private Integer efficAchi;
	
	
	/*
	  납입 년월
	*/
	@Comment("납입 년월")
	@Column(name = "pay_ymm", length = 6)
	private String payYMM;
	
	
	/*
	  수수료 금액
	*/
	@Comment("수수료 금액")
	@Column(name = "cmi_fee_amt")
	private Long cmiFeeAmt;
	
	
	/*
	  환급 수수료 금액
	*/
	@Comment("환급 수수료 금액")
	@Column(name = "pay_back_fee_amt")
	private Long payBackFeeAmt;
	
	
	/*
	  인센티브 수수료
	*/
	@Comment("인센티브 수수료")
	@Column(name = "incentive_fee")
	private Long incentiveFee;
	
	
}

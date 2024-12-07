//ecd:-343857246H20241207204629_V1.0
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
@IdClass(CommissionContractDetailsIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CommissionContractDetails extends AuditEntity implements java.io.Serializable  {

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
	@Column(name = "commi_type_cd", nullable = false, length = 2)
	private String commiTypeCd;
	
	
	@Id
	@Column(name = "cmi_fee_cd", nullable = false, length = 2)
	private String cmiFeeCd;
	
	
	@Id
	@Column(name = "fee_state_cd", nullable = false, length = 2)
	private String feeStateCd;
	
	
	@Column(name = "cnv_fre", nullable = false)
	private Long cnvFre;
	
	
	@Column(name = "cmi_fee_rate", nullable = false)
	private Integer cmiFeeRate;
	
	
	@Column(name = "pay_back_fee_amt", nullable = false)
	private Long payBackFeeAmt;
	
	
	@Column(name = "cmi_fee_amt", nullable = false)
	private Long cmiFeeAmt;
	
	
	@Column(name = "emp_no", nullable = false, length = 15)
	private String empNo;
	
	
	/*
	  납입 패턴 코드
	*/
	@Comment("납입 패턴 코드")
	@Column(name = "pay_ptrn_code", length = 6)
	private String payPtrnCode;
	
	
	/*
	  상품 그룹 코드
	*/
	@Comment("상품 그룹 코드")
	@Column(name = "prod_grp_cd", length = 6)
	private String prodGrpCd;
	
	
}

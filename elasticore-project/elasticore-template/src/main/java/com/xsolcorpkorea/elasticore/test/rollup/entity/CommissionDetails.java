//ecd:55233724H20241207204629_V1.0
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
@IdClass(CommissionDetailsIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CommissionDetails  implements java.io.Serializable  {

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
	@Column(name = "cmi_fee_cd", nullable = false, length = 6)
	private String cmiFeeCd;
	
	
	@Id
	@Column(name = "fee_state_cd", nullable = false, length = 2)
	private String feeStateCd;
	
	
	/*
	  환산 보험료
	*/
	@Comment("환산 보험료")
	@Column(name = "cnvr_fe")
	private Long cnvrFe;
	
	
	/*
	  수수료율
	*/
	@Comment("수수료율")
	@Column(name = "cmi_fee_rate")
	private Float cmiFeeRate;
	
	
	/*
	  환수 대상 금액
	*/
	@Comment("환수 대상 금액")
	@Column(name = "pay_back_fee_amt")
	private Long payBackFeeAmt;
	
	
	/*
	  수수료
	*/
	@Comment("수수료")
	@Column(name = "cmi_fee_amt")
	private Long cmiFeeAmt;
	
	
	/*
	  계약 상태 코드
	*/
	@Comment("계약 상태 코드")
	@Column(name = "polic_state_cd", length = 2)
	private String policStateCd;
	
	
	/*
	  전산 처리 일자
	*/
	@Comment("전산 처리 일자")
	@Column(name = "sys_input_date")
	private java.time.LocalDateTime sysInputDate;
	
	
	/*
	  전산 처리자
	*/
	@Comment("전산 처리자")
	@Column(name = "sys_input_user", length = 20)
	private String sysInputUser;
	
	
	/*
	  사용자 IP
	*/
	@Comment("사용자 IP")
	@Column(name = "sys_input_ip", length = 20)
	private String sysInputIP;
	
	
	/*
	  수수료 패턴 코드
	*/
	@Comment("수수료 패턴 코드")
	@Column(name = "pay_ptrn_code", length = 6)
	private String payPtrnCode;
	
	
}

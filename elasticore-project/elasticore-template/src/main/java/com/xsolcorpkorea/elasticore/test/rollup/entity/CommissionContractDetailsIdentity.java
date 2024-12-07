//ecd:-148465472H20241207204629_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.entity;


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

@Embeddable
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CommissionContractDetailsIdentity  implements java.io.Serializable  {

	@Column(name = "clos_ym", nullable = false, length = 6)
	private String closYm;
	
	@Column(name = "ins_cp_code", nullable = false, length = 6)
	private String insCpCode;
	
	@Column(name = "policy_no", nullable = false, length = 30)
	private String policyNo;
	
	@Column(name = "end_pay_cnt", nullable = false)
	private Integer endPayCnt;
	
	@Column(name = "commi_type_cd", nullable = false, length = 2)
	private String commiTypeCd;
	
	@Column(name = "cmi_fee_cd", nullable = false, length = 2)
	private String cmiFeeCd;
	
	@Column(name = "fee_state_cd", nullable = false, length = 2)
	private String feeStateCd;
	
}

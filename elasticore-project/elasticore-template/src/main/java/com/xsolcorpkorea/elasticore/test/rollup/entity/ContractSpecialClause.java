//ecd:135947283H20241207204629_V1.0
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
@IdClass(ContractSpecialClauseIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class ContractSpecialClause extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "insu_com_cd", nullable = false, length = 6)
	private String insuComCd;
	
	
	@Id
	@Column(name = "policy_no", nullable = false, length = 30)
	private String policyNo;
	
	
	/*
	  순번
	*/
	@Id
	@Comment("순번")
	@Column(name = "seq", length = 5)
	private Integer seq;
	
	
	@Column(name = "spe_nm", nullable = false, length = 50)
	private String speNm;
	
	
	@Column(name = "start_ymd", nullable = false, length = 8)
	private String startYmd;
	
	
	@Column(name = "end_ymd", nullable = false, length = 8)
	private String endYmd;
	
	
	@Column(name = "base_prem", nullable = false)
	private Long basePrem;
	
	
	@Column(name = "pay_prem", nullable = false)
	private Long payPrem;
	
	
	@Column(name = "spe_cd", nullable = false, length = 30)
	private String speCd;
	
	
}

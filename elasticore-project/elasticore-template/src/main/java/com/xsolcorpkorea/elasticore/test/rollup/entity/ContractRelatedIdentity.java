//ecd:-809730276H20241207204629_V1.0
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
public  class ContractRelatedIdentity  implements java.io.Serializable  {

	@Column(name = "insu_com_cd", nullable = false, length = 6)
	private String insuComCd;
	
	@Column(name = "policy_no", nullable = false, length = 30)
	private String policyNo;
	
	/*
	  순번
	*/
	@Column(name = "seq", length = 5)
	private Integer seq;
	
}

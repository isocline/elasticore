//ecd:899232192H20241207204629_V1.0
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
@IdClass(ContractRelatedIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class ContractRelated extends AuditEntity implements java.io.Serializable  {

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
	
	
	@Column(name = "ins_type_cd", nullable = false, length = 1)
	private String insTypeCd;
	
	
	@Column(name = "ins_holder_nm", nullable = false, length = 20)
	private String insHolderNm;
	
	
	/*
	  계약자 번호
	*/
	@Comment("계약자 번호")
	@Column(name = "ins_holder_no", length = 13)
	private String insHolderNo;
	
	
	/*
	  계약 관계 코드
	*/
	@Comment("계약 관계 코드")
	@Column(name = "ins_rel_cd", length = 3)
	private String insRelCd;
	
	
	/*
	  계약자 나이
	*/
	@Comment("계약자 나이")
	@Column(name = "ins_age", length = 3)
	private String insAge;
	
	
}

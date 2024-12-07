//ecd:380010514H20241207204629_V1.0
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
public  class CustInsuranceStatusIdentity  implements java.io.Serializable  {

	@Column(name = "cust_seq", nullable = false, length = 10)
	private String custSeq;
	
	/*
	  순번
	*/
	@Column(name = "seq", length = 5)
	private Integer seq;
	
}

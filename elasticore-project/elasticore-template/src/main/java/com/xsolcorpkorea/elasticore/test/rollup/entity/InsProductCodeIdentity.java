//ecd:557958603H20241207204629_V1.0
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
public  class InsProductCodeIdentity  implements java.io.Serializable  {

	@Column(name = "ins_cp_code", nullable = false, length = 6)
	private String insCpCode;
	
	@Column(name = "product_code", nullable = false, length = 20)
	private String productCode;
	
}

//ecd:1119694858H20241207204629_V1.0
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
public  class EmpEducationIdentity  implements java.io.Serializable  {

	@Column(name = "emp_no", nullable = false, length = 15)
	private String empNo;
	
	@Column(name = "enter_ymd", nullable = false, length = 8)
	private String enterYmd;
	
}

//ecd:-1753732294H20241014191354_V1.0
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

@org.hibernate.annotations.DynamicUpdate
@Embeddable
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Person  implements java.io.Serializable  {

	@Column(name = "age")
	private Integer age;
	
	
}

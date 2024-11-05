//ecd:-1155815089H20241104230940_V1.0
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
import javax.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class PersonGroup  implements java.io.Serializable  {

	@Id
	@Column(name = "id")
	private String id;
	
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "scope1")
	private Integer scope1;
	
	
	@Column(name = "scope2")
	private Integer scope2;
	
	
}

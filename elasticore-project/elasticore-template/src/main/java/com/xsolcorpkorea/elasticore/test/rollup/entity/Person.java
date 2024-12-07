//ecd:599033668H20241207204628_V1.0
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
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Person extends AbstractEntity implements java.io.Serializable  {

	@Id
	@Column(name = "id")
	private String id;
	
	
	@Column(name = "name")
	private String name;
	
	
	@ManyToOne
	@JoinColumn(columnDefinition = "personGrp_id")
	private PersonGroup personGrp;
	
	
	@Column(name = "age")
	private Integer age;
	
	
	@Column(name = "addr")
	private String addr;
	
	
}

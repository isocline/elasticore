//ecd:2059579590H20241212185913_V1.0
package com.test.A2.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Person extends com.test.A1.entity.AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "id")
	private String id;
	
	
	@Column(name = "name", length = 12)
	private String name;
	
	
	@Column(name = "age")
	private Integer age;
	
	
	private com.test.A1.entity.Company company;
	
	
}

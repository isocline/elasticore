//ecd:-1971213066H20250204004214_V1.0
package com.test.entity;


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
import com.test.entity.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Employee extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "id")
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = java.util.UUID.randomUUID().toString();
	}
	
	
	/*
	  직원명
	*/
	@Comment("직원명")
	@Column(name = "name", nullable = false)
	private String name;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(columnDefinition = "company_id")
	private Company company;
	
	
}

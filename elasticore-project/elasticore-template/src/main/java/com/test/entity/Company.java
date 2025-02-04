//ecd:1633440169H20250204004214_V1.0
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
public  class Company extends AuditEntity implements java.io.Serializable  {

	/*
	  PK
	*/
	@Id
	@Comment("PK")
	@Column(name = "cid", length = 30)
	private String cid;
	
	
	@PrePersist
	public void prePersist() {
	  if (cid == null)
	        cid = java.util.UUID.randomUUID().toString();
	}
	
	
	/*
	  회사명
	*/
	@Comment("회사명")
	@Column(name = "name")
	private String name;
	
	
	/*
	  직원
	*/
	@Comment("직원")
	@OneToMany(fetch = FetchType.LAZY ,mappedBy="company")
	private List<Employee> emps;
	
	
}

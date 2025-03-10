//ecd:-754387731H20250310230523_V1.0
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
public  class Company  implements java.io.Serializable  {

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
	
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Employee> emps;
	
	
	@Column(name = "reg_no")
	@jakarta.validation.constraints.Pattern( regexp = "^\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])[1-4]\\d{6}$", message = "유효하지 않은 주민등록번호 형식입니다. (예. 9001011234567)" )
	@jakarta.validation.constraints.NotNull
	private String regNo;
	
	
}

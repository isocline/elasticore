//ecd:-1818256757H20250311000811_V1.0
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
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Employee  implements java.io.Serializable  {

	@Id
	@Column(name = "id")
	private Integer id;
	
	
	@Column(name = "name")
	private String name;
	
	
	/*
	  사원 번호 (자동 생성 및 유니크)
	*/
	@Comment("사원 번호 (자동 생성 및 유니크)")
	@Column(name = "emp_no", length = 15, unique = true)
	private String empNo;
	
	
}

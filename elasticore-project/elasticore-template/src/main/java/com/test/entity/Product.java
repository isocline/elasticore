//ecd:-1492113795H20250117173850_V1.0
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
public  class Product extends LifecycleEntity implements java.io.Serializable  {

	/*
	  PK
	*/
	@Id
	@Comment("PK")
	@Column(name = "pid", length = 30)
	private String pid;
	
	
	@PrePersist
	public void prePersist() {
	  if (pid == null)
	        pid = java.util.UUID.randomUUID().toString();
	}
	
	
	/*
	  상품명
	*/
	@Comment("상품명")
	@Column(name = "name")
	private String name;
	
	
	/*
	  상품 영문명
	*/
	@Comment("상품 영문명")
	@Column(name = "eng_name", length = 64)
	private String engName;
	
	
	/*
	  상품개요
	*/
	@Comment("상품개요")
	@Column(name = "desc", columnDefinition = "TEXT")
	private String desc;
	
	
	/*
	  상품가격
	*/
	@Comment("상품가격")
	@Column(name = "price")
	private Long price;
	
	
	/*
	  상품 무게
	*/
	@Comment("상품 무게")
	@Column(name = "weight")
	private Double weight;
	
	
}

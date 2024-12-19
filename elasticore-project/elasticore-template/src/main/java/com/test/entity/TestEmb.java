//ecd:1396800087H20241219144053_V1.0
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



/**


*/

@org.hibernate.annotations.DynamicUpdate
@Embeddable
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class TestEmb  implements java.io.Serializable  {

	/*
	  조건
	*/
	@Comment("조건")
	@Column(name = "condition")
	private String condition;
	
	
	/*
	  값
	*/
	@Comment("값")
	@Column(name = "value")
	private Double value;
	
	
	/*
	  설명
	*/
	@Comment("설명")
	@Column(name = "content")
	private String content;
	
	
}

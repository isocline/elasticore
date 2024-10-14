//ecd:-506870370H20241014191354_V1.0
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

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BASE")
@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public abstract class BaseResidualInfo extends AuditEntity implements java.io.Serializable  {

	/*
	  아이디
	*/
	@Id
	@Comment("아이디")
	@Column(name = "id", length = 12)
	private String id;
	
	
	/*
	  rollup 대응 discriminator 타입정보
	*/
	@Comment("rollup 대응 discriminator 타입정보")
	@Column(name = "type", insertable = false, updatable = false)
	private String type;
	
	
	/*
	  잔가구분
	*/
	@Comment("잔가구분")
	@Column(name = "division", length = 4)
	private String division;
	
	
}

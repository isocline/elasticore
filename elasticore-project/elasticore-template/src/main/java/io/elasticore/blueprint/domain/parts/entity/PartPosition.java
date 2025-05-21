//ecd:-1176086743H20250422194920_V1.0
package io.elasticore.blueprint.domain.parts.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import java.util.*;
import java.time.*;
import jakarta.persistence.Entity;


/**
 * PartPosition
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@jakarta.persistence.Table(name="epc_part_position")
@org.hibernate.annotations.DynamicUpdate
@IdClass(PartPositionIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class PartPosition  implements java.io.Serializable  {

	public PartPosition(String id,String number) {
	    this.id = id;    this.number = number;
	}
	
	/*
	  groupId
	*/
	@Id
	@Comment("groupId")
	@Column(name = "id", length = 50)
	private String id;
	
	
	@Id
	@Column(name = "number", length = 10)
	private String number;
	
	
	@Column(name = "coordinates")
	@Convert(converter = io.elasticore.springboot3.util.IntegerArrayConverter.class)
	private Integer[] coordinates;
	
	
}

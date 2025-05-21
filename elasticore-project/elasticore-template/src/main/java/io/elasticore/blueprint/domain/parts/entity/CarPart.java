//ecd:1804226605H20250422194920_V1.0
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
 * CarPart
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@jakarta.persistence.Table(name="epc_car_part")
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CarPart  implements java.io.Serializable  {

	public CarPart(String id) {
	    this.id = id;
	}
	
	@Id
	@Column(name = "id", length = 30)
	private String id;
	
	
	@Column(name = "number", length = 30)
	private String number;
	
	
	@Column(name = "name_id", length = 10)
	private String nameId;
	
	
	/*
	  Part name
	*/
	@Comment("Part name")
	@Column(name = "name", length = 500)
	private String name;
	
	
	/*
	  notice
	*/
	@Comment("notice")
	@Column(name = "notice", length = 500)
	private String notice;
	
	
	/*
	  그룹 설명
	*/
	@Comment("그룹 설명")
	@Column(name = "description", length = 1000)
	private String description;
	
	
	@Column(name = "position_number", length = 10)
	private String positionNumber;
	
	
	@Column(name = "url", length = 512)
	private String url;
	
	
}

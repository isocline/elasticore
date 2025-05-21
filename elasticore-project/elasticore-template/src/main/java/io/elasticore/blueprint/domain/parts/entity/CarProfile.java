//ecd:-1496527089H20250422232619_V1.0
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
import io.elasticore.blueprint.domain.parts.entity.*;
import jakarta.persistence.Entity;


/**
 * CarProfile
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@jakarta.persistence.Table(name="epc_car_profile")
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CarProfile  implements java.io.Serializable  {

	public CarProfile(String vin) {
	    this.vin = vin;
	}
	
	/*
	  VIN
	*/
	@Id
	@Comment("VIN")
	@Column(name = "vin", nullable = false, length = 36)
	private String vin;
	
	
	/*
	  frame
	*/
	@Comment("frame")
	@Column(name = "frame", length = 100)
	private String frame;
	
	
	@ManyToOne
	@JoinColumn(columnDefinition = "carInfo_id")
	private CarInfo carInfo;
	
	
}

//ecd:-2024927632H20250422181247_V1.0
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



/**
 * PartGroupInfoIdentity
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Embeddable
@lombok.EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class PartGroupInfoIdentity  implements java.io.Serializable  {

	/*
	  차량 ID (UUID)
	*/
	@Column(name = "car_id", nullable = false, length = 36)
	private String carId;
	
	/*
	  부품 그룹 ID
	*/
	@Column(name = "group_id", nullable = false, length = 50)
	private String groupId;
	
}

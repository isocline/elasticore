//ecd:2103569490H20250422172634_V1.0
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
 * PartPositionIdentity
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
public  class PartPositionIdentity  implements java.io.Serializable  {

	/*
	  groupId
	*/
	@Column(name = "id", length = 50)
	private String id;
	
	@Column(name = "number", length = 10)
	private String number;
	
}

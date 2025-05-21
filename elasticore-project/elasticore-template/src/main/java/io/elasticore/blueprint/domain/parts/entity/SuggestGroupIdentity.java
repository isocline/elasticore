//ecd:-706394768H20250422093122_V1.0
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
 * SuggestGroupIdentity
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
public  class SuggestGroupIdentity  implements java.io.Serializable  {

	/*
	  고유 카탈로그 식별자
	*/
	@Column(name = "catalog_id", nullable = false, length = 30)
	private String catalogId;
	
	/*
	  search id
	*/
	@Column(name = "sid", length = 16)
	private String sid;
	
}

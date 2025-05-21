//ecd:1468410400H20250422194920_V1.0
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
 * SuggestGroup
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@jakarta.persistence.Table(name="epc_suggest_group")
@org.hibernate.annotations.DynamicUpdate
@IdClass(SuggestGroupIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class SuggestGroup  implements java.io.Serializable  {

	public SuggestGroup(String catalogId,String sid) {
	    this.catalogId = catalogId;    this.sid = sid;
	}
	
	/*
	  고유 카탈로그 식별자
	*/
	@Id
	@Comment("고유 카탈로그 식별자")
	@Column(name = "catalog_id", nullable = false, length = 30)
	private String catalogId;
	
	
	/*
	  search id
	*/
	@Id
	@Comment("search id")
	@Column(name = "sid", length = 16)
	private String sid;
	
	
}

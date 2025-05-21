//ecd:-84689139H20250422194920_V1.0
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
 * Catalog
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@jakarta.persistence.Table(name="epc_catalog")
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Catalog  implements java.io.Serializable  {

	public Catalog(String catalogId) {
	    this.catalogId = catalogId;
	}
	
	/*
	  고유 카탈로그 식별자
	*/
	@Id
	@Comment("고유 카탈로그 식별자")
	@Column(name = "catalog_id", nullable = false, length = 30)
	private String catalogId;
	
	
	/*
	  카탈로그 이름
	*/
	@Comment("카탈로그 이름")
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	
}

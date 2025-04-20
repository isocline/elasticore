//ecd:769777900H20250417190806_V1.0
package io.elasticore.blueprint.domain.bbs.entity;

import io.elasticore.blueprint.domain.bbs.enums.*;
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
 * AuditEntity
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity extends BaseEntity implements java.io.Serializable  {

	@Column(name = "last_modified_by", length = 20)
	@org.springframework.data.annotation.LastModifiedBy
	private String lastModifiedBy;
	
	
	@Column(name = "last_modified_date")
	@org.springframework.data.annotation.LastModifiedDate
	private java.time.LocalDateTime lastModifiedDate;
	
	
	/*
	  시스템 입력 IP
	*/
	@Comment("시스템 입력 IP")
	@Column(name = "create_ip", length = 20)
	private String createIP;
	
	
	/*
	  시스템 수정 IP
	*/
	@Comment("시스템 수정 IP")
	@Column(name = "last_modified_ip", length = 20)
	private String lastModifiedIP;
	
	
}

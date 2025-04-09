//ecd:-295482803H20250409104819_V1.0
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
import java.util.*;
import java.time.*;



/**
 * BaseEntity
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
public abstract class BaseEntity  implements java.io.Serializable  {

	@Column(name = "create_date", updatable = false)
	@org.springframework.data.annotation.CreatedDate
	private java.time.LocalDateTime createDate;
	
	
	@Column(name = "created_by", updatable = false, length = 20)
	@org.springframework.data.annotation.CreatedBy
	private String createdBy;
	
	
}

//ecd:45178897H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.entity;

import com.mobillug.leaserent.estimator.biz.enums.*;
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


*/

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity  implements java.io.Serializable  {

	@Column(name = "create_date", updatable = false)
	@org.springframework.data.annotation.CreatedDate
	private java.time.LocalDateTime createDate;
	
	
	@Column(name = "created_by", updatable = false, length = 20)
	@org.springframework.data.annotation.CreatedBy
	private String createdBy;
	
	
	@Column(name = "last_modified_by", length = 20)
	@org.springframework.data.annotation.LastModifiedBy
	private String lastModifiedBy;
	
	
	@Column(name = "last_modified_date")
	@org.springframework.data.annotation.LastModifiedDate
	private java.time.LocalDateTime lastModifiedDate;
	
	
}

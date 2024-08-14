//ecd:1345968487H20240814213720_V1.0
package com.mobillug.linkone.biz.entity;

import com.mobillug.linkone.biz.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@MappedSuperclass
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity  implements java.io.Serializable  {

	/*
	  테스트2323
	*/
	@Comment("테스트2323")
	@Column(name = "create_date", updatable = false)
	@CreatedDate
	private java.time.LocalDateTime createDate;
	
	
	@Column(name = "created_by", updatable = false, length = 20)
	@CreatedBy
	private String createdBy;
	
	
	@Column(name = "last_modified_by", length = 20)
	@LastModifiedBy
	private String lastModifiedBy;
	
	
	@Column(name = "last_modified_date")
	@LastModifiedDate
	private java.time.LocalDateTime lastModifiedDate;
	
	
	// == DEVELOPER SECTION START =============================

	//test

	// =============================== DEVELOPER SECTION END ==
}

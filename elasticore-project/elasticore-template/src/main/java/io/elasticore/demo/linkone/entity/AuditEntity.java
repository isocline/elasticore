//ecd:-244959732H20240529100717V0.7
package io.elasticore.demo.linkone.entity;

import io.elasticore.demo.linkone.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.hibernate.annotations.Comment;
import javax.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class AuditEntity  implements java.io.Serializable  {
	@Column(name = "create_date", updatable = false)
	@CreatedDate
	private java.time.LocalDateTime createDate;
	
	
	@Column(name = "created_by", updatable = false)
	@CreatedBy
	private String createdBy;
	
	
	@Column(name = "last_modified_by")
	@LastModifiedBy
	private String lastModifiedBy;
	
	
	@Column(name = "last_modified_date")
	@LastModifiedDate
	private java.time.LocalDateTime lastModifiedDate;
	
	
};

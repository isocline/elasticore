//ecd:382101328H20240524175232V0.7
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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class AuditEntity  implements java.io.Serializable  {

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.time.LocalDateTime createDate;
	
	
	@Column(name = "created_by")
	private String createdBy;
	
	
	@Column(name = "last_modified_by")
	private String lastModifiedBy;
	
	
	@Column(name = "last_modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.time.LocalDateTime lastModifiedDate;
	
	

};

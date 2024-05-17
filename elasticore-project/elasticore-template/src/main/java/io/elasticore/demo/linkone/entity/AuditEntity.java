//ecd:-1158745113H20240517105348V0.7
package io.elasticore.demo.linkone.entity;


import io.elasticore.demo.linkone.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import javax.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class AuditEntity  implements java.io.Serializable  {

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date createDate;
	

	@Column(name = "created_by")
	private String createdBy;
	

	@Column(name = "last_modified_by")
	private String lastModifiedBy;
	

	@Column(name = "last_modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date lastModifiedDate;
	


};

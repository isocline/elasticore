//ecd:950522285H20240521223026V0.7
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

public  class LoanCarProcess  implements java.io.Serializable  {

	@Id
	@Column(name = "seq")
	private Long seq;
	
	
	@ManyToOne
	@JoinColumn(columnDefinition = "loanCar_id")
	private LoanCar loanCar;
	
	
	private StatusType lcFixCode;
	
	
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

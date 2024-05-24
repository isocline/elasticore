//ecd:195593718H20240524175232V0.7
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

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class LoanCarProcess extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "seq")
	private Long seq;
	
	
	@ManyToOne
	@JoinColumn(columnDefinition = "loanCar_id")
	private LoanCar loanCar;
	
	
	private StatusType lcFixCode;
	
	

};

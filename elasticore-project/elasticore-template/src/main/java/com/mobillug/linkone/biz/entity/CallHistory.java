//ecd:1783201809H20240618012928_V0.8
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

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public  class CallHistory extends AuditEntity implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "call_code")
	private Long callCode;
	
	
	/*
	  관련 작업
	*/
	@Comment("관련 작업")
	@ManyToOne
	@JoinColumn(columnDefinition = "loanCarProcess_id")
	private LoanCarProcess loanCarProcess;
	
	
	@Column(name = "call_content", columnDefinition = "TEXT")
	private String callContent;
	
	
	@Column(name = "call_date", length = 8)
	private String callDate;
	
	
	@Column(name = "call_time", length = 6)
	private String callTime;
	
	
};

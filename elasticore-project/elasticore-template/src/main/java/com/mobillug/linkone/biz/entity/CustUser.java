//ecd:-1815184141H20240618012928_V0.8
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
public  class CustUser extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "id", length = 30)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.aventrix.jnanoid.jnanoid.NanoIdUtils.randomNanoId();
	}
	
	
	/*
	  업체
	*/
	@Comment("업체")
	@ManyToOne
	@JoinColumn(columnDefinition = "company_id")
	private Company company;
	
	
	@Column(name = "usr_id", nullable = false, length = 20, unique = true)
	private String usrId;
	
	
	@Column(name = "password", nullable = false, length = 128)
	private String password;
	
	
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	
	
	@Column(name = "tel_no", nullable = false, length = 16)
	private String telNo;
	
	
	@Column(name = "email", length = 128)
	private String email;
	
	
	@Column(name = "dept_nm", length = 60)
	private String deptNm;
	
	
	@Column(name = "grade", length = 60)
	private String grade;
	
	
};

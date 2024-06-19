//ecd:882020442H20240618012928_V0.8
package com.mobillug.gateone.biz.entity;

import com.mobillug.gateone.biz.enums.*;
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
import jakarta.persistence.*;
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
public  class ServiceInfo extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "id", length = 30)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.aventrix.jnanoid.jnanoid.NanoIdUtils.randomNanoId();
	}
	
	
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	
	
	@Column(name = "key_name", length = 8)
	private String keyName;
	
	
	@Column(name = "main_url", nullable = false, length = 256)
	private String mainUrl;
	
	
	@Column(name = "login_url", nullable = false, length = 256)
	private String loginUrl;
	
	
};

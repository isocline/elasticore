//ecd:-1307188931H20240618012928_V0.8
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
public  class MappingService extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "id", length = 30)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.aventrix.jnanoid.jnanoid.NanoIdUtils.randomNanoId();
	}
	
	
	/*
	  서비스 정보
	*/
	@Comment("서비스 정보")
	@ManyToOne
	@JoinColumn(columnDefinition = "service_id")
	private ServiceInfo service;
	
	
	/*
	  권한 정보 (관리자, 렌트카회원, 일반 사용자 등)
	*/
	@Comment("권한 정보 (관리자, 렌트카회원, 일반 사용자 등)")
	@Convert(converter = UserRole.EntityConverter.class)
	private UserRole role;
	
	
};

//ecd:-1522401152H20240618012928_V0.8
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
public  class LoginUser extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "id", length = 30)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.aventrix.jnanoid.jnanoid.NanoIdUtils.randomNanoId();
	}
	
	
	@Column(name = "user_id", nullable = false, length = 20, unique = true)
	private String userId;
	
	
	@Column(name = "password", nullable = false, length = 128)
	private String password;
	
	
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	
	
	@Column(name = "phone", nullable = false, length = 16)
	private String phone;
	
	
	@Column(name = "email", length = 128)
	private String email;
	
	
	/*
	  아이디 상태 (정상, 패스워드 5회 실패, 정지)
	*/
	@Comment("아이디 상태 (정상, 패스워드 5회 실패, 정지)")
	@Convert(converter = UserStatus.EntityConverter.class)
	private UserStatus status;
	
	
	/*
	  사용자별 접속 가능한 서비스 목록
	*/
	@Comment("사용자별 접속 가능한 서비스 목록")
	@OneToMany(fetch = FetchType.LAZY )
	private List<MappingService> allowServieList;
	
	
};

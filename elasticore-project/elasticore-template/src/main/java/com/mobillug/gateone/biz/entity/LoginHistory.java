//ecd:271156409H20240618012928_V0.8
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
public  class LoginHistory extends AuditEntity implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;
	
	
	@Column(name = "user_id", nullable = false, length = 20)
	private String userId;
	
	
	/*
	  로그인 성공여부
	*/
	@Comment("로그인 성공여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator successYN = Indicator.NO;
	
	
	@Column(name = "token", length = 1000)
	private String token;
	
	
	@Column(name = "agent_info", length = 1000)
	private String agentInfo;
	
	
	@Column(name = "client_ip", length = 36)
	private String clientIp;
	
	
	/*
	  token 만료일시
	*/
	@Comment("token 만료일시")
	@Column(name = "expire_date_time", updatable = false)
	private java.time.LocalDateTime expireDateTime;
	
	
	/*
	  생성일시
	*/
	@Comment("생성일시")
	@Column(name = "create_date_time", updatable = false)
	@CreatedDate
	private java.time.LocalDateTime createDateTime;
	
	
};

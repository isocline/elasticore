//ecd:1014874192H20240529174205V0.7
package io.elasticore.demo.linkone.entity;

import io.elasticore.demo.linkone.enums.*;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_seq")
	private Long usrSeq;
	
	
	/*
	  업체
	*/
	@Comment("업체")
	@ManyToOne
	@JoinColumn(columnDefinition = "company_id")
	private Company company;
	
	
	/*
	  아이디
	*/
	@Comment("아이디")
	@Column(name = "usr_id", nullable = false, length = 20)
	private String usrId;
	
	
	/*
	  패스워드
	*/
	@Comment("패스워드")
	@Column(name = "password", nullable = false, length = 128)
	private String password;
	
	
	/*
	  이름
	*/
	@Comment("이름")
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	
	
	/*
	  전화번호
	*/
	@Comment("전화번호")
	@Column(name = "tel_no", nullable = false, length = 16)
	private String telNo;
	
	
	/*
	  이메일
	*/
	@Comment("이메일")
	@Column(name = "email", length = 128)
	private String email;
	
	
	/*
	  부서
	*/
	@Comment("부서")
	@Column(name = "dept_nm", length = 60)
	private String deptNm;
	
	
	/*
	  직급
	*/
	@Comment("직급")
	@Column(name = "grade", length = 60)
	private String grade;
	
	
};

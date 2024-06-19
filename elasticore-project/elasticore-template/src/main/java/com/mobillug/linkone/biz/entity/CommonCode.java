//ecd:1224829547H20240618012928_V0.8
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
public  class CommonCode extends AuditEntity implements java.io.Serializable  {

	/*
	  코드 sequence
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("코드 sequence")
	@Column(name = "code_sn")
	private Long codeSn;
	
	
	@Column(name = "code_id", nullable = false, length = 50, unique = true)
	private String codeId;
	
	
	@Column(name = "code_nm", nullable = false, length = 50)
	private String codeNm;
	
	
	@Column(name = "code_value", nullable = false, length = 30)
	private String codeValue;
	
	
	@Column(name = "code_optn1", length = 50)
	private String codeOptn1;
	
	
	@Column(name = "code_optn2", length = 50)
	private String codeOptn2;
	
	
	@Column(name = "code_optn3", length = 50)
	private String codeOptn3;
	
	
	/*
	  코드 depth
	*/
	@Comment("코드 depth")
	@Column(name = "code_depth")
	private Integer codeDepth = 1;
	
	
	/*
	  순서 정보
	*/
	@Comment("순서 정보")
	@Column(name = "code_order")
	private Integer codeOrder = 10;
	
	
};

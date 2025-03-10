//ecd:1446438934H20250310225833_V1.0
package com.test.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@org.hibernate.annotations.DynamicUpdate
@Embeddable
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Address  implements java.io.Serializable  {

	/*
	  우편번호
	*/
	@Comment("우편번호")
	@Column(name = "post_no", length = 5)
	private String postNo;
	
	
	/*
	  우편번호 2
	*/
	@Comment("우편번호 2")
	@Column(name = "post_no2", length = 7)
	private String postNo2;
	
	
	/*
	  기본 주소
	*/
	@Comment("기본 주소")
	@Column(name = "base_addr", length = 200)
	private String baseAddr;
	
	
	/*
	  상세 주소
	*/
	@Comment("상세 주소")
	@Column(name = "detail_addr", length = 300)
	private String detailAddr;
	
	
}

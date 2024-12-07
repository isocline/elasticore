//ecd:2036602308H20241207204628_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.entity;

import com.xsolcorpkorea.elasticore.test.rollup.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.*;
import java.time.*;
import javax.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@IdClass(CommonCodeIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CommonCode extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "code_type", nullable = false, length = 20)
	private String codeType;
	
	
	@Id
	@Column(name = "main_code", nullable = false, length = 20)
	private String mainCode;
	
	
	/*
	  사용 시작일 (YYYYMMDD)
	*/
	@Comment("사용 시작일 (YYYYMMDD)")
	@Column(name = "use_frmdt", length = 8)
	private String useFrmdt;
	
	
	/*
	  사용 종료일 (YYYYMMDD)
	*/
	@Comment("사용 종료일 (YYYYMMDD)")
	@Column(name = "use_todt", length = 8)
	private String useTodt;
	
	
	/*
	  코드 짧은 이름
	*/
	@Comment("코드 짧은 이름")
	@Column(name = "short_name", length = 100)
	private String shortName;
	
	
	/*
	  코드 긴 이름
	*/
	@Comment("코드 긴 이름")
	@Column(name = "long_name", length = 200)
	private String longName;
	
	
	/*
	  정렬 순서
	*/
	@Comment("정렬 순서")
	@Column(name = "order_no")
	private Integer orderNo;
	
	
	/*
	  상위 유형
	*/
	@Comment("상위 유형")
	@Column(name = "up_type", length = 20)
	private String upType;
	
	
	/*
	  상위 코드
	*/
	@Comment("상위 코드")
	@Column(name = "up_code", length = 20)
	private String upCode;
	
	
}

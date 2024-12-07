//ecd:-1780174284H20241207204629_V1.0
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
@IdClass(EmpFamilyIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class EmpFamily extends AuditEntity implements java.io.Serializable  {

	/*
	  사원번호
	*/
	@Id
	@Comment("사원번호")
	@Column(name = "emp_no", length = 15)
	private String empNo;
	
	
	/*
	  순번
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("순번")
	@Column(name = "seq", length = 5)
	private Long seq;
	
	
	/*
	  주민번호
	*/
	@Comment("주민번호")
	@Column(name = "jumin_no", length = 13)
	private String juminNo;
	
	
	/*
	  가족관계명
	*/
	@Comment("가족관계명")
	@Column(name = "family_rel_nm", length = 30)
	private String familyRelNm;
	
	
	/*
	  이름
	*/
	@Comment("이름")
	@Column(name = "name", length = 20)
	private String name;
	
	
	/*
	  기타사항
	*/
	@Comment("기타사항")
	@Column(name = "etc", length = 50)
	private String etc;
	
	
	/*
	  동거여부
	*/
	@Comment("동거여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator togetherYn = Indicator.NO;
	
	
}

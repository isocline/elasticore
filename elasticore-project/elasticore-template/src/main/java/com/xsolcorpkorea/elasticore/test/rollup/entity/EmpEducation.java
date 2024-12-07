//ecd:450447392H20241207204629_V1.0
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
@IdClass(EmpEducationIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class EmpEducation extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "emp_no", nullable = false, length = 15)
	private String empNo;
	
	
	@Id
	@Column(name = "enter_ymd", nullable = false, length = 8)
	private String enterYmd;
	
	
	/*
	  졸업일자
	*/
	@Comment("졸업일자")
	@Column(name = "out_ymd", length = 8)
	private String outYmd;
	
	
	/*
	  학교명
	*/
	@Comment("학교명")
	@Column(name = "school_nm", length = 100)
	private String schoolNm;
	
	
	/*
	  학력코드
	*/
	@Comment("학력코드")
	@Column(name = "school_career_cd", length = 15)
	private String schoolCareerCd;
	
	
	/*
	  전공명
	*/
	@Comment("전공명")
	@Column(name = "subject_nm", length = 100)
	private String subjectNm;
	
	
}

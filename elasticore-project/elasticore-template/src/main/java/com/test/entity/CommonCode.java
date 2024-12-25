//ecd:-2127104496H20241225181951_V1.0
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
import jakarta.persistence.Entity;


/**


*/

@Entity
@jakarta.persistence.Table(
   @jakarta.persistence.Index(name="test", comlumnList="1")
  ,@jakarta.persistence.Index(name="test2", comlumnList="2", unique=true)
  )
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CommonCode  implements java.io.Serializable  {

	/*
	  코드 sequence
	*/
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@GenericGenerator(
	  name = "seq_gen",
	  strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	  parameters = {
	    @Parameter(name = "sequence_name", value = "CommonCode_codeSn_seq"),
	    @Parameter(name = "initial_value", value = "1"),
	    @Parameter(name = "increment_size", value = "1")
	}
	)
	@Comment("코드 sequence")
	@Column(name = "code_sn")
	private Long codeSn;
	
	
	@Id
	@Column(name = "code_id", nullable = false, length = 50)
	private String codeId;
	
	
	/*
	  상위 코드 아이디
	*/
	@Comment("상위 코드 아이디")
	@Column(name = "code_pnt_id", length = 50)
	private String codePntId;
	
	
	@Column(name = "code_nm", nullable = false, length = 50)
	private String codeNm;
	
	
	@Column(name = "code_value", nullable = false, length = 100)
	private String codeValue;
	
	
	/*
	  코드옵션1
	*/
	@Comment("코드옵션1")
	@Column(name = "code_optn1", length = 50)
	private String codeOptn1;
	
	
	/*
	  코드옵션2
	*/
	@Comment("코드옵션2")
	@Column(name = "code_optn2", length = 50)
	private String codeOptn2;
	
	
	/*
	  코드옵션3
	*/
	@Comment("코드옵션3")
	@Column(name = "code_optn3", length = 50)
	private String codeOptn3;
	
	
	/*
	  숫자옵션1
	*/
	@Comment("숫자옵션1")
	@Column(name = "number_optn1")
	private Double numberOptn1;
	
	
	/*
	  숫자옵션2
	*/
	@Comment("숫자옵션2")
	@Column(name = "number_optn2")
	private Double numberOptn2;
	
	
	/*
	  숫자옵션3
	*/
	@Comment("숫자옵션3")
	@Column(name = "number_optn3")
	private Double numberOptn3;
	
	
	/*
	  코드 depth
	*/
	@Comment("코드 depth")
	@Column(name = "code_depth")
	private Integer codeDepth;
	
	
	/*
	  순서 정보
	*/
	@Comment("순서 정보")
	@Column(name = "code_order")
	private Integer codeOrder = 10;
	
	
}

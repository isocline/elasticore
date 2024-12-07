//ecd:1464865733H20241207204629_V1.0
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
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CustContactHistory extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "emp_no", nullable = false, length = 15)
	private String empNo;
	
	
	/*
	  접촉 시퀀스
	*/
	@Comment("접촉 시퀀스")
	@Column(name = "contact_seq", length = 5)
	private Integer contactSeq;
	
	
	@Column(name = "contact_ymd", nullable = false, length = 8)
	private String contactYmd;
	
	
	@Column(name = "contact_time", nullable = false, length = 12)
	private String contactTime;
	
	
	/*
	  접촉 코드
	*/
	@Comment("접촉 코드")
	@Column(name = "contact_cd", length = 2)
	private String contactCd;
	
	
	@Column(name = "cust_nm", nullable = false, length = 20)
	private String custNm;
	
	
	/*
	  고객 시퀀스
	*/
	@Comment("고객 시퀀스")
	@Column(name = "cust_seq", length = 13)
	private String custSeq;
	
	
	/*
	  고객 번호
	*/
	@Comment("고객 번호")
	@Column(name = "cust_no", length = 13)
	private String custNo;
	
	
	/*
	  정책 번호
	*/
	@Comment("정책 번호")
	@Column(name = "policy_no", length = 30)
	private String policyNo;
	
	
	/*
	  오픈 여부
	*/
	@Comment("오픈 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator openYN = Indicator.NO;
	
	
	/*
	  접촉 제목
	*/
	@Comment("접촉 제목")
	@Column(name = "contact_ttl", length = 100)
	private String contactTtl;
	
	
	/*
	  접촉 장소
	*/
	@Comment("접촉 장소")
	@Column(name = "contact_place", length = 100)
	private String contactPlace;
	
	
	@Column(name = "contact_desc", nullable = false, length = 300)
	private String contactDesc;
	
	
}

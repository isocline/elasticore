//ecd:117668814H20241207204629_V1.0
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
@IdClass(InsuranceCompanyCodeIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class InsuranceCompanyCode extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "ins_cp_code", nullable = false, length = 6)
	private String insCpCode;
	
	
	@Id
	@Column(name = "ins_cp_type_cd", nullable = false, length = 2)
	private String insCpTypeCd;
	
	
	@Column(name = "ins_cp_nm", nullable = false, length = 50)
	private String insCpNm;
	
	
	@Column(name = "reg_date", nullable = false, length = 8)
	private String regDate;
	
	
	@Column(name = "start_date", nullable = false, length = 8)
	private String startDate;
	
	
	/*
	  종료일 (YYYYMMDD)
	*/
	@Comment("종료일 (YYYYMMDD)")
	@Column(name = "end_date", length = 8)
	private String endDate;
	
	
	/*
	  담당자 이름
	*/
	@Comment("담당자 이름")
	@Column(name = "mgr_nm", length = 30)
	private String mgrNm;
	
	
	/*
	  계약 번호
	*/
	@Comment("계약 번호")
	@Column(name = "cont_num", length = 8)
	private String contNum;
	
	
	/*
	  계약 여부
	*/
	@Comment("계약 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator contYN;
	
	
	/*
	  관리 수량
	*/
	@Comment("관리 수량")
	@Column(name = "mgt_cnt", length = 4)
	private Integer mgtCnt;
	
	
	/*
	  수수료 수량
	*/
	@Comment("수수료 수량")
	@Column(name = "fee_cnt", length = 4)
	private Integer feeCnt;
	
	
	/*
	  회사 팩스 번호
	*/
	@Comment("회사 팩스 번호")
	@Column(name = "company_fax_no", length = 14)
	private String companyFaxNo;
	
	
	/*
	  시작 수량
	*/
	@Comment("시작 수량")
	@Column(name = "start_cnt", length = 4)
	private Integer startCnt;
	
	
	/*
	  종료 수량
	*/
	@Comment("종료 수량")
	@Column(name = "end_cnt", length = 4)
	private Integer endCnt;
	
	
	@Column(name = "pension_adjust_rate", nullable = false)
	private Float pensionAdjustRate;
	
	
	@Column(name = "protection_adjust_rate", nullable = false)
	private Float protectionAdjustRate;
	
	
	@Column(name = "adjust_rate", nullable = false)
	private Float adjustRate;
	
	
}

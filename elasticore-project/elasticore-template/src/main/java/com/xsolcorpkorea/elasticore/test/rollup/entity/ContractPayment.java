//ecd:1555809770H20241207204629_V1.0
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
@IdClass(ContractPaymentIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class ContractPayment extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "policy_no", nullable = false, length = 30)
	private String policyNo;
	
	
	@Id
	@Column(name = "insu_com_cd", nullable = false, length = 6)
	private String insuComCd;
	
	
	/*
	  순번
	*/
	@Id
	@Comment("순번")
	@Column(name = "seq", length = 5)
	private Integer seq;
	
	
	/*
	  배포 횟수
	*/
	@Comment("배포 횟수")
	@Column(name = "distr_cnt", length = 3)
	private Integer distrCnt;
	
	
	@Column(name = "pay_ymm", nullable = false, length = 6)
	private String payYMM;
	
	
	/*
	  납입 방법 코드
	*/
	@Comment("납입 방법 코드")
	@Column(name = "pay_method_cd", length = 4)
	private String payMethodCd;
	
	
	@Column(name = "pay_ymd", nullable = false, length = 8)
	private String payYmd;
	
	
	@Column(name = "sum_prem", nullable = false)
	private Long sumPrem;
	
	
	/*
	  수정 보험료
	*/
	@Comment("수정 보험료")
	@Column(name = "modify_prem")
	private Long modifyPrem;
	
	
	@Column(name = "pay_state_cd", nullable = false, length = 3)
	private String payStateCd;
	
	
	/*
	  영수증 번호
	*/
	@Comment("영수증 번호")
	@Column(name = "rect_no", length = 30)
	private String rectNo;
	
	
	/*
	  요율 유형
	*/
	@Comment("요율 유형")
	@Column(name = "rate_type", length = 4)
	private String rateType;
	
	
	@Column(name = "pay_amt", nullable = false)
	private Long payAmt;
	
	
	/*
	  작업일자
	*/
	@Comment("작업일자")
	@Column(name = "job_date", length = 8)
	private String jobDate;
	
	
	/*
	  마감 여부
	*/
	@Comment("마감 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator closYn;
	
	
	/*
	  계약번호 (이전)
	*/
	@Comment("계약번호 (이전)")
	@Column(name = "policy_no_bk", length = 30)
	private String policyNo_BK;
	
	
	/*
	  계약번호 (신규)
	*/
	@Comment("계약번호 (신규)")
	@Column(name = "policy_no_new", length = 30)
	private String policyNo_New;
	
	
}

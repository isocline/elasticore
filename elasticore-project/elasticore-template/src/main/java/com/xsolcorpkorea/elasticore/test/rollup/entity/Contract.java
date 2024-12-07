//ecd:1493949847H20241207204629_V1.0
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
public  class Contract  implements java.io.Serializable  {

	@Id
	@Column(name = "policy_no", nullable = false, length = 30)
	private String policyNo;
	
	
	@PrePersist
	public void prePersist() {
	  if (policyNo == null)
	        policyNo = java.util.UUID.randomUUID().toString();
	}
	
	
	@Column(name = "insu_com_cd", nullable = false, length = 6)
	private String insuComCd;
	
	
	/*
	  상품유형코드
	*/
	@Comment("상품유형코드")
	@Column(name = "insu_item_cd", length = 6)
	private String insuItemCd;
	
	
	/*
	  상품코드
	*/
	@Comment("상품코드")
	@Column(name = "insu_prod_cd", length = 20)
	private String insuProdCd;
	
	
	/*
	  상품명
	*/
	@Comment("상품명")
	@Column(name = "insu_prod_nm", length = 100)
	private String insuProdNm;
	
	
	/*
	  계약자주민번호
	*/
	@Comment("계약자주민번호")
	@Column(name = "pol_holder_no", length = 13)
	private String polHolderNo;
	
	
	/*
	  계약자명
	*/
	@Comment("계약자명")
	@Column(name = "pol_holder_nm", length = 50)
	private String polHolderNm;
	
	
	/*
	  피보험자주민번호
	*/
	@Comment("피보험자주민번호")
	@Column(name = "ins_holder_no", length = 13)
	private String insHolderNo;
	
	
	/*
	  피보험자명
	*/
	@Comment("피보험자명")
	@Column(name = "ins_holder_nm", length = 50)
	private String insHolderNm;
	
	
	/*
	  계약일자
	*/
	@Comment("계약일자")
	@Column(name = "cont_ymd", length = 8)
	private String contYmd;
	
	
	/*
	  납입주기코드
	*/
	@Comment("납입주기코드")
	@Column(name = "pam_cycl_cd", length = 4)
	private String pamCyclCd;
	
	
	/*
	  납입방법코드
	*/
	@Comment("납입방법코드")
	@Column(name = "pay_method_cd", length = 4)
	private String payMethodCd;
	
	
	/*
	  계약상태코드
	*/
	@Comment("계약상태코드")
	@Column(name = "polic_state_cd", length = 4)
	private String policStateCd;
	
	
	/*
	  계약상태변경일
	*/
	@Comment("계약상태변경일")
	@Column(name = "endorse_ymd", length = 8)
	private String endorseYmd;
	
	
	/*
	  계약시작일자
	*/
	@Comment("계약시작일자")
	@Column(name = "start_ymd", length = 8)
	private String startYmd;
	
	
	/*
	  계약종료일자
	*/
	@Comment("계약종료일자")
	@Column(name = "end_ymd", length = 8)
	private String endYmd;
	
	
	/*
	  주계약보험료
	*/
	@Comment("주계약보험료")
	@Column(name = "main_prem")
	private Long mainPrem;
	
	
	/*
	  특약보험료
	*/
	@Comment("특약보험료")
	@Column(name = "spe_prem")
	private Long spePrem;
	
	
	/*
	  합계보험료
	*/
	@Comment("합계보험료")
	@Column(name = "sum_prem")
	private Long sumPrem;
	
	
	/*
	  실납입보험료
	*/
	@Comment("실납입보험료")
	@Column(name = "real_prem")
	private Long realPrem;
	
	
	/*
	  계약자 코드
	*/
	@Comment("계약자 코드")
	@Column(length = 1)
	private char holderCd;
	
	
	/*
	  수익자 코드
	*/
	@Comment("수익자 코드")
	@Column(length = 1)
	private char benefCd;
	
	
	/*
	  수익자명
	*/
	@Comment("수익자명")
	@Column(name = "benef_nm", length = 20)
	private String benefNm;
	
	
	/*
	  수익자주민번호
	*/
	@Comment("수익자주민번호")
	@Column(name = "benef_no", length = 13)
	private String benefNo;
	
	
	/*
	  모집자사번
	*/
	@Comment("모집자사번")
	@Column(name = "org_emp_no", length = 15)
	private String orgEmpNo;
	
	
	/*
	  취급자사번
	*/
	@Comment("취급자사번")
	@Column(name = "emp_no", length = 15)
	private String empNo;
	
	
	/*
	  전산처리일자
	*/
	@Comment("전산처리일자")
	@Column(name = "sys_input_date")
	private java.time.LocalDateTime sysInputDate;
	
	
	@Column(name = "sysy_input_user", nullable = false, length = 20)
	private String sysyInputUser;
	
	
	@Column(name = "sys_input_ip", nullable = false, length = 20)
	private String sysInputIP;
	
	
	/*
	  계약자고객seq
	*/
	@Comment("계약자고객seq")
	@Column(name = "pol_holder_seq", length = 10)
	private String polHolderSeq;
	
	
	/*
	  피보험자고객seq
	*/
	@Comment("피보험자고객seq")
	@Column(name = "ins_holder_seq", length = 10)
	private String insHolderSeq;
	
	
	/*
	  납입코드
	*/
	@Comment("납입코드")
	@Column(name = "pmty_cd", length = 4)
	private String pmtyCd;
	
	
	/*
	  지급일자
	*/
	@Comment("지급일자")
	@Column(name = "pay_ymd", length = 8)
	private String payYmd;
	
	
	/*
	  최종회차
	*/
	@Comment("최종회차")
	@Column(name = "end_pay_cnt")
	private Integer endPayCnt;
	
	
	/*
	  자동차납입회차
	*/
	@Comment("자동차납입회차")
	@Column(name = "distri_cnt")
	private Integer distriCnt;
	
	
	/*
	  모집자회사코드
	*/
	@Comment("모집자회사코드")
	@Column(name = "org_emp_com_cd", length = 15)
	private String orgEmpComCd;
	
	
	/*
	  취급자회사코드
	*/
	@Comment("취급자회사코드")
	@Column(name = "emp_com_cd", length = 15)
	private String empComCd;
	
	
	/*
	  납입기간
	*/
	@Comment("납입기간")
	@Column(name = "pay_term", length = 4)
	private String payTerm;
	
	
	/*
	  이체방법코드
	*/
	@Comment("이체방법코드")
	@Column(name = "trans_cd", length = 4)
	private String transCd;
	
	
	/*
	  수정보험료
	*/
	@Comment("수정보험료")
	@Column(name = "modify_prem")
	private Long modifyPrem;
	
	
	/*
	  전환보험료
	*/
	@Comment("전환보험료")
	@Column(name = "cnvr_prem")
	private Long cnvrPrem;
	
	
	/*
	  인정보험료
	*/
	@Comment("인정보험료")
	@Column(name = "recog_prem")
	private Long recogPrem;
	
	
	/*
	  보험료일자
	*/
	@Comment("보험료일자")
	@Column(name = "prem_ymd", length = 8)
	private String premYmd;
	
	
	/*
	  최종월도
	*/
	@Comment("최종월도")
	@Column(name = "pay_yymm", length = 6)
	private String payYYMM;
	
	
	/*
	  자동차번호
	*/
	@Comment("자동차번호")
	@Column(name = "plate_no", length = 20)
	private String plateNo;
	
	
	/*
	  자필서명여부
	*/
	@Comment("자필서명여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator signYN;
	
	
	/*
	  계약생성일
	*/
	@Comment("계약생성일")
	@Column(length = 8)
	private char BWDate;
	
	
	/*
	  유예기간
	*/
	@Comment("유예기간")
	@Column(length = 8)
	private char graceDate;
	
	
	/*
	  등급구역
	*/
	@Comment("등급구역")
	@Column(name = "grade_zone", length = 4)
	private String gradeZone;
	
	
	/*
	  물건구분
	*/
	@Comment("물건구분")
	@Column(length = 1)
	private char spcGb;
	
	
	/*
	  변경사유
	*/
	@Comment("변경사유")
	@Column(name = "change_reason", length = 250)
	private String changeReason;
	
	
	/*
	  우량여부
	*/
	@Comment("우량여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator blueChipYN;
	
	
	/*
	  메모
	*/
	@Comment("메모")
	@Column(name = "memo", length = 500)
	private String memo;
	
	
	/*
	  서명복구일자
	*/
	@Comment("서명복구일자")
	@Column(name = "sign_recovery_dt", length = 8)
	private String signRecoveryDt;
	
	
	/*
	  자동이체계좌
	*/
	@Comment("자동이체계좌")
	@Column(name = "autotransfer_acc", length = 50)
	private String autotransferAcc;
	
	
	/*
	  조직보험상품코드
	*/
	@Comment("조직보험상품코드")
	@Column(name = "org_insu_prod_cd", length = 20)
	private String orgInsuProdCd;
	
	
	/*
	  대상 코드
	*/
	@Comment("대상 코드")
	@Column(name = "object_cd", length = 2)
	private String objectCd;
	
	
	/*
	  수수료
	*/
	@Comment("수수료")
	@Column(name = "commission")
	private Long commission;
	
	
	/*
	  만기기간
	*/
	@Comment("만기기간")
	@Column(name = "expiration_period", length = 4)
	private String expirationPeriod;
	
	
	/*
	  만기유형코드
	*/
	@Comment("만기유형코드")
	@Column(name = "expiration_type_cd", length = 2)
	private String expirationTypeCd;
	
	
	/*
	  계약번호(이전)
	*/
	@Comment("계약번호(이전)")
	@Column(name = "policy_no_bk", length = 30)
	private String policyNo_BK;
	
	
	/*
	  계약번호(신규)
	*/
	@Comment("계약번호(신규)")
	@Column(name = "policy_no_new", length = 30)
	private String policyNo_New;
	
	
	/*
	  의료보험료
	*/
	@Comment("의료보험료")
	@Column(name = "medical_prem")
	private Long medicalPrem;
	
	
	/*
	  보증보험료
	*/
	@Comment("보증보험료")
	@Column(name = "guarantee_prem")
	private Long guaranteePrem;
	
	
	/*
	  가상계좌번호
	*/
	@Comment("가상계좌번호")
	@Column(name = "vitual_bank_no", length = 50)
	private String vitualBankNo;
	
	
	/*
	  실비가입여부
	*/
	@Comment("실비가입여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator silbiYN;
	
	
	/*
	  수금자사번
	*/
	@Comment("수금자사번")
	@Column(name = "bill_emp_no", length = 20)
	private String billEmpNo;
	
	
}

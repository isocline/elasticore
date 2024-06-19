//ecd:1279350042H20240618012928_V0.8
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
public  class LoanCarProcess extends AuditEntity implements java.io.Serializable  {

	/*
	  순번 아이디
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("순번 아이디")
	@Column(name = "lcp_code")
	private Long lcpCode;
	
	
	/*
	  렌트카 업체
	*/
	@Comment("렌트카 업체")
	@ManyToOne
	@JoinColumn(columnDefinition = "rentCompany_id")
	private Company rentCompany;
	
	
	/*
	  상태 프로세스
	*/
	@Comment("상태 프로세스")
	@Convert(converter = RentCarProcessType.EntityConverter.class)
	private RentCarProcessType processType;
	
	
	@Column(name = "apply_date", length = 8)
	private String applyDate;
	
	
	@Column(name = "apply_time", length = 6)
	private String applyTime;
	
	
	/*
	  통화내역
	*/
	@Comment("통화내역")
	@ManyToOne
	@JoinColumn(columnDefinition = "callHistory_id")
	private CallHistory callHistory;
	
	
	@Column(name = "memo", columnDefinition = "TEXT")
	private String memo;
	
	
	@Column(name = "loan_car_master_id", length = 30)
	private String loanCarMasterId;
	
	
	@Column(name = "cust_req_memo", columnDefinition = "TEXT")
	private String custReqMemo;
	
	
	@Column(name = "req_car_name", length = 30)
	private String reqCarName;
	
	
	@Column(name = "req_car_no", length = 12)
	private String reqCarNo;
	
	
	/*
	  자차보험 서비스제공여부
	*/
	@Comment("자차보험 서비스제공여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator insureYN = Indicator.NO;
	
	
};

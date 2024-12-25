//ecd:1629865059H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.entity;

import com.mobillug.leaserent.estimator.biz.enums.*;
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
import com.mobillug.leaserent.estimator.biz.enums.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class TxLog  implements java.io.Serializable  {

	/*
	  Transaction 아이디
	*/
	@Id
	@Comment("Transaction 아이디")
	@Column(name = "tx_id")
	private String txId;
	
	
	@PrePersist
	public void prePersist() {
	  if (txId == null)
	        txId = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
	@Convert(converter = TxType.EntityConverter.class)
	private TxType txType;
	
	
	/*
	  podName
	*/
	@Comment("podName")
	@Column(name = "pod_name", length = 200)
	private String podName;
	
	
	@Convert(converter = TxStatus.EntityConverter.class)
	private TxStatus txStatus;
	
	
	@Column(name = "title", length = 2000)
	private String title;
	
	
	@Column(name = "log_txt", columnDefinition = "TEXT")
	private String logTxt;
	
	
	@Column(name = "start_date", nullable = false)
	private java.time.LocalDateTime startDate;
	
	
	/*
	  생성일시
	*/
	@Comment("생성일시")
	@Column(name = "end_date", updatable = false)
	@org.springframework.data.annotation.CreatedDate
	private java.time.LocalDateTime endDate;
	
	
	/*
	  사용자
	*/
	@Comment("사용자")
	@Column(name = "created_by", updatable = false, length = 20)
	@org.springframework.data.annotation.CreatedBy
	private String createdBy;
	
	
}

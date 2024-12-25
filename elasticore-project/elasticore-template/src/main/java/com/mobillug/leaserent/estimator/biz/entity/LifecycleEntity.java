//ecd:-1244658158H20241223210702_V1.0
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



/**


*/

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public abstract class LifecycleEntity extends AuditEntity implements java.io.Serializable  {

	/*
	  차량 정보 적용일시 예
	*/
	@Comment("차량 정보 적용일시 예")
	@Column(name = "effective_date", length = 8)
	private String effectiveDate;
	
	
	/*
	  차량 정보 종료일시,null 또 공백시 설정 안됨. 예
	*/
	@Comment("차량 정보 종료일시,null 또 공백시 설정 안됨. 예")
	@Column(name = "end_date", length = 8)
	private String endDate = "99991231";
	
	
	/*
	  트랜잭션 아이디
	*/
	@Comment("트랜잭션 아이디")
	@Column(name = "tx_id", length = 12)
	private String txId;
	
	
}

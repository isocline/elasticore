//ecd:-815413780H20241223210702_V1.0
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

@org.hibernate.annotations.DynamicUpdate
@Embeddable
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class RentDetails  implements java.io.Serializable  {

	/*
	  선납금
	*/
	@Comment("선납금")
	@Column(name = "advanced_payment_price")
	private Long advancedPaymentPrice;
	
	
	/*
	  보증금
	*/
	@Comment("보증금")
	@Column(name = "deposit_price")
	private Long depositPrice;
	
	
	/*
	  AG 수수료 퍼센트
	*/
	@Comment("AG 수수료 퍼센트")
	@Column(name = "ag_fee_percent")
	private Float agFeePercent;
	
	
	/*
	  CM 수수료 퍼센트
	*/
	@Comment("CM 수수료 퍼센트")
	@Column(name = "cm_fee_percent")
	private Float cmFeePercent;
	
	
}

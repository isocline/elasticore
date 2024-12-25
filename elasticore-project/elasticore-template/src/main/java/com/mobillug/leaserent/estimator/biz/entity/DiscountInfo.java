//ecd:1096885658H20241223210702_V1.0
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
import com.mobillug.leaserent.estimator.biz.entity.*;


/**


*/

@org.hibernate.annotations.DynamicUpdate
@Embeddable
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class DiscountInfo  implements java.io.Serializable  {

	/*
	  할인 금액(율)
	*/
	@Comment("할인 금액(율)")
	@Column(name = "discount_price")
	private Long discountPrice;
	
	
	/*
	  할인율
	*/
	@Comment("할인율")
	@Column(name = "discount_rate")
	private Float discountRate;
	
	
	/*
	  옵션명
	*/
	@Comment("옵션명")
	@Column(name = "opt_name", length = 64)
	private String optName;
	
	
	/*
	  차량 정보 참조
	*/
	@Comment("차량 정보 참조")
	@ManyToOne
	@JoinColumn(columnDefinition = "baseCarInfo_id")
	private BaseCarInfo baseCarInfo;
	
	
}

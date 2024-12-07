//ecd:-1950165496H20241207203210_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.entity;


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
@IdClass(BaseCarInfo2Identity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class BaseCarInfo2  implements java.io.Serializable  {

	@Id
	@Column(name = "brand_id", length = 12)
	private String brandId;
	
	
	@Id
	@Column(name = "car_id", length = 12)
	private String carId;
	
	
	@Id
	@Column(name = "tx_id", length = 12)
	private String txId;
	
	
	/*
	  면세 여부 // 예
	*/
	@Comment("면세 여부 // 예")
	@Column(name = "tax_exempt")
	private Boolean taxExempt;
	
	
}

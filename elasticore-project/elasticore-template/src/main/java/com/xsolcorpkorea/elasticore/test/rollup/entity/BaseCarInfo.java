//ecd:-1733134520H20241206231555_V1.0
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
@IdClass(BaseCarInfoIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class BaseCarInfo  implements java.io.Serializable  {

	@Id
	@Column(name = "brand_id", length = 12)
	private String brandId;
	
	
	@Id
	@Column(name = "car_id", length = 12)
	private String carId;
	
	
	/*
	  면세 여부 // 예
	*/
	@Comment("면세 여부 // 예")
	@Column(name = "tax_exempt")
	private Boolean taxExempt;
	
	
	/*
	  차량 기본가
	*/
	@Comment("차량 기본가")
	@Column(name = "car_base_price")
	private Long carBasePrice;
	
	
	/*
	  36개월 예상 렌트료
	*/
	@Comment("36개월 예상 렌트료")
	@Column(name = "payment36m")
	private Long payment36M;
	
	
	/*
	  48개월 예상 렌트료
	*/
	@Comment("48개월 예상 렌트료")
	@Column(name = "payment48m")
	private Long payment48M;
	
	
	/*
	  60개월 예상 렌트료
	*/
	@Comment("60개월 예상 렌트료")
	@Column(name = "payment60m")
	private Long payment60M;
	
	
}

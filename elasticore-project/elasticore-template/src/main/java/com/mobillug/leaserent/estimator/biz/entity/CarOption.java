//ecd:-879738592H20241223210702_V1.0
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
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CarOption  implements java.io.Serializable  {

	/*
	  순번 아이디
	*/
	@Id
	@Comment("순번 아이디")
	@Column(name = "id", length = 12)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
	/*
	  옵션명 // 예
	*/
	@Comment("옵션명 // 예")
	@Column(name = "option_name", length = 500)
	private String optionName;
	
	
	/*
	  옵션 코드 // 예
	*/
	@Comment("옵션 코드 // 예")
	@Column(name = "option_code", length = 10)
	private String optionCode;
	
	
	/*
	  옵션 가격
	*/
	@Comment("옵션 가격")
	@Column(name = "option_price")
	private Long optionPrice;
	
	
	/*
	  필요 옵션 // 예
	*/
	@Comment("필요 옵션 // 예")
	@Column(name = "required_option", length = 4000)
	@Convert(converter = com.mobillug.leaserent.estimator.common.utils.StringArrayConverter.class)
	private String[] requiredOption;
	
	
	/*
	  옵션 설명
	*/
	@Comment("옵션 설명")
	@Column(name = "option_description", columnDefinition = "TEXT")
	private String optionDescription;
	
	
	/*
	  중복 불가 옵션 // 예
	*/
	@Comment("중복 불가 옵션 // 예")
	@Column(name = "duplicated_option_code", length = 4000)
	@Convert(converter = com.mobillug.leaserent.estimator.common.utils.StringArrayConverter.class)
	private String[] duplicatedOptionCode;
	
	
	/*
	  차량 모델코드
	*/
	@Comment("차량 모델코드")
	@Column(name = "car_model_code", length = 10)
	private String carModelCode;
	
	
	/*
	  옵션 구분 // 예
	*/
	@Comment("옵션 구분 // 예")
	@Column(name = "is_tune_acc")
	private Boolean isTuneAcc;
	
	
	/*
	  temp
	*/
	@Comment("temp")
	@Column(name = "restriction")
	private String restriction;
	
	
}

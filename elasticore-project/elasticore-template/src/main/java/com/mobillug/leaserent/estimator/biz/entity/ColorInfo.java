//ecd:465923319H20241223210702_V1.0
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
public  class ColorInfo  implements java.io.Serializable  {

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
	  색상명 // 예
	*/
	@Comment("색상명 // 예")
	@Column(name = "color_name", length = 256)
	private String colorName;
	
	
	/*
	  색상 프론트 코드 // 예
	*/
	@Comment("색상 프론트 코드 // 예")
	@Column(name = "color_front_code", length = 20)
	private String colorFrontCode;
	
	
	/*
	  색상 코드 // 예
	*/
	@Comment("색상 코드 // 예")
	@Column(name = "color_code", length = 20)
	private String colorCode;
	
	
	/*
	  색상 가격
	*/
	@Comment("색상 가격")
	@Column(name = "color_price")
	private Long colorPrice;
	
	
	/*
	  필요 옵션 // 예
	*/
	@Comment("필요 옵션 // 예")
	@Column(name = "required_options", length = 4000)
	@Convert(converter = com.mobillug.leaserent.estimator.common.utils.StringArrayConverter.class)
	private String[] requiredOptions;
	
	
	/*
	  특정 색상 선택 시 사용불가 코드 // 예
	*/
	@Comment("특정 색상 선택 시 사용불가 코드 // 예")
	@Column(name = "unavailable_color_codes", length = 4000)
	@Convert(converter = com.mobillug.leaserent.estimator.common.utils.StringArrayConverter.class)
	private String[] unavailableColorCodes;
	
	
}

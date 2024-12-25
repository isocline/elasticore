//ecd:477252944H20241223210702_V1.0
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
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CarLineupInfo extends LifecycleEntity implements java.io.Serializable  {

	/*
	  아이디
	*/
	@Id
	@Comment("아이디")
	@Column(name = "id", length = 12)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
	/*
	  시리즈명 // 예
	*/
	@Comment("시리즈명 // 예")
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	
	/*
	  브랜드 정보 참조
	*/
	@Comment("브랜드 정보 참조")
	@ManyToOne
	@JoinColumn(columnDefinition = "seriesInfo_id")
	private SeriesInfo seriesInfo;
	
	
	/*
	  모델명 // 예
	*/
	@Comment("모델명 // 예")
	@Column(name = "model_name", nullable = false, length = 100)
	private String modelName;
	
	
	/*
	  배기량
	*/
	@Comment("배기량")
	@Column(name = "displacement")
	private Long displacement;
	
	
	/*
	  연료 타입 // 예
	*/
	@Comment("연료 타입 // 예")
	@Column(name = "fuel_type", length = 10)
	private String fuelType;
	
	
}

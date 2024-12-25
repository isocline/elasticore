//ecd:1165299711H20241223210702_V1.0
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

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BASE")
@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class BaseCompanyCarInfo extends AuditEntity implements java.io.Serializable  {

	/*
	  차량 아이디
	*/
	@Id
	@Comment("차량 아이디")
	@Column(name = "car_id", length = 12)
	private String carId;
	
	
	@PrePersist
	public void prePersist() {
	  if (carId == null)
	        carId = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
	/*
	  차량 관리 업체
	*/
	@Comment("차량 관리 업체")
	@ManyToOne
	@JoinColumn(columnDefinition = "company_id")
	private BaseCompany company;
	
	
	@Column(name = "type", insertable = false, updatable = false)
	private String type;
	
	
	/*
	  시리즈 정보 참조
	*/
	@Comment("시리즈 정보 참조")
	@ManyToOne
	@JoinColumn(columnDefinition = "seriesInfo_id", nullable = false)
	private SeriesInfo seriesInfo;
	
	
	/*
	  모델명 // 예
	*/
	@Comment("모델명 // 예")
	@Column(name = "model_name", nullable = false, length = 100)
	private String modelName;
	
	
	/*
	  연료 타입 // 예
	*/
	@Comment("연료 타입 // 예")
	@Column(name = "fuel_type", length = 10)
	private String fuelType;
	
	
	/*
	  차량 기본가
	*/
	@Comment("차량 기본가")
	@Column(name = "car_base_price")
	private Long carBasePrice;
	
	
	/*
	  기존 차량정보
	*/
	@Comment("기존 차량정보")
	@ManyToOne
	@JoinColumn(columnDefinition = "baseCarInfo_id")
	private BaseCarInfo baseCarInfo;
	
	
	/*
	  유사도
	*/
	@Comment("유사도")
	@Column(name = "similarity")
	private Double similarity;
	
	
	@Column(name = "similarity_chk_date")
	private java.time.LocalDateTime similarityChkDate;
	
	
}

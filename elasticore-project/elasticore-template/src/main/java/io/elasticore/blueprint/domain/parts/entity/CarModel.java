//ecd:-2138694936H20250422232619_V1.0
package io.elasticore.blueprint.domain.parts.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import java.util.*;
import java.time.*;
import io.elasticore.blueprint.domain.parts.entity.*;
import jakarta.persistence.Entity;


/**
 * CarModel
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@jakarta.persistence.Table(name="epc_car_model")
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CarModel  implements java.io.Serializable  {

	public CarModel(String id) {
	    this.id = id;
	}
	
	/*
	  차량 모델 ID (UUID)
	*/
	@Id
	@Comment("차량 모델 ID (UUID)")
	@Column(name = "id", nullable = false, length = 36)
	private String id;
	
	
	/*
	  모델명
	*/
	@Comment("모델명")
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	
	/*
	  모델 이미지 경로(URL 또는 파일명)
	*/
	@Comment("모델 이미지 경로(URL 또는 파일명)")
	@Column(name = "img", length = 255)
	private String img;
	
	
	/*
	  카탈로그 참조 ID
	*/
	@Comment("카탈로그 참조 ID")
	@ManyToOne
	@JoinColumn(columnDefinition = "catalog_id")
	private Catalog catalog;
	
	
}

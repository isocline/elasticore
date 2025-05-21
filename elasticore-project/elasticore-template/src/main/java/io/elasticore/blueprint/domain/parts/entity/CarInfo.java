//ecd:2055651747H20250422232619_V1.0
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
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import io.elasticore.blueprint.domain.parts.entity.*;
import jakarta.persistence.Entity;


/**
 * CarInfo
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Audited
@Entity
@jakarta.persistence.Table(name="epc_car_info")
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CarInfo  implements java.io.Serializable  {

	public CarInfo(String id) {
	    this.id = id;
	}
	
	/*
	  차량 ID (UUID)
	*/
	@Id
	@Comment("차량 ID (UUID)")
	@Column(name = "id", nullable = false, length = 36)
	private String id;
	
	
	/*
	  차량 이름 또는 사용자 정의명
	*/
	@Comment("차량 이름 또는 사용자 정의명")
	@Column(name = "name", nullable = false, length = 300)
	private String name;
	
	
	/*
	  차량 상세 설명
	*/
	@Comment("차량 상세 설명")
	@Column(name = "description", length = 500)
	private String description;
	
	
	/*
	  연결된 모델 ID
	*/
	@Comment("연결된 모델 ID")
	@Column(name = "model_id", nullable = false, length = 36)
	private String modelId;
	
	
	/*
	  모델 이름 (조회용)
	*/
	@Comment("모델 이름 (조회용)")
	@Column(name = "model_name", length = 500)
	private String modelName;
	
	
	/*
	  차량 등록 기준
	*/
	@Comment("차량 등록 기준")
	@Column(name = "criteria", length = 500)
	private String criteria;
	
	
	/*
	  차량 브랜드명
	*/
	@Comment("차량 브랜드명")
	@Column(name = "brand", length = 50)
	private String brand;
	
	
	/*
	  부품 그룹 트리 지원 여부
	*/
	@Comment("부품 그룹 트리 지원 여부")
	@Column(name = "groups_tree_available")
	private Boolean groupsTreeAvailable = false;
	
	
	@ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@NotAudited
	private List<ParamInfo> parameters;
	
	
}

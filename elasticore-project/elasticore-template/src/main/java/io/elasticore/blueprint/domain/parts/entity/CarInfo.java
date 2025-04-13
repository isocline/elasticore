//ecd:54290779H20250412204313_V1.0
package io.elasticore.blueprint.domain.parts.entity;


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

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CarInfo  implements java.io.Serializable  {

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
	@Column(name = "name", nullable = false, length = 100)
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
	@Column(name = "model_name", nullable = false, length = 100)
	private String modelName;
	
	
	/*
	  차량 식별 번호 (Vehicle Identification Number)
	*/
	@Comment("차량 식별 번호 (Vehicle Identification Number)")
	@Column(name = "vin", length = 30)
	private String vin;
	
	
	/*
	  차체 번호
	*/
	@Comment("차체 번호")
	@Column(name = "frame", length = 50)
	private String frame;
	
	
	/*
	  차량 등록 기준
	*/
	@Comment("차량 등록 기준")
	@Column(name = "criteria", length = 100)
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
	@Column(name = "group_tree_availables")
	private Boolean groupTreeAvailables = false;
	
	
	/*
	  파라미터
	*/
	@Comment("파라미터")
	@ManyToMany(fetch = FetchType.LAZY )
	private List<ParamInfo> parameters;
	
	
}

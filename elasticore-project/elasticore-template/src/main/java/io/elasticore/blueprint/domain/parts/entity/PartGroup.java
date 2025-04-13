//ecd:-1604026480H20250410163649_V1.0
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
import jakarta.persistence.Entity;


/**
 * PartGroup
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
public  class PartGroup  implements java.io.Serializable  {

	/*
	  부품 그룹 ID
	*/
	@Id
	@Comment("부품 그룹 ID")
	@Column(name = "id", nullable = false, length = 36)
	private String id;
	
	
	/*
	  부품 그룹 이름
	*/
	@Comment("부품 그룹 이름")
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	
	/*
	  하위 그룹 존재 여부
	*/
	@Comment("하위 그룹 존재 여부")
	@Column(name = "has_subgroups", nullable = false)
	private Boolean hasSubgroups;
	
	
	/*
	  그룹 이미지
	*/
	@Comment("그룹 이미지")
	@Column(name = "img", length = 255)
	private String img;
	
	
	/*
	  그룹 설명
	*/
	@Comment("그룹 설명")
	@Column(name = "description", length = 500)
	private String description;
	
	
	/*
	  상위 그룹 ID
	*/
	@Comment("상위 그룹 ID")
	@Column(name = "parent_id", length = 36)
	private String parentId;
	
	
	/*
	  연결된 차량 ID
	*/
	@Comment("연결된 차량 ID")
	@Column(name = "car_id", nullable = false, length = 36)
	private String carId;
	
	
}

//ecd:-173081843H20250422232619_V1.0
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
 * PartGroup
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@jakarta.persistence.Table(name="epc_part_group")
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class PartGroup  implements java.io.Serializable  {

	public PartGroup(String id) {
	    this.id = id;
	}
	
	/*
	  부품 그룹 ID
	*/
	@Id
	@Comment("부품 그룹 ID")
	@Column(name = "id", nullable = false, length = 50)
	private String id;
	
	
	/*
	  부품 그룹 이름
	*/
	@Comment("부품 그룹 이름")
	@Column(name = "name", length = 100)
	private String name;
	
	
	@Column(name = "has_subgroups", nullable = false)
	private Boolean hasSubgroups = false;
	
	
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
	@Column(name = "description", length = 1000)
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
	
	
	/*
	  차량 등록 기준
	*/
	@Comment("차량 등록 기준")
	@Column(name = "criteria", length = 500)
	private String criteria;
	
	
	@Column(name = "brand", length = 100)
	private String brand;
	
	
	@Column(name = "img_description", length = 1000)
	private String imgDescription;
	
	
	@OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CarPart> parts;
	
	
	@OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PartPosition> positions;
	
	
}

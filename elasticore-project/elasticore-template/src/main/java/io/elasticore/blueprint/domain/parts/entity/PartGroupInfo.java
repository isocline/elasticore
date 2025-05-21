//ecd:-2115530895H20250422232619_V1.0
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
 * PartGroupInfo
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@jakarta.persistence.Table(name="epc_part_group_info")
@org.hibernate.annotations.DynamicUpdate
@IdClass(PartGroupInfoIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class PartGroupInfo  implements java.io.Serializable  {

	public PartGroupInfo(String carId,String groupId) {
	    this.carId = carId;    this.groupId = groupId;
	}
	
	/*
	  차량 ID (UUID)
	*/
	@Id
	@Comment("차량 ID (UUID)")
	@Column(name = "car_id", nullable = false, length = 36)
	private String carId;
	
	
	/*
	  부품 그룹 ID
	*/
	@Id
	@Comment("부품 그룹 ID")
	@Column(name = "group_id", nullable = false, length = 50)
	private String groupId;
	
	
	@Column(name = "img", length = 512)
	private String img;
	
	
	@Column(name = "img_description", length = 1000)
	private String imgDescription;
	
	
	@Column(name = "brand", length = 100)
	private String brand;
	
	
	@OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PartGroup> partGroups;
	
	
	@OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PartPosition> positions;
	
	
}

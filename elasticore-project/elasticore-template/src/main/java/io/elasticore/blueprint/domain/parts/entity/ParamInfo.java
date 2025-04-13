//ecd:547449399H20250412203017_V1.0
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
 * ParamInfo
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
public  class ParamInfo  implements java.io.Serializable  {

	/*
	  파라미터 고유 ID (UUID)
	*/
	@Id
	@Comment("파라미터 고유 ID (UUID)")
	@Column(name = "idx", nullable = false, length = 36)
	private String idx;
	
	
	/*
	  고유키
	*/
	@Comment("고유키")
	@Column(name = "key", nullable = false, length = 50)
	private String key;
	
	
	/*
	  파라미터 명칭
	*/
	@Comment("파라미터 명칭")
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	
	/*
	  파라미터 값
	*/
	@Comment("파라미터 값")
	@Column(name = "value", length = 200)
	private String value;
	
	
	/*
	  연결된 차량 ID
	*/
	@Comment("연결된 차량 ID")
	@Column(name = "car_id", nullable = false, length = 36)
	private String carId;
	
	
	/*
	  정렬 순서
	*/
	@Comment("정렬 순서")
	@Column(name = "sort_order", length = 5)
	private Integer sortOrder;
	
	
}

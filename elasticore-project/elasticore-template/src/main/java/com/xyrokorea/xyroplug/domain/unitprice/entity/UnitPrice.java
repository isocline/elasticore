//ecd:1226861134H20250401192833_V1.0
package com.xyrokorea.xyroplug.domain.unitprice.entity;


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
import com.xyrokorea.xyroplug.domain.channel.enums.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class UnitPrice extends LifecycleEntity implements java.io.Serializable  {

	/*
	  단가 ID
	*/
	@Id
	@Comment("단가 ID")
	@Column(name = "id", length = 30)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.xyrokorea.xyroplug.common.util.IdUtils.newId();
	}
	
	
	/*
	  메시지 타입
	*/
	@Comment("메시지 타입")
	@Column(length = 3)
	@Convert(converter = MessageType.EntityConverter.class)
	private MessageType msgType;
	
	
	/*
	  단가(원단위)
	*/
	@Comment("단가(원단위)")
	@Column(name = "price")
	private Integer price;
	
	
	@Column(name = "description", nullable = false, length = 200)
	private String description;
	
	
	/*
	  적용 대상 메시지 최소 크기
	*/
	@Comment("적용 대상 메시지 최소 크기")
	@Column(name = "length_min")
	private Integer lengthMin;
	
	
	/*
	  적용 대상 메시지 최대 크기
	*/
	@Comment("적용 대상 메시지 최대 크기")
	@Column(name = "length_max")
	private Integer lengthMax;
	
	
}

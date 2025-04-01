//ecd:-2036342590H20250401192833_V1.0
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
public  class TestInUnitPrice extends LifecycleEntity implements java.io.Serializable  {

	@Id
	@Column(name = "id", length = 30)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.xyrokorea.xyroplug.common.util.IdUtils.newId();
	}
	
	
	/*
	  최종 전송 메세지 타입
	*/
	@Comment("최종 전송 메세지 타입")
	@Column(length = 3)
	@Convert(converter = MessageType.EntityConverter.class)
	private MessageType msgType;
	
	
}

//ecd:372120073H20250422231129_V1.0
package io.elasticore.blueprint.domain.bbs.entity;

import io.elasticore.blueprint.domain.bbs.enums.*;
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
import jakarta.persistence.Entity;


/**
 * Message
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@jakarta.persistence.Table(
  indexes = {
    @jakarta.persistence.Index(name="idx_msg_create_date", columnList="create_date")
  }
  )
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Message  implements java.io.Serializable  {

	public Message(Long msgId) {
	    this.msgId = msgId;
	}
	
	/*
	  메세지 아이디
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("메세지 아이디")
	@Column(name = "msg_id")
	private Long msgId;
	
	
	/*
	  고객 채널 아이디
	*/
	@Comment("고객 채널 아이디")
	@Column(name = "cc_id", length = 30)
	private String ccId;
	
	
	/*
	  외부 채널 ID
	*/
	@Comment("외부 채널 ID")
	@Column(name = "ch_id", length = 30)
	private String chId;
	
	
	/*
	  발신자 정보(전화번호)
	*/
	@Comment("발신자 정보(전화번호)")
	@Column(name = "sender", length = 64)
	private String sender;
	
	
}

//ecd:-1564662960H20250410094431_V1.0
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
import java.util.*;
import java.time.*;
import io.elasticore.blueprint.domain.bbs.entity.*;
import jakarta.persistence.Entity;


/**
 * Article
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
public  class Article extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "aid")
	private String aid;
	
	
	@PrePersist
	public void prePersist() {
	  if (aid == null)
	        aid = java.util.UUID.randomUUID().toString();
	}
	
	
	/*
	  제목
	*/
	@Comment("제목")
	@Column(name = "title")
	private String title;
	
	
	/*
	  본문
	*/
	@Comment("본문")
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	
	/*
	  상위 게시판
	*/
	@Comment("상위 게시판")
	@ManyToOne
	@JoinColumn(columnDefinition = "board_id")
	private Board board;
	
	
}

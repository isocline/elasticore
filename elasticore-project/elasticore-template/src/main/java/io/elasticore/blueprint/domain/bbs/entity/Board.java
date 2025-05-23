//ecd:-350373932H20250522154454_V1.0
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
import io.elasticore.blueprint.domain.bbs.enums.*;
import io.elasticore.blueprint.domain.bbs.entity.*;
import jakarta.persistence.Entity;


/**
 * Board
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
public  class Board extends AuditEntity implements java.io.Serializable  {

	public Board(Long bid) {
	    this.bid = bid;
	}
	
	/*
	  게시판 아이디
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("게시판 아이디")
	@Column(name = "bid")
	private Long bid;
	
	
	/*
	  게시판 명
	*/
	@Comment("게시판 명")
	@Column(name = "name")
	private String name;
	
	
	/*
	  게시판 타입
	*/
	@Comment("게시판 타입")
	@Column(length = 2)
	@Convert(converter = BoardType.EntityConverter.class)
	private BoardType boardType;
	
	
	/*
	  테스트
	*/
	@Comment("테스트")
	@ElementCollection( fetch = FetchType.EAGER)
	@CollectionTable(name = "board_board_type_list", joinColumns = @JoinColumn(name = "map_seq"))
	@Column(name = "code")
	@Convert(converter = BoardType.EntityConverter.class)
	private List<BoardType> boardTypeList;
	
	
	@OneToMany(fetch = FetchType.LAZY ,mappedBy="companyId")
	private List<Article> articles;
	
	
}

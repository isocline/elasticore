//ecd:1444482098H20250402174055_V1.0
package com.xyrokorea.xyroplug.domain.channel.entity;

import com.xyrokorea.xyroplug.domain.channel.enums.*;
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
import com.xyrokorea.xyroplug.domain.channel.entity.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Article  implements java.io.Serializable  {

	/*
	  아이디
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("아이디")
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "title", nullable = false, length = 512)
	private String title;
	
	
	/*
	  본문
	*/
	@Comment("본문")
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	
	/*
	  작성자
	*/
	@Comment("작성자")
	@Column(name = "writer")
	private String writer;
	
	
	@ManyToOne
	@JoinColumn(columnDefinition = "board_id")
	private Board board;
	
	
	/*
	  읽은 수
	*/
	@Comment("읽은 수")
	@Column(name = "read_count")
	private Long readCount;
	
	
	/*
	  유저수
	*/
	@Comment("유저수")
	@Column(name = "user_count")
	private Integer userCount;
	
	
	/*
	  혼자글
	*/
	@Comment("혼자글")
	@Column(name = "private_yn")
	private Boolean privateYN;
	
	
}

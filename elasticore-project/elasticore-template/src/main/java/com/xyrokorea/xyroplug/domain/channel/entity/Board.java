//ecd:-789165158H20250402132512_V1.0
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
public  class Board  implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	/*
	  게시판명
	*/
	@Comment("게시판명")
	@Column(name = "name")
	private String name;
	
	
	@OneToMany(fetch = FetchType.LAZY ,mappedBy="board")
	private List<Article> articles;
	
	
}

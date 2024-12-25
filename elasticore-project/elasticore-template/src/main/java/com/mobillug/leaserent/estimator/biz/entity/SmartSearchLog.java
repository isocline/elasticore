//ecd:-1366069953H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.entity;

import com.mobillug.leaserent.estimator.biz.enums.*;
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


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class SmartSearchLog  implements java.io.Serializable  {

	/*
	  로그 아이디
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("로그 아이디")
	@Column(name = "log_id")
	private Long logId;
	
	
	/*
	  검색어
	*/
	@Comment("검색어")
	@Column(name = "search_query", length = 2000)
	private String searchQuery;
	
	
	/*
	  검색 결과
	*/
	@Comment("검색 결과")
	@Column(name = "search_result", columnDefinition = "TEXT")
	private String searchResult;
	
	
	/*
	  수행 시간 (밀리세컨드)
	*/
	@Comment("수행 시간 (밀리세컨드)")
	@Column(name = "execution_time")
	private Long executionTime;
	
	
	/*
	  응답 건수
	*/
	@Comment("응답 건수")
	@Column(name = "result_count")
	private Integer resultCount;
	
	
	/*
	  생성일시
	*/
	@Comment("생성일시")
	@Column(name = "create_date", updatable = false)
	@org.springframework.data.annotation.CreatedDate
	private java.time.LocalDateTime createDate;
	
	
	/*
	  사용자
	*/
	@Comment("사용자")
	@Column(name = "created_by", updatable = false, length = 20)
	@org.springframework.data.annotation.CreatedBy
	private String createdBy;
	
	
}

//ecd:1070848158H20241223210702_V1.0
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
public  class TransArea  implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;
	
	
	/*
	  그룹 아이디 (CM:공통, 또는 차량코드)
	*/
	@Comment("그룹 아이디 (CM:공통, 또는 차량코드)")
	@Column(name = "grp_cd", length = 20)
	private String grpCd;
	
	
	/*
	  도시 이이디
	*/
	@Comment("도시 이이디")
	@Column(name = "city_idx")
	private String cityIdx;
	
	
	/*
	  지역 아이디
	*/
	@Comment("지역 아이디")
	@Column(name = "area_idx")
	private String areaIdx;
	
	
	/*
	  지역명
	*/
	@Comment("지역명")
	@Column(name = "name")
	private String name;
	
	
}

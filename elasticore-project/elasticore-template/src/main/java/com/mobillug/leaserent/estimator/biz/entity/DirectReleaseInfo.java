//ecd:-1269661392H20241223210702_V1.0
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
import com.mobillug.leaserent.estimator.biz.entity.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class DirectReleaseInfo extends AuditEntity implements java.io.Serializable  {

	/*
	  순번 아이디
	*/
	@Id
	@Comment("순번 아이디")
	@Column(name = "id", length = 12)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
	/*
	  차량 정보 참조
	*/
	@Comment("차량 정보 참조")
	@ManyToOne
	@JoinColumn(columnDefinition = "baseCarInfo_id")
	private BaseCarInfo baseCarInfo;
	
	
	/*
	  선택 옵션 코드 리스트
	*/
	@Comment("선택 옵션 코드 리스트")
	@Column(name = "choice_option_code_list", length = 4000)
	@Convert(converter = com.mobillug.leaserent.estimator.common.utils.StringArrayConverter.class)
	private String[] choiceOptionCodeList;
	
	
	/*
	  선택 외장 색상 코드
	*/
	@Comment("선택 외장 색상 코드")
	@Column(name = "choice_outside_color_code", length = 10)
	private String choiceOutsideColorCode;
	
	
	/*
	  선택 내장 색상 코드
	*/
	@Comment("선택 내장 색상 코드")
	@Column(name = "choice_inner_color_code", length = 10)
	private String choiceInnerColorCode;
	
	
	/*
	  중복 차량 대수
	*/
	@Comment("중복 차량 대수")
	@Column(name = "duplicated_car_count")
	private Integer duplicatedCarCount;
	
	
	/*
	  캐피탈사 명
	*/
	@Comment("캐피탈사 명")
	@Column(name = "company_name", length = 20)
	private String companyName;
	
	
}

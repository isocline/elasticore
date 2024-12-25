//ecd:586863490H20241223210702_V1.0
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
import com.mobillug.leaserent.estimator.biz.enums.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class RcmdCarInfo extends LifecycleEntity implements java.io.Serializable  {

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
	  차량 정보 타입 (추천, 즉시출고)
	*/
	@Comment("차량 정보 타입 (추천, 즉시출고)")
	@Convert(converter = RecommendType.EntityConverter.class)
	private RecommendType recommendType;
	
	
	/*
	  캐피탈
	*/
	@Comment("캐피탈")
	@ManyToOne
	@JoinColumn(columnDefinition = "capital_id")
	private LeaseRentalCapital capital;
	
	
	/*
	  렌트 R /리스 L /렌트리스 RL
	*/
	@Comment("렌트 R /리스 L /렌트리스 RL")
	@Column(name = "estimate_type", length = 2)
	private String estimateType;
	
	
	/*
	  선택 car ID
	*/
	@Comment("선택 car ID")
	@Column(name = "car_id")
	private String carId;
	
	
	/*
	  선택 내부 색상
	*/
	@Comment("선택 내부 색상")
	@ManyToOne
	@JoinColumn(columnDefinition = "innerColor_id")
	private ColorInfo innerColor;
	
	
	/*
	  선택 외부 색상
	*/
	@Comment("선택 외부 색상")
	@ManyToOne
	@JoinColumn(columnDefinition = "outsideColor_id")
	private ColorInfo outsideColor;
	
	
	/*
	  선택 옵션 리스트
	*/
	@Comment("선택 옵션 리스트")
	@OneToMany(fetch = FetchType.LAZY )
	private List<CarOption> optionList;
	
	
	/*
	  기본 차량가격
	*/
	@Comment("기본 차량가격")
	@Column(name = "base_car_price")
	private Long baseCarPrice;
	
	
	/*
	  탁송비
	*/
	@Comment("탁송비")
	@Column(name = "consignment_price")
	private Long consignmentPrice;
	
	
	/*
	  36개월 할부 월 렌트료
	*/
	@Comment("36개월 할부 월 렌트료")
	@Column(name = "payment")
	private Long payment;
	
	
	/*
	  추가 부연 설명
	*/
	@Comment("추가 부연 설명")
	@Column(name = "extra_desc", columnDefinition = "TEXT")
	private String extraDesc;
	
	
	/*
	  만기 인수가
	*/
	@Comment("만기 인수가")
	@Column(name = "takeover")
	private Long takeover;
	
	
	/*
	  총비용
	*/
	@Comment("총비용")
	@Column(name = "total_amount")
	private Long totalAmount;
	
	
	/*
	  약정거리
	*/
	@Comment("약정거리")
	@Column(name = "distance")
	private Long distance;
	
	
	/*
	  취득원가
	*/
	@Comment("취득원가")
	@Column(name = "won")
	private Long won;
	
	
	/*
	  보증금
	*/
	@Comment("보증금")
	@Column(name = "deposit")
	private Long deposit;
	
	
	/*
	  선납금
	*/
	@Comment("선납금")
	@Column(name = "pre_exp")
	private Long preExp;
	
	
	/*
	  면책금
	*/
	@Comment("면책금")
	@Column(name = "em")
	private String em;
	
	
	/*
	  대물
	*/
	@Comment("대물")
	@Column(name = "ins")
	private String ins;
	
	
	/*
	  위약금
	*/
	@Comment("위약금")
	@Column(name = "penalty")
	private String penalty;
	
	
	/*
	  재고량
	*/
	@Comment("재고량")
	@Column(name = "stock_quantity")
	private Integer stockQuantity;
	
	
}

//ecd:1392443793H20241223210702_V1.0
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

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BASE")
@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class BaseCarInfo extends LifecycleEntity implements java.io.Serializable  {

	/*
	  차량 아이디
	*/
	@Id
	@Comment("차량 아이디")
	@Column(name = "car_id", length = 12)
	private String carId;
	
	
	@PrePersist
	public void prePersist() {
	  if (carId == null)
	        carId = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
	/*
	  rollup 대응 discriminator 타입정보
	*/
	@Comment("rollup 대응 discriminator 타입정보")
	@Column(name = "type", insertable = false, updatable = false)
	private String type;
	
	
	/*
	  라인업 정보 참조
	*/
	@Comment("라인업 정보 참조")
	@ManyToOne
	@JoinColumn(columnDefinition = "lineupInfo_id", nullable = false)
	private CarLineupInfo lineupInfo;
	
	
	/*
	  면세 여부 // 예
	*/
	@Comment("면세 여부 // 예")
	@Column(name = "tax_exempt")
	private Boolean taxExempt;
	
	
	/*
	  차량 기본가
	*/
	@Comment("차량 기본가")
	@Column(name = "car_base_price")
	private Long carBasePrice;
	
	
	/*
	  인승
	*/
	@Comment("인승")
	@Column(name = "seater")
	private Integer seater = 0;
	
	
	/*
	  트림 // 예
	*/
	@Comment("트림 // 예")
	@Column(name = "trim", length = 100)
	private String trim;
	
	
	/*
	  차량 풀네임 // 예
	*/
	@Comment("차량 풀네임 // 예")
	@Column(name = "car_full_name", nullable = false, length = 500)
	private String carFullName;
	
	
	/*
	  국가코드
	*/
	@Comment("국가코드")
	@Column(name = "nation", length = 2)
	private String nation;
	
	
	/*
	  차량 모델코드 // 예
	*/
	@Comment("차량 모델코드 // 예")
	@Column(name = "car_model_code", length = 10)
	private String carModelCode;
	
	
	/*
	  배기량
	*/
	@Comment("배기량")
	@Column(name = "displacement")
	private Integer displacement = 0;
	
	
	/*
	  연비
	*/
	@Comment("연비")
	@Column(name = "efficiency")
	private Float efficiency;
	
	
	/*
	  연료타입
	*/
	@Comment("연료타입")
	@Column(name = "fuel_name", length = 64)
	private String fuelName;
	
	
	/*
	  차량 타입
	*/
	@Comment("차량 타입")
	@Column(name = "classify_name", length = 64)
	private String classifyName;
	
	
	/*
	  연료타입
	*/
	@Comment("연료타입")
	@Convert(converter = FuelType.EntityConverter.class)
	private FuelType fuelType;
	
	
	/*
	  차량타입
	*/
	@Comment("차량타입")
	@Convert(converter = CarClassType.EntityConverter.class)
	private CarClassType carClassType;
	
	
	/*
	  내장 색상정보
	*/
	@Comment("내장 색상정보")
	@OneToMany(fetch = FetchType.LAZY )
	private List<ColorInfo> innerColors;
	
	
	/*
	  외장 색상 정보
	*/
	@Comment("외장 색상 정보")
	@OneToMany(fetch = FetchType.LAZY )
	private List<ColorInfo> outsideColor;
	
	
	/*
	  옵션정보
	*/
	@Comment("옵션정보")
	@OneToMany(fetch = FetchType.LAZY )
	private List<CarOption> options;
	
	
	/*
	  전장
	*/
	@Comment("전장")
	@Column(name = "overall_length")
	private Integer overallLength;
	
	
	/*
	  전폭
	*/
	@Comment("전폭")
	@Column(name = "overall_width")
	private Integer overallWidth;
	
	
	/*
	  전고
	*/
	@Comment("전고")
	@Column(name = "overall_height")
	private Integer overallHeight;
	
	
	/*
	  휠베이스
	*/
	@Comment("휠베이스")
	@Column(name = "wheelbase")
	private Integer wheelbase;
	
	
	/*
	  전륜 윤거
	*/
	@Comment("전륜 윤거")
	@Column(name = "tread_front")
	private Integer treadFront;
	
	
	/*
	  후륜 윤거
	*/
	@Comment("후륜 윤거")
	@Column(name = "tread_rear")
	private Integer treadRear;
	
	
	/*
	  공차중량
	*/
	@Comment("공차중량")
	@Column(name = "curb_weight")
	private Integer curbWeight;
	
	
	/*
	  최고출력
	*/
	@Comment("최고출력")
	@Column(name = "high_output")
	private Integer highOutput;
	
	
	/*
	  최대토크
	*/
	@Comment("최대토크")
	@Column(name = "max_torque")
	private Float maxTorque;
	
	
	/*
	  36개월 예상 렌트료
	*/
	@Comment("36개월 예상 렌트료")
	@Column(name = "payment36m")
	private Long payment36M;
	
	
	/*
	  48개월 예상 렌트료
	*/
	@Comment("48개월 예상 렌트료")
	@Column(name = "payment48m")
	private Long payment48M;
	
	
	/*
	  60개월 예상 렌트료
	*/
	@Comment("60개월 예상 렌트료")
	@Column(name = "payment60m")
	private Long payment60M;
	
	
}

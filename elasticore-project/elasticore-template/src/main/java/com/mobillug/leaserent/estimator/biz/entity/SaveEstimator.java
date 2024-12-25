//ecd:-1651726518H20241223210702_V1.0
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
public  class SaveEstimator extends AuditEntity implements java.io.Serializable  {

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
	  고객 정보
	*/
	@Comment("고객 정보")
	@Embedded
	private CustomerInfo customerInfo;
	
	
	/*
	  기본 차량가격 (+색상)
	*/
	@Comment("기본 차량가격 (+색상)")
	@Column(name = "base_car_price")
	private Long baseCarPrice;
	
	
	/*
	  옵션가격
	*/
	@Comment("옵션가격")
	@Column(name = "option_price")
	private Long optionPrice;
	
	
	/*
	  선택 차량 정보
	*/
	@Comment("선택 차량 정보")
	@ManyToOne
	@JoinColumn(columnDefinition = "carInfo_id")
	private BaseCarInfo carInfo;
	
	
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
	@JoinTable(name = "save_estimator_option_list")
	private List<CarOption> optionList;
	
	
	/*
	  내장 색상명칭
	*/
	@Comment("내장 색상명칭")
	@Column(name = "inner_color_name", length = 100)
	private String innerColorName;
	
	
	/*
	  추가 옵션 , ex
	*/
	@Comment("추가 옵션 , ex")
	@ElementCollection( fetch = FetchType.EAGER)
	@CollectionTable(name = "save_estimator_extra_opts", joinColumns = @JoinColumn(name = "map_seq"))
	@Column(name = "code")
	@Convert(converter = SaleExtraOption.EntityConverter.class)
	private List<SaleExtraOption> extraOpts;
	
	
	/*
	  탁송비
	*/
	@Comment("탁송비")
	@Column(name = "consignment_price")
	private Long consignmentPrice;
	
	
	/*
	  메모
	*/
	@Comment("메모")
	@Column(name = "memo", length = 150)
	private String memo;
	
	
	/*
	  견적분류  무심사렌트 LR / 무심사리스 LL / 일반렌트 RT  / 일반리스 LS
	*/
	@Comment("견적분류  무심사렌트 LR / 무심사리스 LL / 일반렌트 RT  / 일반리스 LS")
	@Column(name = "estimate_type", length = 2)
	private String estimateType;
	
	
	/*
	  월 렌트료
	*/
	@Comment("월 렌트료")
	@Column(name = "month_rental_price")
	private Long monthRentalPrice;
	
	
}

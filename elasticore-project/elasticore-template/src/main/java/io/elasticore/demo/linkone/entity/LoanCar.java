//ecd:-1152224740H20240527134244V0.7
package io.elasticore.demo.linkone.entity;

import io.elasticore.demo.linkone.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.hibernate.annotations.Comment;
import javax.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class LoanCar  implements java.io.Serializable  {
	// 아이디
	@Id
	@Comment("아이디")
	@Column(name = "lc_code")
	private Long lcCode;
	
	
	// ZZZ
	@Comment("ZZZ")
	@Column(name = "custom_name", nullable = false, length = 20)
	private String customName;
	
	
	// 연락처
	@Comment("연락처")
	@Column(name = "custom_ph")
	private String customPh;
	
	
	// 콜센터에서 링크원으로 보낸 알람 수신
	@Comment("콜센터에서 링크원으로 보낸 알람 수신")
	@Column(length = 5)
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator alarmStatus = Indicator.N;
	
	
	// 입고지
	@Comment("입고지")
	@Column(name = "lc_receive_location")
	private String lcReceiveLocation;
	
	
	// 문자내용
	@Comment("문자내용")
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	
	// 차량모델
	@Comment("차량모델")
	@Column(name = "car_model")
	private String carModel;
	
	
	// 차량번호
	@Comment("차량번호")
	@Column(name = "car_number")
	private String carNumber;
	
	
	// 메모
	@Comment("메모")
	@Column(name = "memo", columnDefinition = "TEXT")
	private String memo;
	
	
	// 보험사 접수번호
	@Comment("보험사 접수번호")
	@Column(name = "insurance_code", length = 32)
	private String insuranceCode;
	
	
	// 미제휴 공장명
	@Comment("미제휴 공장명")
	@Column(name = "lc_repair_shop_name", length = 64)
	private String lcRepairShopName;
	
	
	// 미제휴 공장기입 사유
	@Comment("미제휴 공장기입 사유")
	@Column(name = "lc_repair_shop_reason")
	private String lcRepairShopReason;
	
	
	// 사고타입 피해:D 가해:I 단독:S 정비:R 기타:E 미정:U
	@Comment("사고타입 피해:D 가해:I 단독:S 정비:R 기타:E 미정:U")
	@Convert(converter = AaccidentType.EntityConverter.class)
	private AaccidentType accidentType = AaccidentType.U;
	
	
	// 대차미진행 사유
	@Comment("대차미진행 사유")
	@Column(name = "lc_reason")
	private String lcReason;
	
	
	// 링크원에서 렌터카으로 보낸 알람 수신
	@Comment("링크원에서 렌터카으로 보낸 알람 수신")
	@Column(length = 5)
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator rentAlarmStatus = Indicator.N;
	
	
	// 목록 노출
	@Comment("목록 노출")
	@Column(length = 5)
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator lcView = Indicator.Y;
	
	
	// 대차미진행타입 수리만진행:R 지역변경:A 기타:E
	@Comment("대차미진행타입 수리만진행:R 지역변경:A 기타:E")
	private NoWayType noWayType;
	
	
	// 소개처(고객사기타)test
	@Comment("소개처(고객사기타)test")
	@Column(name = "agent_name", length = 64)
	private String agentName;
	
	
	// 영업담당자
	@Comment("영업담당자")
	@Column(name = "agent_pic_name", length = 64)
	private String agentPicName;
	
	
	// 지역(고객사기타)
	@Comment("지역(고객사기타)")
	@Column(name = "area_name", length = 64)
	private String areaName;
	
	
	// 고객사명 (고객사기타)
	@Comment("고객사명 (고객사기타)")
	@Column(name = "capital_name", length = 64)
	private String capitalName;
	
	
	// 문자파싱 성공여부
	@Comment("문자파싱 성공여부")
	@Column(length = 5)
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator smsParseType;
	
	
	@ManyToOne
	@JoinColumn(columnDefinition = "capital_id")
	private Capital capital;
	
	
	@Embedded
	private PictureInfo picInfo;
	
	
	private StatusType lcFixCode;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<CallInfo> callInfos;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<LoanCarProcess> processHistory;
	
	
	@Column(name = "create_date")
	private java.time.LocalDateTime createDate;
	
	
	@Column(name = "created_by")
	private String createdBy;
	
	
	@Column(name = "last_modified_by")
	private String lastModifiedBy;
	
	
	@Column(name = "last_modified_date")
	private java.time.LocalDateTime lastModifiedDate;
	
	
};

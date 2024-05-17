//ecd:-587613920H20240517105348V0.7
package io.elasticore.demo.linkone.entity;


import io.elasticore.demo.linkone.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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

	@Id
	@Comment("아이디")
	@Column(name = "lc_code")
	private Long lcCode;
	

	@Comment("고객명")
	@Column(name = "custom_name")
	private String customName;
	

	@Comment("연락처")
	@Column(name = "custom_ph")
	private String customPh;
	

	@Comment("콜센터에서 링크원으로 보낸 알람 수신")
	private Indicator alarmStatus = N;
	

	@Comment("입고지")
	@Column(name = "lc_receive_location")
	private String lcReceiveLocation;
	

	@Comment("문자내용")
	private text content;
	

	@Comment("차량모델")
	@Column(name = "car_model")
	private String carModel;
	

	@Comment("차량번호")
	@Column(name = "car_number")
	private String carNumber;
	

	@Comment("메모")
	private text memo;
	

	@Comment("보험사 접수번호")
	@Column(name = "insurance_code")
	private String insuranceCode;
	

	@Comment("미제휴 공장명")
	@Column(name = "lc_repair_shop_name")
	private String lcRepairShopName;
	

	@Comment("미제휴 공장기입 사유")
	@Column(name = "lc_repair_shop_reason")
	private String lcRepairShopReason;
	

	@Comment("사고타입 피해:D 가해:I 단독:S 정비:R 기타:E 미정:U")
	private AaccidentType accidentType = U;
	

	@Comment("대차미진행 사유")
	@Column(name = "lc_reason")
	private String lcReason;
	

	@Comment("링크원에서 렌터카으로 보낸 알람 수신")
	private Indicator rentAlarmStatus = N;
	

	@Comment("목록 노출")
	private Indicator lcView = Y;
	

	@Comment("대차미진행타입 수리만진행:R 지역변경:A 기타:E")
	private NoWayType noWayType;
	

	@Comment("'소개처(고객사기타")
	@Column(name = "agent_name")
	private String agentName;
	

	@Comment("영업담당자")
	@Column(name = "agent_pic_name")
	private String agentPicName;
	

	@Comment("'지역(고객사기타")
	@Column(name = "area_name")
	private String areaName;
	

	@Comment("'고객사명(고객사기타")
	@Column(name = "capital_name")
	private String capitalName;
	

	@Comment("문자파싱 성공여부")
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
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date createDate;
	

	@Column(name = "created_by")
	private String createdBy;
	

	@Column(name = "last_modified_by")
	private String lastModifiedBy;
	

	@Column(name = "last_modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date lastModifiedDate;
	


};

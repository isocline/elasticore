//ecd:388295944H20240524175232V0.7
package io.elasticore.demo.linkone.dto;


import io.elasticore.demo.linkone.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class LoanCarDTO  implements java.io.Serializable  {

	private Integer carSeq;
	private String contrNm;
	// 아이디
	private Long lcCode;
	// ZZZ
	private String customName;
	// 연락처
	private String customPh;
	// 콜센터에서 링크원으로 보낸 알람 수신
	private Indicator alarmStatus = Indicator.N;
	// 입고지
	private String lcReceiveLocation;
	// 문자내용
	private String content;
	// 차량모델
	private String carModel;
	// 차량번호
	private String carNumber;
	// 메모
	private String memo;
	// 보험사 접수번호
	private String insuranceCode;
	// 미제휴 공장명
	private String lcRepairShopName;
	// 미제휴 공장기입 사유
	private String lcRepairShopReason;
	// 사고타입 피해:D 가해:I 단독:S 정비:R 기타:E 미정:U
	private AaccidentType accidentType = AaccidentType.U;
	// 대차미진행 사유
	private String lcReason;
	// 링크원에서 렌터카으로 보낸 알람 수신
	private Indicator rentAlarmStatus = Indicator.N;
	// 목록 노출
	private Indicator lcView = Indicator.Y;
	// 대차미진행타입 수리만진행:R 지역변경:A 기타:E
	private NoWayType noWayType;
	// 소개처(고객사기타)test
	private String agentName;
	// 영업담당자
	private String agentPicName;
	// 지역(고객사기타)
	private String areaName;
	// 고객사명 (고객사기타)
	private String capitalName;
	// 문자파싱 성공여부
	private Indicator smsParseType;
	private StatusType lcFixCode;
	@Temporal(TemporalType.TIMESTAMP)
	private java.time.LocalDateTime createDate;
	private String createdBy;
	private String lastModifiedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private java.time.LocalDateTime lastModifiedDate;

};

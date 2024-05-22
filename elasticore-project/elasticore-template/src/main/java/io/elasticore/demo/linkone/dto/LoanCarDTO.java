//ecd:1468601341H20240521223026V0.7
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

	private Long lcCode;
	private String customName;
	private String customPh;
	private Indicator alarmStatus = Indicator.N;
	private String lcReceiveLocation;
	private String content;
	private String carModel;
	private String carNumber;
	private String memo;
	private String insuranceCode;
	private String lcRepairShopName;
	private String lcRepairShopReason;
	private AaccidentType accidentType = AaccidentType.U;
	private String lcReason;
	private Indicator rentAlarmStatus = Indicator.N;
	private Indicator lcView = Indicator.Y;
	private NoWayType noWayType;
	private String agentName;
	private String agentPicName;
	private String areaName;
	private String capitalName;
	private Indicator smsParseType;
	private StatusType lcFixCode;
	private Integer carSeq;
	private String contrNm;

};

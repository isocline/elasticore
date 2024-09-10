//ecd:-1776634803H20240904145011_V1.0
package com.xsolcorpkorea.kb.poc.px.model.request;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**
 * <pre>보험료Master</pre>

*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class PremiumMaster  implements java.io.Serializable  {

	/*
	  보장기간타입
	*/
	private String insurancePeriodType;
	
	/*
	  납입기간타입
	*/
	private String premiumPaymentType;
	
	/*
	  성별
	*/
	private String gender;
	
	/*
	  적용이율
	*/
	private Double appliedRate;
	
	/*
	  고액할인여부(1:할인, 0:할인하지 않음)
	*/
	private String bandDisClassCode;
	
	/*
	  연령
	*/
	private String age;
	
	/*
	  보험료단위
	*/
	private Double premiumUnit;
	
	/*
	  상품부코드(ex: 1->1종, 2->2종)
	*/
	private String insuranceType;
	
	/*
	  상품코드
	*/
	private String productComponentKey;
	
	/*
	  납입기간값
	*/
	private String premiumPaymentValue;
	
	/*
	  납입주기(3: 월납, 4: 3개월납, 5: 6개월납, 6: 연납)
	*/
	private String premiumMode;
	
	/*
	  보장기간값
	*/
	private String insurancePeriodValue;
	
	/*
	  사업비
	*/
	private List<Load> loadList;
	
	/*
	  해지유보효과
	*/
	private List<SurrenderSuspEffec> surrenderSuspEffecList;
	
	/*
	  위험율
	*/
	private List<RiskRate> riskRateList;
	
	/*
	  해지율
	*/
	private List<SurrenderRate> surrenderRateList;
	
	private List<ReserveCalc> reserveCalcList;
	
}

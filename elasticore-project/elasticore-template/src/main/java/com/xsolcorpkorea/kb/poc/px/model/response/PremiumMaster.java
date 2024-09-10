//ecd:-451579286H20240904145011_V1.0
package com.xsolcorpkorea.kb.poc.px.model.response;


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
	  월납, 3개월납, 6개월납, 연납 보험료를 계산하여 Set으로 구성(리턴값: 납입주기, 보험료)
	*/
	private List<HashMap> premiumSet_premiumMode = new ArrayList<HashMap>(Arrays.asList(new HashMap()));
	
	/*
	  보장기간값
	*/
	private String insurancePeriodValue;
	
	/*
	  보험료_계산식
	*/
	private Double premium;
	
	/*
	  사업비
	*/
	private List<Load> loadList = new ArrayList<Load>(Arrays.asList(new Load()));
	
	/*
	  해지유보효과
	*/
	private List<SurrenderSuspEffec> surrenderSuspEffecList = new ArrayList<SurrenderSuspEffec>(Arrays.asList(new SurrenderSuspEffec()));
	
	/*
	  위험율
	*/
	private List<RiskRate> riskRateList = new ArrayList<RiskRate>(Arrays.asList(new RiskRate()));
	
	/*
	  해지율
	*/
	private List<SurrenderRate> surrenderRateList = new ArrayList<SurrenderRate>(Arrays.asList(new SurrenderRate()));
	
	private List<ReserveCalc> reserveCalcList = new ArrayList<ReserveCalc>(Arrays.asList(new ReserveCalc()));
	
}

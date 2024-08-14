//ecd:-1582564626H20240806164538_V0.8
package io.elasticore.px.dto.response;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**
 * <pre></pre>

*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class Rider  implements java.io.Serializable  {

	private String paymentPeriodType;
	
	private String initialDate;
	
	private String insurancePeriodType;
	
	private String paymentPeriodEndDate;
	
	private Double faceAmount;
	
	private String insurancePeriodEndDate;
	
	private String initialAge;
	
	private String insurancePeriodValue;
	
	private String paymentPeriodValue;
	
	private Double reserveAmount;
	
	private Double simulatedRate;
	
	private Double premium;
	
	private List<RiskRate> riskRateList = new ArrayList<RiskRate>(Arrays.asList(new RiskRate()));
	
	private List<GMxBRate> gMxBRateList = new ArrayList<GMxBRate>(Arrays.asList(new GMxBRate()));
	
	private List<PremiumRate> premiumRateList = new ArrayList<PremiumRate>(Arrays.asList(new PremiumRate()));
	
	private List<ReserveRate> reserveRateList = new ArrayList<ReserveRate>(Arrays.asList(new ReserveRate()));
	
};

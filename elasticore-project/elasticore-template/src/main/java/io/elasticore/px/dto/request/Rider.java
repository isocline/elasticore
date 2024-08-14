//ecd:686139799H20240806164538_V0.8
package io.elasticore.px.dto.request;


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
	
	private Double faceAmount;
	
	private String initialAge;
	
	private String insurancePeriodValue;
	
	private String paymentPeriodValue;
	
	private Double simulatedRate;
	
	private List<RiskRate> riskRateList;
	
	private List<GMxBRate> gMxBRateList;
	
	private List<PremiumRate> premiumRateList;
	
	private List<ReserveRate> reserveRateList;
	
};

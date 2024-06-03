//ecd:-1992052291H20240603001815_V0.8
package io.elasticore.px.dto.response;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class PolicyCalculatedData  implements java.io.Serializable  {

	private Double mainPremium;
	
	private Double totalCashValue;
	
	private String durationYearAge;
	
	private Double coveragePremium;
	
	private Double accidentalDeathClaim;
	
	private Double refundRate;
	
	private String processCategory;
	
	private Double yearAndMonthETI;
	
	private Double totalPremiumPaid;
	
	private Double faceAmountRPU;
	
	private Double deathClaim;
	
	private Double mainCashValue;
	
	private String durationYear;
	
	private Double coverageCashValue;
	
	private String applicationNumber;
	
};

//ecd:-170362040H20240603001815_V0.8
package io.elasticore.px.dto.request;


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
public  class Rider  implements java.io.Serializable  {

	private String paymentPeriodType;
	
	private String insurancePeriodType;
	
	private String coverageNumber;
	
	private String paymentPeriodValue;
	
	private String productComponentKey;
	
	private Double inputPremium;
	
	private String insurancePeriodValue;
	
	private Double faceAmount;
	
	private String applicationNumber;
	
	private String premCalculationCategory;
	
};

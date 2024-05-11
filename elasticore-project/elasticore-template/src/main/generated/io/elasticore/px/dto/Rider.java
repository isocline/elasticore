package io.elasticore.px.dto;


//import io.elasticore.px.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
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
	

	private Double calculatedPremium;
	

	private String applicationNumber;
	

	private String premCalculationCategory;
	


};

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

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

public  class JavaPXPolicy  implements java.io.Serializable  {

	private Double totalPremium;
	

	private String coveragePremiumBasePlan;
	

	private Double totalYearlyPremium;
	

	private List<JavaPXRider> javaPXRiderList;
	


};

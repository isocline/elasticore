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

public  class JavaPXRider  implements java.io.Serializable  {

	private String coverageYearlyPremium;
	

	private String coveragePremium;
	

	private String coverageNumber;
	

	private String premiumMode;
	


};

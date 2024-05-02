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

public  class Policy  implements java.io.Serializable  {

	private String issueAge;
	

	private Double accidentalDeathClaim;
	

	private String standingInstructionMode;
	

	private Double deathClaim;
	

	private String productKey;
	

	private String gender;
	

	private String applicationNumber;
	

	private List<Rider> riderList;
	

	private List<PolicyCalculatedData> policyCalculatedDataList;
	


};

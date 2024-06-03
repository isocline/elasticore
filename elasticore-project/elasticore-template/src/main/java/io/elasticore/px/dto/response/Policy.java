//ecd:-475051034H20240603001815_V0.8
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

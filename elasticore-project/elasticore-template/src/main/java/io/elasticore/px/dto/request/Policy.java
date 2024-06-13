//ecd:-508313430H20240614012735_V0.8
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
public  class Policy  implements java.io.Serializable  {

	private String issueAge;
	
	private String standingInstructionMode;
	
	private String productKey;
	
	private String gender;
	
	private String applicationNumber;
	
	private List<Rider> riderList;
	
	private List<PolicyCalculatedData> policyCalculatedDataList;
	
};

//ecd:587451432H20240614012735_V0.8
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
public  class JavaPXPolicy  implements java.io.Serializable  {

	private Double totalPremium;
	
	private String coveragePremiumBasePlan;
	
	private Double totalYearlyPremium;
	
	private List<JavaPXRider> javaPXRiderList;
	
};

//ecd:-364593891H20240806164538_V0.8
package io.elasticore.px.dto.response;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**
 * <pre></pre>

*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class Policy  implements java.io.Serializable  {

	private Double totalReserveAmount;
	
	private List<Rider> riderList = new ArrayList<Rider>(Arrays.asList(new Rider()));
	
};

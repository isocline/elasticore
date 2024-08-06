//ecd:696966952H20240806161524_V0.8
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

	// id:ID:5dca282d-5a32-4ee3-8129-4328460aec5c
	// vid:ID:38fc2a94-c8ca-45d4-be98-a1c8a96e74a6
	// lid:ID:4a06d231-8560-4476-b65a-8e0adc4347e5
	// required:null
	private Double totalReserveAmount;
	
	// id:null
	// vid:null
	// lid:null
	// required:null
	private List<Rider> riderList = new ArrayList<Rider>(Arrays.asList(new Rider()));
	
};

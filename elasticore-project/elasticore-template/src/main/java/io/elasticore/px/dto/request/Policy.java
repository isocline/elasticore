//ecd:-1275879645H20240806161524_V0.8
package io.elasticore.px.dto.request;


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

	// id:null
	// vid:null
	// lid:null
	// required:null
	private List<Rider> riderList;
	
};

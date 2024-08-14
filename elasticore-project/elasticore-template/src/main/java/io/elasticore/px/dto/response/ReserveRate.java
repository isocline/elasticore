//ecd:2105388541H20240806171759_V0.8
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
public  class ReserveRate  implements java.io.Serializable  {

	// calcRequired:true
	private Double reserveRate;
	
	// calcRequired:true
	private String duration;
	
	// calcRequired:true
	private String reserveType;
	
};

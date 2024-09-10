//ecd:1641119780H20240904145011_V1.0
package com.xsolcorpkorea.kb.poc.px.model.request;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**
 * <pre>해지유보효과</pre>

*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class SurrenderSuspEffec  implements java.io.Serializable  {

	/*
	  납입기간값
	*/
	private String paymentPeriodValue;
	
	/*
	  경과기간
	*/
	private String duration;
	
	/*
	  해지유보효과값
	*/
	private Double surrenderSuspEffecValue;
	
}

//ecd:1994090060H20240904145011_V1.0
package com.xsolcorpkorea.kb.poc.px.model.response;


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
 * <pre>해지율</pre>

*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class SurrenderRate  implements java.io.Serializable  {

	/*
	  납입기간값
	*/
	private String paymentPeriodValue;
	
	/*
	  경과기간
	*/
	private String duration;
	
	/*
	  해지율값
	*/
	private Double surrenderValue;
	
}

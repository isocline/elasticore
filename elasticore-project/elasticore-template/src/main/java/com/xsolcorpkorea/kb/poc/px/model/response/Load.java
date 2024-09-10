//ecd:1833245964H20240904145011_V1.0
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
 * <pre>사업비</pre>

*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class Load  implements java.io.Serializable  {

	/*
	  납입기간값
	*/
	private String paymentPeriodValue;
	
	/*
	  사업비타입
	*/
	private String loadType;
	
	/*
	  사업비율
	*/
	private Double loadRate;
	
}

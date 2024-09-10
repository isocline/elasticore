//ecd:1813659170H20240904145011_V1.0
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
 * <pre></pre>

*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class ReserveCalc  implements java.io.Serializable  {

	/*
	  경과기간
	*/
	private String durationYear;
	
	/*
	  UVType(준비금타입)
	*/
	private String uVType;
	
}

//ecd:-504535534H20240904145011_V1.0
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
 * <pre>위험율</pre>

*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class RiskRate  implements java.io.Serializable  {

	/*
	  위험율코드
	*/
	private String qCode;
	
	/*
	  연령
	*/
	private String age;
	
	/*
	  위험율값
	*/
	private Double qValue;
	
	/*
	  성별
	*/
	private String gender;
	
}

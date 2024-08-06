//ecd:411981721H20240805175914_V0.8
package com.mobillug.linkone.apisvc.dto;

import com.mobillug.linkone.apisvc.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class Re120  implements java.io.Serializable  {

	/*
	  통화일자	yyyymmdd
	*/
	@Schema(description = "통화일자	yyyymmdd"  )
	private String callDate;
	
	/*
	  통화시간	hhmiss
	*/
	@Schema(description = "통화시간	hhmiss"  )
	private String callTime;
	
	/*
	  통화내용
	*/
	@Schema(description = "통화내용"  )
	private String bigo;
	


};

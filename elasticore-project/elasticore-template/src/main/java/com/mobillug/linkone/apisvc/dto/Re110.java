//ecd:1183623863H20240805175914_V0.8
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
public  class Re110  implements java.io.Serializable  {

	/*
	  차량확정여부
	*/
	@Schema(description = "차량확정여부"  )
	private String rentCarFixCode;
	
	/*
	  차량명
	*/
	@Schema(description = "차량명"  )
	private String rentCarFixNm;
	
	/*
	  특이사항
	*/
	@Schema(description = "특이사항"  )
	private String bigo;
	


};

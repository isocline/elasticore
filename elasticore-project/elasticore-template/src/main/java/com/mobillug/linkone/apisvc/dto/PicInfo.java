//ecd:985870013H20240805175914_V0.8
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
public  class PicInfo  implements java.io.Serializable  {

	/*
	  사진경로	/rent/yyyy/mm/dd/ + 대차접수번호
	*/
	@Schema(description = "사진경로	/rent/yyyy/mm/dd/ + 대차접수번호"  )
	private String rentPicPath;
	
	/*
	  사진명
	*/
	@Schema(description = "사진명"  )
	private String rentPicFilenm;
	


};

//ecd:-1354518044H20240805175914_V0.8
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
public  class Re130  implements java.io.Serializable  {

	/*
	  완료일자	yyyymmdd
	*/
	@Schema(description = "완료일자	yyyymmdd"  )
	private String rentCompDate;
	
	/*
	  완료시간	hhmiss
	*/
	@Schema(description = "완료시간	hhmiss"  )
	private String rentCompTime;
	
	/*
	  특이사항
	*/
	@Schema(description = "특이사항"  )
	private String bigo;
	
	/*
	  사진배열명
	*/
	@Schema(description = "사진배열명"  )
	private List<PicInfo> rentInfo;
	


};

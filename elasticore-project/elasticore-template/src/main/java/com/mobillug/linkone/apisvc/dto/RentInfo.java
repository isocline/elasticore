//ecd:384004626H20240805175914_V0.8
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
public  class RentInfo  implements java.io.Serializable  {

	/*
	  정산구분	01:대차계약서/02:정비/03:유류비/04:통행료/05:탁송비/06:세차/07:기타
	*/
	@Schema(description = "정산구분	01:대차계약서/02:정비/03:유류비/04:통행료/05:탁송비/06:세차/07:기타"  )
	private String rentGb;
	
	/*
	  정산비용
	*/
	@Schema(description = "정산비용"  )
	private String rentCost;
	
	/*
	  사진경로	/rent/yyyy/mm/dd/ + 대차접수번호
	*/
	@Schema(description = "사진경로	/rent/yyyy/mm/dd/ + 대차접수번호"  )
	private String rentPicPath;
	
	/*
	  사진이름
	*/
	@Schema(description = "사진이름"  )
	private String rentPicFilenm;
	


};

//ecd:-88006936H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.unitprice.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.xyrokorea.xyroplug.domain.channel.enums.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class UnitPriceKeyDTO  implements java.io.Serializable  {

	/*
	  단가 ID
	*/
	@Schema(description = "단가 ID"  )
	@Size(max=30)
	private String id;
	

}

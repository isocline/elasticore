//ecd:-1985314220H20250401183440_V1.0
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
public  class UnitPriceDTO  implements java.io.Serializable  {

	/*
	  메시지 타입
	*/
	@Schema(description = "메시지 타입"  )
	private MessageType msgType;
	
	/*
	  단가 ID
	*/
	@Schema(description = "단가 ID"  )
	@Size(max=30)
	private String id;
	
	/*
	  단가(원단위)
	*/
	@Schema(description = "단가(원단위)"  )
	private Integer price;
	
	@Schema(description = "description" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=200)
	private String description;
	
	/*
	  적용 대상 메시지 최소 크기
	*/
	@Schema(description = "적용 대상 메시지 최소 크기"  )
	private Integer lengthMin;
	
	/*
	  적용 대상 메시지 최대 크기
	*/
	@Schema(description = "적용 대상 메시지 최대 크기"  )
	private Integer lengthMax;
	

}

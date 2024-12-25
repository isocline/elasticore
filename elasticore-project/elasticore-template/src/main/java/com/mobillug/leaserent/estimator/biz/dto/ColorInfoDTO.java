//ecd:467200966H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.dto;

import com.mobillug.leaserent.estimator.biz.enums.*;
import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class ColorInfoDTO  implements java.io.Serializable  {

	/*
	  순번 아이디
	*/
	@Schema(description = "순번 아이디"  )
	@Size(max=12)
	private String id;
	
	/*
	  색상명 // 예
	*/
	@Schema(description = "색상명 // 예"  )
	@Size(max=256)
	private String colorName;
	
	/*
	  색상 프론트 코드 // 예
	*/
	@Schema(description = "색상 프론트 코드 // 예"  )
	@Size(max=20)
	private String colorFrontCode;
	
	/*
	  색상 코드 // 예
	*/
	@Schema(description = "색상 코드 // 예"  )
	@Size(max=20)
	private String colorCode;
	
	/*
	  색상 가격
	*/
	@Schema(description = "색상 가격"  )
	private Long colorPrice;
	
	/*
	  필요 옵션 // 예
	*/
	@Schema(description = "필요 옵션 // 예"  )
	@Size(max=4000)
	private String[] requiredOptions;
	
	/*
	  특정 색상 선택 시 사용불가 코드 // 예
	*/
	@Schema(description = "특정 색상 선택 시 사용불가 코드 // 예"  )
	@Size(max=4000)
	private String[] unavailableColorCodes;
	

}

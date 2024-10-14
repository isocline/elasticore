//ecd:126661432H20241014192241_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;


import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public  class ResidualMobillugDTO  implements java.io.Serializable  {

	@Schema(description = "period36"  )
	private Float period36;
	
	@Schema(description = "period48"  )
	private Float period48;
	
	@Schema(description = "period60"  )
	private Float period60;
	
	/*
	  아이디
	*/
	@Schema(description = "아이디"  )
	@Size(max=12)
	private String id;
	
	/*
	  rollup 대응 discriminator 타입정보
	*/
	@Schema(description = "rollup 대응 discriminator 타입정보"  )
	private String type;
	
	/*
	  잔가구분
	*/
	@Schema(description = "잔가구분"  )
	@Size(max=4)
	private String division;
	
	@Schema(description = "createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDate;
	

}

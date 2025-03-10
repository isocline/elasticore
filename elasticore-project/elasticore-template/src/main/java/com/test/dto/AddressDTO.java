//ecd:-2030175201H20250310224803_V1.0
package com.test.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class AddressDTO  implements java.io.Serializable  {

	/*
	  우편번호
	*/
	@Schema(description = "우편번호"  )
	@Size(max=5)
	private String postNo;
	
	/*
	  우편번호 2
	*/
	@Schema(description = "우편번호 2"  )
	@Size(max=7)
	private String postNo2;
	
	/*
	  기본 주소
	*/
	@Schema(description = "기본 주소"  )
	@Size(max=200)
	private String baseAddr;
	
	/*
	  상세 주소
	*/
	@Schema(description = "상세 주소"  )
	@Size(max=300)
	private String detailAddr;
	

}

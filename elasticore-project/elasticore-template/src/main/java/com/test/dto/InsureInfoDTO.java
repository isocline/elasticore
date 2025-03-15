//ecd:1900194844H20250313130133_V1.0
package com.test.dto;

import com.test.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.test.enums.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class InsureInfoDTO  implements java.io.Serializable  {

	/*
	  아이디
	*/
	@Schema(description = "아이디"  )
	@Size(max=12)
	private String id;
	
	@Schema(description = "id2"  )
	private Long id2;
	
	@Schema(description = "name"  )
	@Size(max=12)
	private String name;
	
	/*
	  고객 분류 / 기준2
	*/
	@Schema(description = "고객 분류 / 기준2"  , example="PR: 개인 | BS: 개인 사업자 | CP: 법인")
	private List<CustomerType> customerType;
	

}

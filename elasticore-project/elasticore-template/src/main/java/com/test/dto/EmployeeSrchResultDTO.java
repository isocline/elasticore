//ecd:-1785115882H20250311000811_V1.0
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
public  class EmployeeSrchResultDTO  implements java.io.Serializable  {

	@Schema(description = "id"  )
	private Integer id;
	
	@Schema(description = "name"  )
	private String name;
	
	/*
	  사원 번호 (자동 생성 및 유니크)
	*/
	@Schema(description = "사원 번호 (자동 생성 및 유니크)"  )
	@Size(max=15)
	private String empNo;
	

}

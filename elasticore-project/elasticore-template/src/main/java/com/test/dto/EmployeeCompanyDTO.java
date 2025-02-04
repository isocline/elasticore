//ecd:-2146871438H20250204104202_V1.0
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
import com.test2.enums.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class EmployeeCompanyDTO  implements java.io.Serializable  {

	/*
	  직원 ID
	*/
	@Schema(description = "직원 ID"  )
	private String employeeId;
	
	/*
	  직원 이름
	*/
	@Schema(description = "직원 이름"  )
	private String employeeName;
	
	/*
	  직원 나이
	*/
	@Schema(description = "직원 나이"  )
	private Integer employeeAge;
	
	/*
	  직원 주소
	*/
	@Schema(description = "직원 주소"  )
	private String employeeAddress;
	
	/*
	  성인 여부
	*/
	@Schema(description = "성인 여부"  )
	private Indicator isAdult;
	
	/*
	  회사 ID
	*/
	@Schema(description = "회사 ID"  )
	private String companyId;
	
	/*
	  회사 이름
	*/
	@Schema(description = "회사 이름"  )
	private String companyName;
	
	/*
	  회사 주소
	*/
	@Schema(description = "회사 주소"  )
	private String companyAddress;
	
	/*
	  회사 전화번호
	*/
	@Schema(description = "회사 전화번호"  )
	private String companyPhone;
	
	/*
	  회사 자본금
	*/
	@Schema(description = "회사 자본금"  )
	private Long companyCapital;
	

}

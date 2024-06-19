//ecd:1375499340H20240618012928_V0.8
package com.mobillug.linkone.biz.dto;

import com.mobillug.linkone.biz.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public  class CustUserSearchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  회사코드
	*/
	@Schema(description = "회사코드"  )
	private Long companyId;
	
	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	
	@Schema(description = "name" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=64)
	private String name;
	
	@Schema(description = "telNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=16)
	private String telNo;
	
	@Schema(description = "email"  )
	@Size(max=128)
	private String email;
	
	@Schema(description = "deptNm"  )
	@Size(max=60)
	private String deptNm;
	
	@Schema(description = "grade"  )
	@Size(max=60)
	private String grade;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

//ecd:1718262934H20240618012928_V0.8
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
public  class CommonCodeDTO  implements java.io.Serializable  {

	/*
	  코드 sequence
	*/
	@Schema(description = "코드 sequence"  )
	private Long codeSn;
	
	@Schema(description = "codeId" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=50)
	private String codeId;
	
	@Schema(description = "codeNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=50)
	private String codeNm;
	
	@Schema(description = "codeValue" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String codeValue;
	
	@Schema(description = "codeOptn1"  )
	@Size(max=50)
	private String codeOptn1;
	
	@Schema(description = "codeOptn2"  )
	@Size(max=50)
	private String codeOptn2;
	
	@Schema(description = "codeOptn3"  )
	@Size(max=50)
	private String codeOptn3;
	
	/*
	  코드 depth
	*/
	@Schema(description = "코드 depth"  )
	private Integer codeDepth = 1;
	
	/*
	  순서 정보
	*/
	@Schema(description = "순서 정보"  )
	private Integer codeOrder = 10;
	
	@Schema(description = "createDate"  )
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  )
	private java.time.LocalDateTime lastModifiedDate;
	
};

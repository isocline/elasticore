//ecd:-513066395H20240618012928_V0.8
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
public  class CommonCodeSearchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

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
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

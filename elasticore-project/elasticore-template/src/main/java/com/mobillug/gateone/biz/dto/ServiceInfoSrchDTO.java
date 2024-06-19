//ecd:-511045279H20240618012928_V0.8
package com.mobillug.gateone.biz.dto;

import com.mobillug.gateone.biz.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public  class ServiceInfoSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	
	@Schema(description = "name" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=64)
	private String name;
	
	@Schema(description = "keyName"  )
	@Size(max=8)
	private String keyName;
	
	@Schema(description = "mainUrl" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=256)
	private String mainUrl;
	
	@Schema(description = "loginUrl" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=256)
	private String loginUrl;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

//ecd:1853410515H20240618012928_V0.8
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
public  class ServiceInfoDTO  implements java.io.Serializable  {

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

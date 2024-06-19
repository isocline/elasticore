//ecd:398382283H20240618012928_V0.8
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
public  class MappingServiceDTO  implements java.io.Serializable  {

	@Schema(description = "serviceId"  )
	private String serviceId;
	
	@Schema(description = "serviceName"  )
	private String serviceName;
	
	@Schema(description = "serviceKeyName"  )
	private String serviceKeyName;
	
	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	
	/*
	  권한 정보 (관리자, 렌트카회원, 일반 사용자 등)
	*/
	@Schema(description = "권한 정보 (관리자, 렌트카회원, 일반 사용자 등)"  , example="A: 관리자 | M: 렌트카회원 | C: 일반 사용자")
	private UserRole role;
	
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

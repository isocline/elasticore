//ecd:-1223484806H20240618012928_V0.8
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
public  class LoginUserDTO  implements java.io.Serializable  {

	@Schema(description = "allowServieList"  )
	private List<MappingServiceDTO> allowServieList;
	
	@Schema(description = "password"  )
	private String password;
	
	public String getPassword() {
	    return null;
	}
	
	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	
	@Schema(description = "userId" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String userId;
	
	@Schema(description = "name" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=64)
	private String name;
	
	@Schema(description = "phone" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=16)
	private String phone;
	
	@Schema(description = "email"  )
	@Size(max=128)
	private String email;
	
	/*
	  아이디 상태 (정상, 패스워드 5회 실패, 정지)
	*/
	@Schema(description = "아이디 상태 (정상, 패스워드 5회 실패, 정지)"  , example="A: 정상 | F: 패스워드 5회 실패 | S: 정지")
	private UserStatus status;
	
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

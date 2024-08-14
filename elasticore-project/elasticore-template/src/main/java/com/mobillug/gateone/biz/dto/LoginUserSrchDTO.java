//ecd:-183562570H20240806171759_V0.8
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
public  class LoginUserSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  사용자 아이디
	*/
	@Schema(description = "사용자 아이디"  )
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
	
	/*
	  이메일 주소
	*/
	@Schema(description = "이메일 주소"  )
	@Size(max=128)
	private String email;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=30;
};

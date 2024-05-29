//ecd:972144549H20240529174205V0.7
package io.elasticore.demo.linkone.dto;

import io.elasticore.demo.linkone.enums.*;
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
public  class CustUserDTO  implements java.io.Serializable  {

	@Schema(description = "companyComSeq"  )
	private Long companyComSeq;
	
	@Schema(description = "company"  )
	private CompanyDTO company;
	
	@Schema(description = "usrSeq"  )
	private Long usrSeq;
	
	/*
	  아이디
	*/
	@Schema(description = "아이디" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String usrId;
	
	/*
	  패스워드
	*/
	@Schema(description = "패스워드" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=128)
	private String password;
	
	/*
	  이름
	*/
	@Schema(description = "이름" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=64)
	private String name;
	
	/*
	  전화번호
	*/
	@Schema(description = "전화번호" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=16)
	private String telNo;
	
	/*
	  이메일
	*/
	@Schema(description = "이메일"  )
	@Size(max=128)
	private String email;
	
	/*
	  부서
	*/
	@Schema(description = "부서"  )
	@Size(max=60)
	private String deptNm;
	
	/*
	  직급
	*/
	@Schema(description = "직급"  )
	@Size(max=60)
	private String grade;
	
	@Schema(description = "createDate"  )
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  )
	private java.time.LocalDateTime lastModifiedDate;
	
};

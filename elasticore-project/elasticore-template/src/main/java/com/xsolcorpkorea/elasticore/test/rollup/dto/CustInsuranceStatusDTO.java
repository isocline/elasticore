//ecd:-1722800607H20241207204629_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;

import com.xsolcorpkorea.elasticore.test.rollup.enums.*;
import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class CustInsuranceStatusDTO  implements java.io.Serializable  {

	@Schema(description = "custSeq" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=10)
	private String custSeq;
	
	/*
	  순번
	*/
	@Schema(description = "순번"  )
	@Size(max=5)
	private Integer seq;
	
	@Schema(description = "insuComNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String insuComNm;
	
	@Schema(description = "insuItemNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String insuItemNm;
	
	@Schema(description = "insuProdNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=100)
	private String insuProdNm;
	
	@Schema(description = "startYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String startYmd;
	
	@Schema(description = "endYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String endYmd;
	
	@Schema(description = "createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDate;
	
	/*
	  시스템 입력 IP
	*/
	@Schema(description = "시스템 입력 IP"  )
	@Size(max=20)
	private String createIP;
	
	/*
	  시스템 수정 IP
	*/
	@Schema(description = "시스템 수정 IP"  )
	@Size(max=20)
	private String lastModifiedIP;
	

}

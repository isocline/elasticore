//ecd:1394423353H20250312162715_V1.0
package com.test.dto;

import com.test.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.test.dto.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class CrmNotiInfoSrchDTO extends BaseSearchInput implements java.io.Serializable  {

	@Schema(description = "fromDate"  )
	private String fromDate;
	
	@Schema(description = "toDate"  )
	private String toDate;
	
	@Schema(description = "notiTy"  )
	private String notiTy;
	
	@Schema(description = "custNm"  )
	private String custNm;
	
	@Schema(description = "contactName"  )
	private String contactName;
	
	@Schema(description = "contactTel"  )
	private String contactTel;
	

}

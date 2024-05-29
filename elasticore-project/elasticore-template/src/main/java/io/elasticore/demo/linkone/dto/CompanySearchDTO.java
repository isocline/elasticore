//ecd:-163837699H20240529174205V0.7
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
public  class CompanySearchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "comSeq"  )
	private Long comSeq;
	
	/*
	  업체명
	*/
	@Schema(description = "업체명"  )
	@Size(max=64)
	private String comName;
	
	@Schema(description = "respName"  )
	@Size(max=64)
	private String respName;
	
	/*
	  담당자전화번호
	*/
	@Schema(description = "담당자전화번호"  )
	private String respTel;
	
	@Schema(description = "respZone"  )
	@Size(max=100)
	private String respZone;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

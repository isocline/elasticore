//ecd:384427841H20240531164142_V0.8
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
public  class CustUserSearchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "comSeq"  )
	private Long comSeq;
	
	@Schema(description = "usrSeq"  )
	private Long usrSeq;
	
	@Schema(description = "name" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=64)
	private String name;
	
	@Schema(description = "telNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=16)
	private String telNo;
	
	@Schema(description = "email"  )
	@Size(max=128)
	private String email;
	
	@Schema(description = "deptNm"  )
	@Size(max=60)
	private String deptNm;
	
	@Schema(description = "grade"  )
	@Size(max=60)
	private String grade;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

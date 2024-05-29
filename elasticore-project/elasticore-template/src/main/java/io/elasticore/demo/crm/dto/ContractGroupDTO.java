//ecd:-287886545H20240529174205V0.7
package io.elasticore.demo.crm.dto;

import io.elasticore.demo.crm.enums.*;
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
public  class ContractGroupDTO  implements java.io.Serializable  {

	@Schema(description = "carSeq"  )
	private Integer carSeq;
	
	@Schema(description = "contrNm"  )
	private String contrNm;
	
	/*
	  그릅 일련번호
	*/
	@Schema(description = "그릅 일련번호"  )
	private Integer grpSeq;
	
	/*
	  그룹명
	*/
	@Schema(description = "그룹명"  )
	@Size(max=200)
	private String groupName;
	
};

//ecd:-91793041H20240530103703_V0.8
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
public  class SelectCnctList4Output  implements java.io.Serializable  {

	@Schema(description = "contractNo"  )
	private String contractNo;
	
	@Schema(description = "driverSeqNo"  )
	private String driverSeqNo;
	
	@Schema(description = "custNo"  )
	private String custNo;
	
};

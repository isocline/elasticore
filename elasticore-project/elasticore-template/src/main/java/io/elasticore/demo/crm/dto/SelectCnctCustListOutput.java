//ecd:-1783076002H20240805175925_V0.8
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
public  class SelectCnctCustListOutput  implements java.io.Serializable  {

	@Schema(description = "carSeq"  )
	private Integer carSeq;
	
	@Schema(description = "contrNm"  )
	private String contrNm;
	
	@Schema(description = "custNm"  )
	private String custNm;
	
	@Schema(description = "contrTel"  )
	private String contrTel;
	
	@Schema(description = "rentAmt"  )
	private Integer rentAmt;
	
	@Schema(description = "exceptAmt"  )
	private Integer exceptAmt;
	
	@Schema(description = "penaltyRate"  )
	private String penaltyRate;
	
	@Schema(description = "contractNo"  )
	private String contractNo;
	
	@Schema(description = "driverSeqNo"  )
	private String driverSeqNo;
	
	@Schema(description = "custNo"  )
	private String custNo;
	
};

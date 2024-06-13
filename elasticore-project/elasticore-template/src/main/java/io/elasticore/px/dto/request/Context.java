//ecd:1084789895H20240614012735_V0.8
package io.elasticore.px.dto.request;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
public  class Context  implements java.io.Serializable  {

	private String environment;
	
	private String reversalReason;
	
	private String actionName;
	
	private String userId;
	
	private String redoIndicator;
	
	private String postedDate;
	
	private String operation;
	
	private String requestServiceName;
	
	private String effectiveDate;
	
	private String batchItemNumber;
	
	private String oid;
	
	private String instructionOid;
	
	private String parentPath;
	
	private String systemDate;
	
	private String commitIndicator;
	
};

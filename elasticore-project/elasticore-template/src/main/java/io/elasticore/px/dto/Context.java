package io.elasticore.px.dto;


//import io.elasticore.px.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
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

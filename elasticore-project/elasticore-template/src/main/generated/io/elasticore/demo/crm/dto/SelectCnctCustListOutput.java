package io.elasticore.demo.crm.dto;


//import io.elasticore.demo.crm.enums.*;

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

public  class SelectCnctCustListOutput  implements java.io.Serializable  {

	private Integer carSeq;
	

	private String contrNm;
	

	private String custNm;
	

	private String contrTel;
	

	private Integer rentAmt;
	

	private Integer exceptAmt;
	

	private String penaltyRate;
	

	private String contractNo;
	

	private String driverSeqNo;
	

	private String custNo;
	


};

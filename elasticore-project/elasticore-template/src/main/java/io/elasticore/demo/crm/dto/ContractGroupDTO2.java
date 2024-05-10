package io.elasticore.demo.crm.dto;


//import io.elasticore.demo.crm.enums.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;


/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class ContractGroupDTO2 implements java.io.Serializable  {

	public ContractGroupDTO2(Object[] objects) {
		this.carSeq = Integer.parseInt(objects[0].toString());
		this.contrNm = objects[1].toString();
		this.custName = objects[2].toString();
		this.contrTel = objects[3].toString();
	}

	private int carSeq;
	

	private String contrNm;
	

	private String custName;
	

	private String contrTel;
	


};

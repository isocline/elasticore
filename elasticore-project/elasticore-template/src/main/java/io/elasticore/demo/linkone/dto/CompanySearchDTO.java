//ecd:2135142321H20240528142512V0.7
package io.elasticore.demo.linkone.dto;

import io.elasticore.demo.linkone.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class CompanySearchDTO  implements java.io.Serializable, SortableObject, PageableObject  {
	private Long comSeq;
	
	// 업체명
	private String comName;
	
	private String respName;
	
	// 담당자전화번호
	private String respTel;
	
	private String respZone;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

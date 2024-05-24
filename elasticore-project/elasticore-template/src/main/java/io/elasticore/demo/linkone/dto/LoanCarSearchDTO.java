//ecd:-1872008115H20240524175232V0.7
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
public  class LoanCarSearchDTO  implements java.io.Serializable  {

	private String test;
	
	// 아이디
	private Long lcCode;
	
	// ZZZ
	private String customName;
	
	// 연락처
	private String customPh;
	
	private java.time.LocalDateTime createDateFrom;
	private java.time.LocalDateTime createDateTo;
	

};

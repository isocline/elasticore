//ecd:135664540H20240529100717V0.7
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
public  class CustUserSearchDTO  implements java.io.Serializable, SortableObject, PageableObject  {
	private Long comSeq;
	
	private Long usrSeq;
	
	// 이름
	private String name;
	
	// 전화번호
	private String telNo;
	
	// 이메일
	private String email;
	
	// 부서
	private String deptNm;
	
	// 직급
	private String grade;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

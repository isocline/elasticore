//ecd:-15971171H20240528142316V0.7
package io.elasticore.demo.crm.dto;

import io.elasticore.demo.crm.enums.*;
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
public  class ContractGroupDTO  implements java.io.Serializable  {
	private Integer carSeq;
	private String contrNm;
	// 그릅 일련번호
	private Integer grpSeq;
	// 그룹명
	private String groupName;
};

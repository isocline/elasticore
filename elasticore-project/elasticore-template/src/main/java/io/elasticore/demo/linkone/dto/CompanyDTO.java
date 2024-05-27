//ecd:1850035887H20240528005422V0.7
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
public  class CompanyDTO  implements java.io.Serializable  {
	private Long comSeq;
	private CompanyGroupCode comGrpCode;
	// 업체명
	private String comName;
	private String respName;
	// 담당자전화번호
	private String respTel;
	private String respZone;
	private java.time.LocalDateTime createDate;
	private String createdBy;
	private String lastModifiedBy;
	private java.time.LocalDateTime lastModifiedDate;
};

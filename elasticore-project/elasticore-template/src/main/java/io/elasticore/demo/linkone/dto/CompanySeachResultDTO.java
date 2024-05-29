//ecd:61612899H20240529100717V0.7
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
public  class CompanySeachResultDTO  implements java.io.Serializable  {
	private Long comSeq;
	private CompanyGroupCode comGrpCode;
	private String respName;
	// 담당자전화번호
	private String respTel;
	private String respZone;
	private java.time.LocalDateTime createDate;
	private String createdBy;
	private String lastModifiedBy;
	private java.time.LocalDateTime lastModifiedDate;
};

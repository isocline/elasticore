//ecd:72608902H20241207204629_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.dto;

import com.xsolcorpkorea.elasticore.test.rollup.enums.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class ContractPaymentDTO  implements java.io.Serializable  {

	@Schema(description = "policyNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=30)
	private String policyNo;
	
	@Schema(description = "insuComCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String insuComCd;
	
	/*
	  순번
	*/
	@Schema(description = "순번"  )
	@Size(max=5)
	private Integer seq;
	
	/*
	  배포 횟수
	*/
	@Schema(description = "배포 횟수"  )
	@Size(max=3)
	private Integer distrCnt;
	
	@Schema(description = "payYMM" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=6)
	private String payYMM;
	
	/*
	  납입 방법 코드
	*/
	@Schema(description = "납입 방법 코드"  )
	@Size(max=4)
	private String payMethodCd;
	
	@Schema(description = "payYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String payYmd;
	
	@Schema(description = "sumPrem" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Long sumPrem;
	
	/*
	  수정 보험료
	*/
	@Schema(description = "수정 보험료"  )
	private Long modifyPrem;
	
	@Schema(description = "payStateCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=3)
	private String payStateCd;
	
	/*
	  영수증 번호
	*/
	@Schema(description = "영수증 번호"  )
	@Size(max=30)
	private String rectNo;
	
	/*
	  요율 유형
	*/
	@Schema(description = "요율 유형"  )
	@Size(max=4)
	private String rateType;
	
	@Schema(description = "payAmt" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Long payAmt;
	
	/*
	  작업일자
	*/
	@Schema(description = "작업일자"  )
	@Size(max=8)
	private String jobDate;
	
	/*
	  마감 여부
	*/
	@Schema(description = "마감 여부"  , example="Y: | N:")
	private Indicator closYn;
	
	/*
	  계약번호 (이전)
	*/
	@Schema(description = "계약번호 (이전)"  )
	@Size(max=30)
	private String policyNo_BK;
	
	/*
	  계약번호 (신규)
	*/
	@Schema(description = "계약번호 (신규)"  )
	@Size(max=30)
	private String policyNo_New;
	
	@Schema(description = "createDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime lastModifiedDate;
	
	/*
	  시스템 입력 IP
	*/
	@Schema(description = "시스템 입력 IP"  )
	@Size(max=20)
	private String createIP;
	
	/*
	  시스템 수정 IP
	*/
	@Schema(description = "시스템 수정 IP"  )
	@Size(max=20)
	private String lastModifiedIP;
	

}

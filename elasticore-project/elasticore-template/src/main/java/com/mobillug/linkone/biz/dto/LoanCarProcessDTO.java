//ecd:870450192H20240618012928_V0.8
package com.mobillug.linkone.biz.dto;

import com.mobillug.linkone.biz.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public  class LoanCarProcessDTO  implements java.io.Serializable  {

	/*
	  렌트카업체코드
	*/
	@Schema(description = "렌트카업체코드"  )
	private String rentComId;
	
	/*
	  통화내역
	*/
	@Schema(description = "통화내역"  )
	private CallHistoryDTO callHistory;
	
	/*
	  순번 아이디
	*/
	@Schema(description = "순번 아이디"  )
	private Long lcpCode;
	
	/*
	  상태 프로세스
	*/
	@Schema(description = "상태 프로세스"  , example="RQ: 요청 | DL: 배송 | CL: 통화 | FL: 불가 | ET: 기타 | RT: 반납확인")
	private RentCarProcessType processType;
	
	@Schema(description = "applyDate"  )
	@Size(max=8)
	private String applyDate;
	
	@Schema(description = "applyTime"  )
	@Size(max=6)
	private String applyTime;
	
	@Schema(description = "memo"  )
	private String memo;
	
	@Schema(description = "loanCarMasterId"  )
	@Size(max=30)
	private String loanCarMasterId;
	
	@Schema(description = "custReqMemo"  )
	private String custReqMemo;
	
	@Schema(description = "reqCarName"  )
	@Size(max=30)
	private String reqCarName;
	
	@Schema(description = "reqCarNo"  )
	@Size(max=12)
	private String reqCarNo;
	
	/*
	  자차보험 서비스제공여부
	*/
	@Schema(description = "자차보험 서비스제공여부"  , example="Y: | N:")
	private Indicator insureYN = Indicator.NO;
	
	@Schema(description = "createDate"  )
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedBy"  )
	@Size(max=20)
	private String lastModifiedBy;
	
	@Schema(description = "lastModifiedDate"  )
	private java.time.LocalDateTime lastModifiedDate;
	
};

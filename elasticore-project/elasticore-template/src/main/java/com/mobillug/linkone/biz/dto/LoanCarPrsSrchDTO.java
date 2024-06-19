//ecd:1407673552H20240618012928_V0.8
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
public  class LoanCarPrsSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  렌트카업체코드
	*/
	@Schema(description = "렌트카업체코드"  )
	private String rentComId;
	
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
	
	@Schema(description = "memo"  )
	private String memo;
	
	@Schema(description = "loanCarMasterId"  )
	@Size(max=30)
	private String loanCarMasterId;
	
	@Schema(description = "custReqMemo"  )
	private String custReqMemo;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

//ecd:-1739467218H20240618012928_V0.8
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
public  class LoanCarSearchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  파트너사 코드
	*/
	@Schema(description = "파트너사 코드"  )
	private String rentComId;
	
	/*
	  파트너사 코드
	*/
	@Schema(description = "파트너사 코드"  )
	private String partCustCd;
	
	/*
	  접수일자 비교
	*/
	@Schema(description = "접수일자 비교"  )
	private java.time.LocalDateTime createDateFrom;
	private java.time.LocalDateTime createDateTo;
	
	/*
	  렌트카 업체명
	*/
	@Schema(description = "렌트카 업체명"  )
	private String rentCompanyName;
	
	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	
	@Schema(description = "lcCode"  )
	@Size(max=50)
	private String lcCode;
	
	/*
	  대차작업상태
	*/
	@Schema(description = "대차작업상태"  , example="RQ: 요청 | CF: 확정 | DL: 배송 | RT: 반납 | CG: 변경요청 | FL: 불가")
	private StatusType statusType;
	
	/*
	  사고유형
	*/
	@Schema(description = "사고유형"  , example="01: 피해 | 02: 정비 | 03: 가해 | 04: 단독 | 08: 미정 | 09: 기타")
	private RentKindType rentKind;
	
	@Schema(description = "rentCarCode"  )
	@Size(max=12)
	private String rentCarCode;
	
	@Schema(description = "rentCarNm"  )
	@Size(max=200)
	private String rentCarNm;
	
	@Schema(description = "custNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private String custNm;
	
	@Schema(description = "custTel" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private String custTel;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

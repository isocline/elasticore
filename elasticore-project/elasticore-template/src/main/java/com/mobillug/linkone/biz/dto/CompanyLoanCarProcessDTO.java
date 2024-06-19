//ecd:1794467360H20240618012928_V0.8
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
public  class CompanyLoanCarProcessDTO  implements java.io.Serializable  {

	/*
	  대차 마스터 코드
	*/
	@Schema(description = "대차 마스터 코드"  )
	private String id;
	
	/*
	  기록일시
	*/
	@Schema(description = "기록일시"  )
	private String createDate;
	
	/*
	  고객명
	*/
	@Schema(description = "고객명"  )
	private String custNm;
	
	/*
	  연락처
	*/
	@Schema(description = "연락처"  )
	private String custTel;
	
	/*
	  대차차량명
	*/
	@Schema(description = "대차차량명"  )
	private String rentCarNm;
	
	/*
	  상태 프로세스
	*/
	@Schema(description = "상태 프로세스"  )
	private String processType;
	
	/*
	  메모
	*/
	@Schema(description = "메모"  )
	private String memo;
	
	/*
	  적용일
	*/
	@Schema(description = "적용일"  )
	private String applyDate;
	
	/*
	  적용시간
	*/
	@Schema(description = "적용시간"  )
	private String applyTime;
	
	/*
	  고객요청사항
	*/
	@Schema(description = "고객요청사항"  )
	private String custReqMemo;
	
	/*
	  보험적용여부 (Y/N)
	*/
	@Schema(description = "보험적용여부 (Y/N)"  )
	private String insureyn;
	
	/*
	  상태 프로세스 이름
	*/
	@Schema(description = "상태 프로세스 이름"  )
	private String processTypeName;
	
	public String getProcessTypeName() {
	    return com.mobillug.linkone.biz.util.DTOUtils.getProcessTypeName(processType);
	}
	
};

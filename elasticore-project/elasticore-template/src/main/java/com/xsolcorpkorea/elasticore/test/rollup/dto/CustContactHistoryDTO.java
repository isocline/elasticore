//ecd:336628084H20241207204629_V1.0
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
public  class CustContactHistoryDTO  implements java.io.Serializable  {

	@Schema(description = "empNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String empNo;
	
	/*
	  접촉 시퀀스
	*/
	@Schema(description = "접촉 시퀀스"  )
	@Size(max=5)
	private Integer contactSeq;
	
	@Schema(description = "contactYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String contactYmd;
	
	@Schema(description = "contactTime" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=12)
	private String contactTime;
	
	/*
	  접촉 코드
	*/
	@Schema(description = "접촉 코드"  )
	@Size(max=2)
	private String contactCd;
	
	@Schema(description = "custNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=20)
	private String custNm;
	
	/*
	  고객 시퀀스
	*/
	@Schema(description = "고객 시퀀스"  )
	@Size(max=13)
	private String custSeq;
	
	/*
	  고객 번호
	*/
	@Schema(description = "고객 번호"  )
	@Size(max=13)
	private String custNo;
	
	/*
	  정책 번호
	*/
	@Schema(description = "정책 번호"  )
	@Size(max=30)
	private String policyNo;
	
	/*
	  오픈 여부
	*/
	@Schema(description = "오픈 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator openYN = Indicator.NO;
	
	/*
	  접촉 제목
	*/
	@Schema(description = "접촉 제목"  )
	@Size(max=100)
	private String contactTtl;
	
	/*
	  접촉 장소
	*/
	@Schema(description = "접촉 장소"  )
	@Size(max=100)
	private String contactPlace;
	
	@Schema(description = "contactDesc" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=300)
	private String contactDesc;
	
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

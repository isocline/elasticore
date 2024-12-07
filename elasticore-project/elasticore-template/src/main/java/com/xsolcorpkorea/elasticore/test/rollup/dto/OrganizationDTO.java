//ecd:2083101459H20241207204629_V1.0
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
public  class OrganizationDTO  implements java.io.Serializable  {

	@Schema(description = "orgCd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String orgCd;
	
	@Schema(description = "orgNm" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=300)
	private String orgNm;
	
	/*
	  조직 레벨 코드
	*/
	@Schema(description = "조직 레벨 코드"  )
	@Size(max=15)
	private String orgLevelCd;
	
	/*
	  상위 조직 코드
	*/
	@Schema(description = "상위 조직 코드"  )
	@Size(max=15)
	private String upOrgCd;
	
	/*
	  정렬 순서
	*/
	@Schema(description = "정렬 순서"  )
	private Long orderNo;
	
	/*
	  조직장 ID
	*/
	@Schema(description = "조직장 ID"  )
	@Size(max=15)
	private String orgBossId;
	
	/*
	  직통전화 1
	*/
	@Schema(description = "직통전화 1"  )
	@Size(max=4)
	private String directPhone1;
	
	/*
	  직통전화 2
	*/
	@Schema(description = "직통전화 2"  )
	@Size(max=4)
	private String directPhone2;
	
	/*
	  직통전화 3
	*/
	@Schema(description = "직통전화 3"  )
	@Size(max=4)
	private String directPhone3;
	
	/*
	  팩스 전화 1
	*/
	@Schema(description = "팩스 전화 1"  )
	@Size(max=4)
	private String faxPhone1;
	
	/*
	  팩스 전화 2
	*/
	@Schema(description = "팩스 전화 2"  )
	@Size(max=4)
	private String faxPhone2;
	
	/*
	  팩스 전화 3
	*/
	@Schema(description = "팩스 전화 3"  )
	@Size(max=4)
	private String faxPhone3;
	
	/*
	  우편번호 1
	*/
	@Schema(description = "우편번호 1"  )
	@Size(max=7)
	private String postNo1;
	
	/*
	  우편번호 2
	*/
	@Schema(description = "우편번호 2"  )
	@Size(max=7)
	private String postNo2;
	
	/*
	  기본 주소
	*/
	@Schema(description = "기본 주소"  )
	@Size(max=200)
	private String baseAddr;
	
	/*
	  상세 주소
	*/
	@Schema(description = "상세 주소"  )
	@Size(max=300)
	private String detailAddr;
	
	/*
	  개설일자
	*/
	@Schema(description = "개설일자"  )
	@Size(max=8)
	private String openYmd;
	
	/*
	  폐쇄일자
	*/
	@Schema(description = "폐쇄일자"  )
	@Size(max=8)
	private String closeYmd;
	
	/*
	  조직 유형 코드
	*/
	@Schema(description = "조직 유형 코드"  )
	@Size(max=15)
	private String orgTypeCd;
	
	/*
	  증감 조직 코드
	*/
	@Schema(description = "증감 조직 코드"  )
	@Size(max=15)
	private String increaseOrgCd;
	
	/*
	  검색 여부
	*/
	@Schema(description = "검색 여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator searchYN = Indicator.YES;
	
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

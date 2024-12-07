//ecd:402925795H20241207204629_V1.0
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
public  class EmployeeSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:empNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String empNo;
	
	/*
	  주민등록번호
	*/
	@Schema(description = "주민등록번호 Use 'like' if value has %, else 'equal' field:juminNo"  )
	@Size(max=13)
	private String juminNo;
	
	/*
	  한국 이름
	*/
	@Schema(description = "한국 이름 Use 'like' if value has %, else 'equal' field:korNm"  )
	@Size(max=50)
	private String korNm;
	
	/*
	  영어 이름
	*/
	@Schema(description = "영어 이름 Use 'like' if value has %, else 'equal' field:engNm"  )
	@Size(max=50)
	private String engNm;
	
	/*
	  조직 코드
	*/
	@Schema(description = "조직 코드 Use 'like' if value has %, else 'equal' field:orgCd"  )
	@Size(max=20)
	private String orgCd;
	
	/*
	  직위 코드
	*/
	@Schema(description = "직위 코드 Use 'like' if value has %, else 'equal' field:jobPositionCd"  )
	@Size(max=20)
	private String jobPositionCd;
	
	/*
	  직급 코드
	*/
	@Schema(description = "직급 코드 Use 'like' if value has %, else 'equal' field:jobGradeCd"  )
	@Size(max=20)
	private String jobGradeCd;
	
	/*
	  직책 코드
	*/
	@Schema(description = "직책 코드 Use 'like' if value has %, else 'equal' field:jobTitleCd"  )
	@Size(max=20)
	private String jobTitleCd;
	
	/*
	  경력 코드
	*/
	@Schema(description = "경력 코드 Use 'like' if value has %, else 'equal' field:careerCd"  )
	@Size(max=20)
	private String careerCd;
	
	/*
	  투자 여부
	*/
	@Schema(description = "투자 여부 Field equals value. field:inVtmentYn"  , example="Y: | N:")
	private Indicator inVtmentYn;
	
	/*
	  입사 일자 (YYYYMMDD)
	*/
	@Schema(description = "입사 일자 (YYYYMMDD) Use 'like' if value has %, else 'equal' field:enterYmd"  )
	@Size(max=8)
	private String enterYmd;
	
	/*
	  퇴사 일자 (YYYYMMDD)
	*/
	@Schema(description = "퇴사 일자 (YYYYMMDD) Use 'like' if value has %, else 'equal' field:retireYmd"  )
	@Size(max=8)
	private String retireYmd;
	
	/*
	  발령 일자 (YYYYMMDD)
	*/
	@Schema(description = "발령 일자 (YYYYMMDD) Use 'like' if value has %, else 'equal' field:orderYmd"  )
	@Size(max=8)
	private String orderYmd;
	
	/*
	  퇴사 사유
	*/
	@Schema(description = "퇴사 사유 Use 'like' if value has %, else 'equal' field:retireReason"  )
	@Size(max=100)
	private String retireReason;
	
	/*
	  생년월일 (YYYYMMDD)
	*/
	@Schema(description = "생년월일 (YYYYMMDD) Use 'like' if value has %, else 'equal' field:birthYmd"  )
	@Size(max=8)
	private String birthYmd;
	
	/*
	  휴대전화 1
	*/
	@Schema(description = "휴대전화 1 Use 'like' if value has %, else 'equal' field:mobilePhone1"  )
	@Size(max=4)
	private String mobilePhone1;
	
	/*
	  휴대전화 2
	*/
	@Schema(description = "휴대전화 2 Use 'like' if value has %, else 'equal' field:mobilePhone2"  )
	@Size(max=4)
	private String mobilePhone2;
	
	/*
	  휴대전화 3
	*/
	@Schema(description = "휴대전화 3 Use 'like' if value has %, else 'equal' field:mobilePhone3"  )
	@Size(max=70)
	private String mobilePhone3;
	
	/*
	  직통전화 1
	*/
	@Schema(description = "직통전화 1 Use 'like' if value has %, else 'equal' field:directPhone1"  )
	@Size(max=4)
	private String directPhone1;
	
	/*
	  직통전화 2
	*/
	@Schema(description = "직통전화 2 Use 'like' if value has %, else 'equal' field:directPhone2"  )
	@Size(max=4)
	private String directPhone2;
	
	/*
	  직통전화 3
	*/
	@Schema(description = "직통전화 3 Use 'like' if value has %, else 'equal' field:directPhone3"  )
	@Size(max=70)
	private String directPhone3;
	
	/*
	  이메일
	*/
	@Schema(description = "이메일 Use 'like' if value has %, else 'equal' field:email"  )
	@Size(max=100)
	private String email;
	
	/*
	  우편번호 1
	*/
	@Schema(description = "우편번호 1 Use 'like' if value has %, else 'equal' field:postNo1"  )
	@Size(max=3)
	private String postNo1;
	
	/*
	  우편번호 2
	*/
	@Schema(description = "우편번호 2 Use 'like' if value has %, else 'equal' field:postNo2"  )
	@Size(max=3)
	private String postNo2;
	
	/*
	  기본 주소
	*/
	@Schema(description = "기본 주소 Use 'like' if value has %, else 'equal' field:baseAddr"  )
	@Size(max=200)
	private String baseAddr;
	
	/*
	  상세 주소
	*/
	@Schema(description = "상세 주소 Use 'like' if value has %, else 'equal' field:detailAddr"  )
	@Size(max=300)
	private String detailAddr;
	
	/*
	  결혼 여부
	*/
	@Schema(description = "결혼 여부 Field equals value. field:marryYn"  , example="Y: | N:")
	private Indicator marryYn;
	
	/*
	  혈액형 코드
	*/
	@Schema(description = "혈액형 코드 Use 'like' if value has %, else 'equal' field:bloodCd"  )
	@Size(max=2)
	private String bloodCd;
	
	/*
	  종교 코드
	*/
	@Schema(description = "종교 코드 Use 'like' if value has %, else 'equal' field:religionCd"  )
	@Size(max=2)
	private String religionCd;
	
	/*
	  취미명
	*/
	@Schema(description = "취미명 Use 'like' if value has %, else 'equal' field:hobbyNm"  )
	@Size(max=200)
	private String hobbyNm;
	
	/*
	  헌혈 번호
	*/
	@Schema(description = "헌혈 번호 Use 'like' if value has %, else 'equal' field:ctbno"  )
	@Size(max=1000)
	private String ctbno;
	
	/*
	  생체 정보 유형
	*/
	@Schema(description = "생체 정보 유형 Use 'like' if value has %, else 'equal' field:bioTypeCd"  )
	@Size(max=1)
	private String bioTypeCd;
	
	/*
	  생명보험 가입 여부
	*/
	@Schema(description = "생명보험 가입 여부 Field equals value. field:lifeInsuYn"  , example="Y: | N:")
	private Indicator lifeInsuYn;
	
	/*
	  연금보험 가입 여부
	*/
	@Schema(description = "연금보험 가입 여부 Field equals value. field:pnpnSvn"  , example="Y: | N:")
	private Indicator pnpnSvn;
	
	/*
	  은행 코드
	*/
	@Schema(description = "은행 코드 Use 'like' if value has %, else 'equal' field:bankCd"  )
	@Size(max=20)
	private String bankCd;
	
	/*
	  계좌 번호
	*/
	@Schema(description = "계좌 번호 Use 'like' if value has %, else 'equal' field:accountNo"  )
	@Size(max=20)
	private String accountNo;
	
	/*
	  사진 파일명
	*/
	@Schema(description = "사진 파일명 Use 'like' if value has %, else 'equal' field:photoFileNm"  )
	@Size(max=150)
	private String photoFileNm;
	
	/*
	  이메일 사용 여부
	*/
	@Schema(description = "이메일 사용 여부 Field equals value. field:emailUseYn"  , example="Y: | N:")
	private Indicator emailUseYn;
	
	/*
	  전자통신 우편 여부
	*/
	@Schema(description = "전자통신 우편 여부 Field equals value. field:eCommP"  , example="Y: | N:")
	private Indicator eCommP;
	
	@Schema(description = "Field equals value. field:includeNumber" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	private Integer includeNumber;
	
	@Schema(description = "Field equals value. field:advancedPayment"  , example="Y: | N:")
	private Indicator advancedPayment;
	
	/*
	  장학금 여부
	*/
	@Schema(description = "장학금 여부 Field equals value. field:scholarship"  , example="Y: | N:")
	private Indicator scholarship;
	
	/*
	  장학금 시작일 (YYYYMM)
	*/
	@Schema(description = "장학금 시작일 (YYYYMM) Use 'like' if value has %, else 'equal' field:scholarshipStart"  )
	@Size(max=6)
	private String scholarshipStart;
	
	/*
	  장학금 종료일 (YYYYMM)
	*/
	@Schema(description = "장학금 종료일 (YYYYMM) Use 'like' if value has %, else 'equal' field:scholarshipEnd"  )
	@Size(max=6)
	private String scholarshipEnd;
	
	/*
	  육아 휴직 여부
	*/
	@Schema(description = "육아 휴직 여부 Field equals value. field:childCare"  , example="Y: | N:")
	private Indicator childCare;
	
	/*
	  임명 여부
	*/
	@Schema(description = "임명 여부 Field equals value. field:appoint"  , example="Y: | N:")
	private Indicator appoint;
	
	/*
	  임명 월 (YYYYMM)
	*/
	@Schema(description = "임명 월 (YYYYMM) Use 'like' if value has %, else 'equal' field:appointMonth"  )
	@Size(max=6)
	private String appointMonth;
	
	/*
	  소개 정책
	*/
	@Schema(description = "소개 정책 Use 'like' if value has %, else 'equal' field:introductionPolicy"  )
	@Size(max=1)
	private String introductionPolicy;
	
	/*
	  복귀 월 (YYYYMM)
	*/
	@Schema(description = "복귀 월 (YYYYMM) Use 'like' if value has %, else 'equal' field:revertMonth"  )
	@Size(max=6)
	private String revertMonth;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

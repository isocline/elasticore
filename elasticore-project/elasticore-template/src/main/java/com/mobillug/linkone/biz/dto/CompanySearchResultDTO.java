//ecd:1548813659H20240618012928_V0.8
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
public  class CompanySearchResultDTO  implements java.io.Serializable  {

	/*
	  전체 지역정보 텍스트 형태
	*/
	@Schema(description = "전체 지역정보 텍스트 형태"  )
	private String reaTextInfo;
	
	public String getReaTextInfo() {
	    return com.mobillug.linkone.biz.util.DTOUtils.getAreaInfo(areaCodeList);
	}
	
	@Schema(description = "partCustId"  )
	private String partCustId;
	
	@Schema(description = "partCustNm"  )
	private String partCustNm;
	
	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	
	/*
	  업체그룹코드
	*/
	@Schema(description = "업체그룹코드"  , example="CC: 콜센터 | GR: 공업사 | RC: 렌트카 | HD: 본사")
	private CompanyGroupCode comGrpCode;
	
	/*
	  담당지역
	*/
	@Schema(description = "담당지역"  , example="SU: 서울 | GG: 경기 | KW: 강원 | CC: 충청 | JL: 전라 | GS: 경상 | JJ: 제주")
	private List<AreaCode> areaCodeList;
	
	@Schema(description = "comName"  )
	@Size(max=64)
	private String comName;
	
	@Schema(description = "respName"  )
	@Size(max=64)
	private String respName;
	
	/*
	  담당자전화번호
	*/
	@Schema(description = "담당자전화번호"  )
	private String respTel;
	
	/*
	  업체활성화여부 Y:활성화 / N:비활성화
	*/
	@Schema(description = "업체활성화여부 Y:활성화 / N:비활성화"  , example="Y: | N:")
	private Indicator useYn;
	
	@Schema(description = "createDate"  )
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	@Size(max=20)
	private String createdBy;
	
	@Schema(description = "lastModifiedDate"  )
	private java.time.LocalDateTime lastModifiedDate;
	
};

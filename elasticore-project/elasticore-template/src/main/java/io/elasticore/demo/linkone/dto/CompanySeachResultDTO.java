//ecd:460774620H20240531125740_V0.8
package io.elasticore.demo.linkone.dto;

import io.elasticore.demo.linkone.enums.*;
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
public  class CompanySeachResultDTO  implements java.io.Serializable  {

	/*
	  전체 지역정보 텍스트 형태
	*/
	@Schema(description = "전체 지역정보 텍스트 형태"  )
	private String reaTextInfo;
	
	public String getReaTextInfo() {
	    return 'com.mobillug.linkone.biz.util.DTOUtils.getAreaInfo(areaCodeList)' ;
	}
	
	@Schema(description = "partCustId"  )
	private String partCustId;
	
	@Schema(description = "partCustNm"  )
	private String partCustNm;
	
	@Schema(description = "comSeq"  )
	private Long comSeq;
	
	@Schema(description = "comGrpCode"  , example="CC: 콜센터 | GR: 공업사 | RC: 렌트카 | HD: 본사")
	private CompanyGroupCode comGrpCode;
	
	/*
	  업체명
	*/
	@Schema(description = "업체명"  )
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
	
	@Schema(description = "createDate"  )
	private java.time.LocalDateTime createDate;
	
	@Schema(description = "createdBy"  )
	private String createdBy;
	
	@Schema(description = "lastModifiedDate"  )
	private java.time.LocalDateTime lastModifiedDate;
	
};

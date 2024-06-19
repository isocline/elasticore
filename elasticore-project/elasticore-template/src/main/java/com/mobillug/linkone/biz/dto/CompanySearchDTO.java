//ecd:-1420264025H20240618012928_V0.8
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
public  class CompanySearchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

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
	private AreaCode areaCodeListItem;
	
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
	
	@Schema(description = "respZone"  )
	@Size(max=100)
	private String respZone;
	
	/*
	  업체활성화여부 Y:활성화 / N:비활성화
	*/
	@Schema(description = "업체활성화여부 Y:활성화 / N:비활성화"  , example="Y: | N:")
	private Indicator useYn;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	private int pageSize=30;
};

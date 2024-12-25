//ecd:-748676513H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.dto;

import com.mobillug.leaserent.estimator.biz.enums.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.mobillug.leaserent.estimator.biz.enums.*;


/**


*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class TransCitySrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  자식 데이터 존재 여부
	*/
	@Schema(description = "자식 데이터 존재 여부 Field equals value. field:hasChild"  , example="Y: | N:")
	private Indicator hasChild;
	
	@Schema(description = "Field equals value. field:seq"  )
	private Long seq;
	
	/*
	  그룹 아이디 (CM:공통, 또는 차량코드)
	*/
	@Schema(description = "그룹 아이디 (CM:공통, 또는 차량코드) Use 'like' if value has %, else 'equal' field:grpCd"  )
	@Size(max=20)
	private String grpCd;
	
	/*
	  도시 이이디
	*/
	@Schema(description = "도시 이이디 Use 'like' if value has %, else 'equal' field:cityIdx"  )
	private String cityIdx;
	
	/*
	  도시명
	*/
	@Schema(description = "도시명 Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

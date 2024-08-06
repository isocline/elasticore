//ecd:2129041940H20240805175914_V0.8
package com.mobillug.linkone.apisvc.dto;

import com.mobillug.linkone.apisvc.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class Re100SrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	/*
	  아이디
	*/
	@Schema(description = "아이디"  )
	private Long id;
	
	/*
	  요청일자
	*/
	@Schema(description = "요청일자"  )
	private String reqDate;
	
	/*
	  요청시간
	*/
	@Schema(description = "요청시간"  )
	private String reqTime;
	
	
	private String sortCode;
	
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=30;


    private String sortColumn; // 정렬 컬럼
    private Boolean sortAscending; // sort ASC 정렬 여부 (true : ASC, false : DESC)
};

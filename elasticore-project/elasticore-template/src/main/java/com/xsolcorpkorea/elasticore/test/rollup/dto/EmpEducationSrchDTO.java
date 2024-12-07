//ecd:595935536H20241207204629_V1.0
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
public  class EmpEducationSrchDTO  implements java.io.Serializable, SortableObject, PageableObject  {

	@Schema(description = "Use 'like' if value has %, else 'equal' field:empNo" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=15)
	private String empNo;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:enterYmd" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=8)
	private String enterYmd;
	
	/*
	  졸업일자
	*/
	@Schema(description = "졸업일자 Use 'like' if value has %, else 'equal' field:outYmd"  )
	@Size(max=8)
	private String outYmd;
	
	/*
	  학교명
	*/
	@Schema(description = "학교명 Use 'like' if value has %, else 'equal' field:schoolNm"  )
	@Size(max=100)
	private String schoolNm;
	
	/*
	  학력코드
	*/
	@Schema(description = "학력코드 Use 'like' if value has %, else 'equal' field:schoolCareerCd"  )
	@Size(max=15)
	private String schoolCareerCd;
	
	/*
	  전공명
	*/
	@Schema(description = "전공명 Use 'like' if value has %, else 'equal' field:subjectNm"  )
	@Size(max=100)
	private String subjectNm;
	
	
	private String sortCode;
	
	@Builder.Default
	private int pageNumber=0;
	
	@Builder.Default
	private int pageSize=20;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

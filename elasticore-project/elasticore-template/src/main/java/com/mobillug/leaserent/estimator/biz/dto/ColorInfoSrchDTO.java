//ecd:1603065300H20241223210702_V1.0
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



/**


*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class ColorInfoSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  순번 아이디
	*/
	@Schema(description = "순번 아이디 Use 'like' if value has %, else 'equal' field:id"  )
	@Size(max=12)
	private String id;
	
	/*
	  색상명 // 예
	*/
	@Schema(description = "색상명 // 예 Use 'like' if value has %, else 'equal' field:colorName"  )
	@Size(max=256)
	private String colorName;
	
	/*
	  색상 프론트 코드 // 예
	*/
	@Schema(description = "색상 프론트 코드 // 예 Use 'like' if value has %, else 'equal' field:colorFrontCode"  )
	@Size(max=20)
	private String colorFrontCode;
	
	/*
	  색상 코드 // 예
	*/
	@Schema(description = "색상 코드 // 예 Use 'like' if value has %, else 'equal' field:colorCode"  )
	@Size(max=20)
	private String colorCode;
	
	/*
	  색상 가격
	*/
	@Schema(description = "색상 가격 Field equals value. field:colorPrice"  )
	private Long colorPrice;
	
	/*
	  필요 옵션 // 예
	*/
	@Schema(description = "필요 옵션 // 예 Field equals value. field:requiredOptions"  )
	@Size(max=4000)
	private String[] requiredOptions;
	
	/*
	  특정 색상 선택 시 사용불가 코드 // 예
	*/
	@Schema(description = "특정 색상 선택 시 사용불가 코드 // 예 Field equals value. field:unavailableColorCodes"  )
	@Size(max=4000)
	private String[] unavailableColorCodes;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

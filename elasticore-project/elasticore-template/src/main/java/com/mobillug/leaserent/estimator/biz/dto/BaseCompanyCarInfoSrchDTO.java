//ecd:1575928652H20241223210702_V1.0
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
public  class BaseCompanyCarInfoSrchDTO  implements java.io.Serializable, SortableObject  {

	@Schema(description = "Field equals value. field:companyComId"  )
	private String companyComId;
	
	@Schema(description = "Field equals value. field:seriesInfoId"  )
	private String seriesInfoId;
	
	@Schema(description = "Field equals value. field:baseCarInfoCarId"  )
	private String baseCarInfoCarId;
	
	/*
	  차량 아이디
	*/
	@Schema(description = "차량 아이디 Use 'like' if value has %, else 'equal' field:carId"  )
	@Size(max=12)
	private String carId;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:type"  )
	private String type;
	
	/*
	  모델명 // 예
	*/
	@Schema(description = "모델명 // 예 Use 'like' if value has %, else 'equal' field:modelName" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=100)
	private String modelName;
	
	/*
	  연료 타입 // 예
	*/
	@Schema(description = "연료 타입 // 예 Use 'like' if value has %, else 'equal' field:fuelType"  )
	private String fuelType;
	
	/*
	  차량 기본가
	*/
	@Schema(description = "차량 기본가 Field equals value. field:carBasePrice"  )
	private Long carBasePrice;
	
	/*
	  유사도
	*/
	@Schema(description = "유사도 Field equals value. field:similarity"  )
	private Double similarity;
	
	@Schema(description = "Field equals value. field:similarityChkDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime similarityChkDate;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

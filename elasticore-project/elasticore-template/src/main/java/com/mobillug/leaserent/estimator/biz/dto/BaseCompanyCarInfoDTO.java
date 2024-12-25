//ecd:873940027H20241223210702_V1.0
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
import com.mobillug.leaserent.estimator.biz.dto.*;


/**


*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class BaseCompanyCarInfoDTO  implements java.io.Serializable  {

	@Schema(description = "seriesInfo"  )
	private SeriesInfoDTO seriesInfo;
	
	@Schema(description = "baseCarInfo"  )
	private BaseCarInfoDTO baseCarInfo;
	
	@Schema(description = "companyComId"  )
	private String companyComId;
	
	@Schema(description = "seriesInfoId"  )
	private String seriesInfoId;
	
	@Schema(description = "baseCarInfoCarId"  )
	private String baseCarInfoCarId;
	
	/*
	  차량 아이디
	*/
	@Schema(description = "차량 아이디"  )
	@Size(max=12)
	private String carId;
	
	@Schema(description = "type"  )
	private String type;
	
	/*
	  모델명 // 예
	*/
	@Schema(description = "모델명 // 예" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=100)
	private String modelName;
	
	/*
	  연료 타입 // 예
	*/
	@Schema(description = "연료 타입 // 예"  )
	private String fuelType;
	
	/*
	  차량 기본가
	*/
	@Schema(description = "차량 기본가"  )
	private Long carBasePrice;
	
	/*
	  유사도
	*/
	@Schema(description = "유사도"  )
	private Double similarity;
	
	@Schema(description = "similarityChkDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime similarityChkDate;
	
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
	

}

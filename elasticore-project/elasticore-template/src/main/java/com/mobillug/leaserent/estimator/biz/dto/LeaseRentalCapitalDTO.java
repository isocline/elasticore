//ecd:1979512982H20241223210702_V1.0
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
public  class LeaseRentalCapitalDTO  implements java.io.Serializable  {

	/*
	  캐피탈 아이디
	*/
	@Schema(description = "캐피탈 아이디"  )
	@Size(max=12)
	private String id;
	
	@Schema(description = "name"  )
	private String name;
	
	/*
	  차량 정보 적용일시 예
	*/
	@Schema(description = "차량 정보 적용일시 예"  )
	@Size(max=8)
	private String effectiveDate;
	
	/*
	  차량 정보 종료일시,null 또 공백시 설정 안됨. 예
	*/
	@Schema(description = "차량 정보 종료일시,null 또 공백시 설정 안됨. 예"  )
	@Size(max=8)
	@Builder.Default
	private String endDate = "99991231";
	
	/*
	  트랜잭션 아이디
	*/
	@Schema(description = "트랜잭션 아이디"  )
	@Size(max=12)
	private String txId;
	
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

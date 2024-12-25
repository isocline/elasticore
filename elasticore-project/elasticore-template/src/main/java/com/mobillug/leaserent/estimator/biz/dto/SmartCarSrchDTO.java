//ecd:1942195930H20241223210702_V1.0
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
public  class SmartCarSrchDTO  implements java.io.Serializable  {

	/*
	  차량 관련 정보(업체명 또는 모델명 라인업명등 공백으로 구분하여 설정)
	*/
	@Schema(description = "차량 관련 정보(업체명 또는 모델명 라인업명등 공백으로 구분하여 설정)"  )
	private String keyword;
	

}

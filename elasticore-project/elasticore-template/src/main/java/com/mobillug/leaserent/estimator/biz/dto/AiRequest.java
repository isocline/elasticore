//ecd:219880147H20241223210702_V1.0
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
public  class AiRequest  implements java.io.Serializable  {

	/*
	  질문내용
	*/
	@Schema(description = "질문내용"  )
	private String question;
	
	/*
	  모드 무심사인 경우 L
	*/
	@Schema(description = "모드 무심사인 경우 L"  )
	private String mode;
	

}

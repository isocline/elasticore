//ecd:1214487537H20250312162715_V1.0
package com.test.dto;

import com.test.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class CustNoticesDTO  implements java.io.Serializable  {

	/*
	  알림 시퀀스
	*/
	@Schema(description = "알림 시퀀스"  )
	private Long notiSeq;
	
	/*
	  알림 캠페인 시퀀스
	*/
	@Schema(description = "알림 캠페인 시퀀스"  )
	private Long notiCpgSeq;
	
	/*
	  등록일시
	*/
	@Schema(description = "등록일시"  )
	private String insDtm;
	
	/*
	  알림 유형
	*/
	@Schema(description = "알림 유형"  )
	private String notiTy;
	
	/*
	  메시지 내용
	*/
	@Schema(description = "메시지 내용"  )
	private String msg;
	
	/*
	  템플릿 제목
	*/
	@Schema(description = "템플릿 제목"  )
	private String title;
	
	/*
	  알림 명칭
	*/
	@Schema(description = "알림 명칭"  )
	private String notiNm;
	
	/*
	  고객명
	*/
	@Schema(description = "고객명"  )
	private String custNm;
	

}

//ecd:1978618325H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.channel.dto;

import com.xyrokorea.xyroplug.domain.channel.enums.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;
import com.xyrokorea.xyroplug.domain.channel.enums.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class TestInChannelDTO  implements java.io.Serializable  {

	/*
	  메시지 타입
	*/
	@Schema(description = "메시지 타입"  , example="SMS: 단문문자 | LMS: 장문문자 | CALL: 전화발신 | KAKAO: 카카오톡 | OBC: 전화발신 | FAX: 팩스")
	private MessageType msgType;
	
	@Schema(description = "id"  )
	@Size(max=30)
	private String id;
	

}

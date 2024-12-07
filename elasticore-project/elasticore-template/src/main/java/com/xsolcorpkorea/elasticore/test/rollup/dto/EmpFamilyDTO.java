//ecd:307983864H20241207204629_V1.0
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
public  class EmpFamilyDTO  implements java.io.Serializable  {

	/*
	  사원번호
	*/
	@Schema(description = "사원번호"  )
	@Size(max=15)
	private String empNo;
	
	/*
	  순번
	*/
	@Schema(description = "순번"  )
	@Size(max=5)
	private Long seq;
	
	/*
	  주민번호
	*/
	@Schema(description = "주민번호"  )
	@Size(max=13)
	private String juminNo;
	
	/*
	  가족관계명
	*/
	@Schema(description = "가족관계명"  )
	@Size(max=30)
	private String familyRelNm;
	
	/*
	  이름
	*/
	@Schema(description = "이름"  )
	@Size(max=20)
	private String name;
	
	/*
	  기타사항
	*/
	@Schema(description = "기타사항"  )
	@Size(max=50)
	private String etc;
	
	/*
	  동거여부
	*/
	@Schema(description = "동거여부"  , example="Y: | N:")
	@Builder.Default
	private Indicator togetherYn = Indicator.NO;
	
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
	
	/*
	  시스템 입력 IP
	*/
	@Schema(description = "시스템 입력 IP"  )
	@Size(max=20)
	private String createIP;
	
	/*
	  시스템 수정 IP
	*/
	@Schema(description = "시스템 수정 IP"  )
	@Size(max=20)
	private String lastModifiedIP;
	

}

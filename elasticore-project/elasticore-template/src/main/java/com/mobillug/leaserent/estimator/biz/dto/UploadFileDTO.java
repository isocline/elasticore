//ecd:-791725950H20241223210702_V1.0
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
import com.mobillug.leaserent.estimator.biz.enums.*;


/**


*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class UploadFileDTO  implements java.io.Serializable  {

	/*
	  업로드 종류
	*/
	@Schema(description = "업로드 종류"  , example="CI: 차량 이미지 | LG: 브랜드이미지")
	private FileKind fileKind;
	
	/*
	  아이디
	*/
	@Schema(description = "아이디"  )
	@Size(max=12)
	private String id;
	
	@Schema(description = "fileUrl" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=512)
	private String fileUrl;
	
	/*
	  경로정보
	*/
	@Schema(description = "경로정보"  )
	@Size(max=512)
	private String filePath;
	
	/*
	  파일 타입
	*/
	@Schema(description = "파일 타입"  )
	@Size(max=16)
	private String fileType;
	
	/*
	  파일 크기
	*/
	@Schema(description = "파일 크기"  )
	private Long size;
	
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

//ecd:1954813191H20241223210702_V1.0
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
public  class UploadFileSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  업로드 종류
	*/
	@Schema(description = "업로드 종류 Field equals value. field:fileKind"  , example="CI: 차량 이미지 | LG: 브랜드이미지")
	private FileKind fileKind;
	
	/*
	  아이디
	*/
	@Schema(description = "아이디 Use 'like' if value has %, else 'equal' field:id"  )
	@Size(max=12)
	private String id;
	
	@Schema(description = "Field equals value. field:fileUrl" , requiredMode=Schema.RequiredMode.REQUIRED )
	@NotNull
	@Size(max=512)
	private String fileUrl;
	
	/*
	  경로정보
	*/
	@Schema(description = "경로정보 Use 'like' if value has %, else 'equal' field:filePath"  )
	@Size(max=512)
	private String filePath;
	
	/*
	  파일 타입
	*/
	@Schema(description = "파일 타입 Use 'like' if value has %, else 'equal' field:fileType"  )
	@Size(max=16)
	private String fileType;
	
	/*
	  파일 크기
	*/
	@Schema(description = "파일 크기 Field equals value. field:size"  )
	private Long size;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

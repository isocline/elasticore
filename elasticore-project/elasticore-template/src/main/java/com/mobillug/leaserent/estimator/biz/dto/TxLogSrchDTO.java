//ecd:-463416741H20241223210702_V1.0
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
public  class TxLogSrchDTO  implements java.io.Serializable, SortableObject  {

	@Schema(description = "Field equals value. field:txType" , requiredMode=Schema.RequiredMode.REQUIRED , example="CI: 차량정보 동기화")
	@NotNull
	private TxType txType;
	
	@Schema(description = "Field equals value. field:txStatus" , requiredMode=Schema.RequiredMode.REQUIRED , example="SC: 성공 | FL: 실패")
	@NotNull
	private TxStatus txStatus;
	
	/*
	  Transaction 아이디
	*/
	@Schema(description = "Transaction 아이디 Use 'like' if value has %, else 'equal' field:txId"  )
	private String txId;
	
	/*
	  podName
	*/
	@Schema(description = "podName Use 'like' if value has %, else 'equal' field:podName"  )
	@Size(max=200)
	private String podName;
	
	@Schema(description = "Field matches value (case-insensitive) with identical content between start and end. field:title"  )
	@Size(max=2000)
	private String title;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:logTxt"  )
	private String logTxt;
	
	@Schema(description = "Field equals value. field:startDate" , requiredMode=Schema.RequiredMode.REQUIRED , example="yyyy-MM-dd HH:mm:ss")
	@NotNull
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.time.LocalDateTime startDate;
	
	/*
	  생성일시
	*/
	@Schema(description = "생성일시 Field equals value. field:endDate"  , example="yyyy-MM-dd HH:mm:ss")
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@org.springframework.data.annotation.CreatedDate
	private java.time.LocalDateTime endDate;
	
	/*
	  사용자
	*/
	@Schema(description = "사용자 Use 'like' if value has %, else 'equal' field:createdBy"  )
	@Size(max=20)
	@org.springframework.data.annotation.CreatedBy
	private String createdBy;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

//ecd:2051015945H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.unitprice.dto;


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
public  class TestInUnitPriceSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  최종 전송 메세지 타입
	*/
	@Schema(description = "최종 전송 메세지 타입 Field equals value. field:msgType"  )
	private MessageType msgType;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:id"  )
	@Size(max=30)
	private String id;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

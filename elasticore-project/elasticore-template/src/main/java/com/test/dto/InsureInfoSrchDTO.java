//ecd:-1216954310H20250312131438_V1.0
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
import com.test.enums.*;


/**


*/


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class InsureInfoSrchDTO  implements java.io.Serializable, SortableObject  {

	@Schema(description = "Field equals value. field:insureCompanyId"  )
	@Size(max=12)
	private String insureCompanyId;
	
	@Schema(description = "Use 'like' if value has %, else 'equal' field:customerType"  , example="PR: 개인 | BS: 개인 사업자 | CP: 법인")
	private List<CustomerType> customerType;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

//ecd:85487289H20250117173850_V1.0
package com.test.dto;


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
public  class ProductSrchDTO  implements java.io.Serializable, SortableObject  {

	/*
	  PK
	*/
	@Schema(description = "PK Use 'like' if value has %, else 'equal' field:pid"  )
	@Size(max=30)
	private String pid;
	
	/*
	  상품명
	*/
	@Schema(description = "상품명 Use 'like' if value has %, else 'equal' field:name"  )
	private String name;
	
	/*
	  상품 영문명
	*/
	@Schema(description = "상품 영문명 Use 'like' if value has %, else 'equal' field:engName"  )
	private String engName;
	
	/*
	  상품개요
	*/
	@Schema(description = "상품개요 Use 'like' if value has %, else 'equal' field:desc"  )
	private String desc;
	
	/*
	  상품가격
	*/
	@Schema(description = "상품가격 Field equals value. field:price"  )
	private Long price;
	
	/*
	  상품 무게
	*/
	@Schema(description = "상품 무게 Field equals value. field:weight"  )
	private Double weight;
	
	
	private String sortCode;

    private String sortColumn; // Column to sort by
    private Boolean sortAscending; // Sort order (true: ascending, false: descending)
}

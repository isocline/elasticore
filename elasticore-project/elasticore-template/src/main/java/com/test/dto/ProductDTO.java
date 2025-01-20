//ecd:1503564132H20250117173850_V1.0
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
public  class ProductDTO  implements java.io.Serializable  {

	/*
	  PK
	*/
	@Schema(description = "PK"  )
	@Size(max=30)
	private String pid;
	
	/*
	  상품명
	*/
	@Schema(description = "상품명"  )
	private String name;
	
	/*
	  상품 영문명
	*/
	@Schema(description = "상품 영문명"  )
	private String engName;
	
	/*
	  상품개요
	*/
	@Schema(description = "상품개요"  )
	private String desc;
	
	/*
	  상품가격
	*/
	@Schema(description = "상품가격"  )
	private Long price;
	
	/*
	  상품 무게
	*/
	@Schema(description = "상품 무게"  )
	private Double weight;
	

}

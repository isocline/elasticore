//ecd:-1678564100H20240530111741_V0.8
package io.elasticore.demo.linkone.dto;

import io.elasticore.demo.linkone.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public  class PagingInfo  implements java.io.Serializable  {

	/*
	  현재 페이지 번호
	*/
	@Schema(description = "현재 페이지 번호"  )
	private Integer page = 0;
	
	/*
	  페이지당 로우수
	*/
	@Schema(description = "페이지당 로우수"  )
	private Integer perPage = 10;
	
	/*
	  전체 로우 수
	*/
	@Schema(description = "전체 로우 수"  )
	private Integer rowCount;
	
	/*
	  전체 페이지 수
	*/
	@Schema(description = "전체 페이지 수"  )
	private Integer pageCount;
	
};

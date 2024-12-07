//ecd:-1832412207H20241207204629_V1.0
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
public  class PersonGroupDTO  implements java.io.Serializable  {

	@Schema(description = "personList"  )
	private List<PersonDTO> personList;
	
	@Schema(description = "testMap2"  )
	private java.util.HashMap<String,Object> testMap2;
	
	@Schema(description = "test2"  )
	private Integer test2;
	
	/*
	  차량분류
	*/
	@Schema(description = "차량분류"  )
	private com.mobillug.leaserent.estimator.biz.enums.CarClassType carClassType;
	
	/*
	  유종
	*/
	@Schema(description = "유종"  )
	private com.mobillug.leaserent.estimator.biz.enums.FuelType fuelType;
	
	@Schema(description = "id"  )
	private String id;
	
	@Schema(description = "name"  )
	private String name;
	
	@Schema(description = "scope1"  )
	private Integer scope1;
	
	@Schema(description = "scope2"  )
	private Integer scope2;
	

}

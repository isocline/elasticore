//ecd:562840402H20240806161524_V0.8
package io.elasticore.px.dto.response;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.time.*;



/**
 * <pre></pre>

*/


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class ReserveRate  implements java.io.Serializable  {

	// id:ID:c92d66c9-54cc-4877-b2bc-b381c624d146
	// vid:ID:3e394d60-7ee3-4a29-a1a9-b0d9a595c043
	// lid:ID:c3b3315f-0cf9-4358-ad46-4eca9d6b85c4
	// required:true
	private Double reserveRate;
	
	// id:ID:3c5faf08-c1c9-4a96-a1f9-f18be6deb4ab
	// vid:ID:69a34789-d1ea-4329-94d3-e2395a04c7ee
	// lid:ID:5a2f7c5f-1277-4222-91fb-4008eca54bfa
	// required:true
	private String duration;
	
	// id:ID:f2484c48-ad7b-4154-9e27-2cd62ebde68d
	// vid:ID:d0534c83-9315-4d69-bb74-3b3ec5f63402
	// lid:ID:494e09d7-9385-4f48-8c85-a2ca790a1731
	// required:true
	private String reserveType;
	
};

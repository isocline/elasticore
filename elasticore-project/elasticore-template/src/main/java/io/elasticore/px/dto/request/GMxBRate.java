//ecd:2051656192H20240806161524_V0.8
package io.elasticore.px.dto.request;


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
public  class GMxBRate  implements java.io.Serializable  {

	// id:ID:717a5ee6-5ccb-4109-b15e-da1b4adb9474
	// vid:ID:fdcc211a-dfa9-4641-8e20-dbdcedae7878
	// lid:ID:d254ad79-6a06-43c9-a562-c0a931d854eb
	// required:null
	private String gMxBType;
	
	// id:ID:d0e12d18-be0e-424c-a4fd-8545d4511a33
	// vid:ID:4e89804d-fa54-4b57-b6df-85d912b65a3e
	// lid:ID:c63d239a-f954-4d38-a197-21bccee4a0f5
	// required:null
	private Double gMxBRate;
	
};

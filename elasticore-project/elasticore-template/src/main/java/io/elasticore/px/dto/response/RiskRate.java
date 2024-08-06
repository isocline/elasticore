//ecd:-956118280H20240806161524_V0.8
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
public  class RiskRate  implements java.io.Serializable  {

	// id:ID:b1ec2b72-0e51-410d-9994-52b2054fee1b
	// vid:ID:99a8dc56-60da-4e45-acdc-ff4f62d630fa
	// lid:ID:279d216b-1639-4156-ad01-49ae25ec3d4b
	// required:null
	private Double riskRate;
	
	// id:ID:0088385e-3451-424d-bb8e-2aa3fde3405f
	// vid:ID:f2b83c7d-c2e7-4f65-aa64-77c1af8e0565
	// lid:ID:3959c78c-b907-4fa2-af73-621a5254c824
	// required:true
	private String duration;
	
};

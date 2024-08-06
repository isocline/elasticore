//ecd:-1333477509H20240806161524_V0.8
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
public  class Rider  implements java.io.Serializable  {

	// id:ID:95cdb140-e2f1-41fa-b044-9174f414b45d
	// vid:ID:fd720191-ea96-4b77-8a97-708a1da04394
	// lid:ID:7c07d90b-3a3a-45d7-a6f4-70d11e8b744b
	// required:null
	private String paymentPeriodType;
	
	// id:ID:fe1aa5dd-dbda-4796-a865-a6e12d31db71
	// vid:ID:42421e43-00b0-4c92-97ea-d5b7871b9665
	// lid:ID:c5a69718-22cf-4c89-80a4-2b7c26bdd6e7
	// required:null
	private String initialDate;
	
	// id:ID:61e99ca0-616e-40e1-b125-82440ceea1f0
	// vid:ID:1e5df65d-ca19-4a66-831c-20cd8a785f7e
	// lid:ID:dd059736-894a-4e91-8232-520b37a4bc10
	// required:null
	private String insurancePeriodType;
	
	// id:ID:e9d82927-cbd8-4409-84e4-1710b938f2f3
	// vid:ID:6030e323-beae-4326-a885-701303cbe40d
	// lid:ID:cbfa0b4c-e2f5-44e6-b485-3c4ec00168d5
	// required:null
	private Double faceAmount;
	
	// id:ID:ae6e7c56-c1cf-4e69-8bf8-6cfc243cf2b2
	// vid:ID:02abaa2d-2275-4108-bb57-c060912e3ea5
	// lid:ID:f78b146a-e339-4063-80f0-584cd623ac14
	// required:null
	private String initialAge;
	
	// id:ID:04500df6-a962-42c1-bd17-fbc6ff03271b
	// vid:ID:0ac3983b-a54c-432a-88c3-85df87d7180c
	// lid:ID:7d9cb87c-0b66-467d-8e7c-eb8157d4b01c
	// required:null
	private String insurancePeriodValue;
	
	// id:ID:bef2c855-ecf9-49e0-8fae-b787880c056c
	// vid:ID:7db9b457-9178-4dfc-9c06-9d45c4c9e2d8
	// lid:ID:6e80d7c2-400b-49e2-a809-e78044ac72c1
	// required:null
	private String paymentPeriodValue;
	
	// id:ID:d2e465a7-7545-40be-a210-77d954a56c85
	// vid:ID:83c1a900-9663-48b9-9895-eb3e62322e72
	// lid:ID:32d741d5-66d5-46e0-9bd1-cf9529419049
	// required:null
	private Double simulatedRate;
	
	// id:null
	// vid:null
	// lid:null
	// required:null
	private List<RiskRate> riskRateList;
	
	// id:null
	// vid:null
	// lid:null
	// required:null
	private List<GMxBRate> gMxBRateList;
	
	// id:null
	// vid:null
	// lid:null
	// required:null
	private List<PremiumRate> premiumRateList;
	
	// id:null
	// vid:null
	// lid:null
	// required:null
	private List<ReserveRate> reserveRateList;
	
};

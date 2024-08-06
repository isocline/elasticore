//ecd:-1433122905H20240806161524_V0.8
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
public  class PremiumRate  implements java.io.Serializable  {

	// id:ID:11172bac-dfb5-471b-b019-7c745f875219
	// vid:ID:25601678-dd73-4716-9673-76da3685291f
	// lid:ID:b92cd548-f67a-4495-9844-7f9a99eddf11
	// required:null
	private Double premiumRate;
	
};

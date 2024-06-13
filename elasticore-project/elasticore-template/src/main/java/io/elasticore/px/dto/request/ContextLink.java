//ecd:-893302508H20240614012735_V0.8
package io.elasticore.px.dto.request;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
public  class ContextLink  implements java.io.Serializable  {

	private String oid;
	
	private String parentPath;
	
	private Context context;
	
};

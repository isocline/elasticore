package io.elasticore.px.dto;


//import io.elasticore.px.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class JavaPX  implements java.io.Serializable  {

	private Policy policy;
	

	private JavaPXPolicy javaPXPolicy;
	

	private JavaPXPolicy1 javaPXPolicy1;
	


};

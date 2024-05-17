//ecd:290684443H20240517105348V0.7
package io.elasticore.demo.linkone.entity;


import io.elasticore.demo.linkone.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import javax.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class Capital  implements java.io.Serializable  {

	@Id
	@Column(name = "cpt_code")
	private String cptCode;
	

	@Column(name = "cpt_name")
	private String cptName;
	

	@Comment("활성화 여부")
	private Indicator enableStatus = Y;
	


};

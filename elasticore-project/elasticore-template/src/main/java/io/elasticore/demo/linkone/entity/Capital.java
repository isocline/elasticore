//ecd:-1647539886H20240523142719V0.7
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
	@Column(name = "cpt_code", length = 32)
	private String cptCode;
	
	
	@Column(name = "cpt_name", length = 64)
	private String cptName;
	
	
	@Comment("활성화 여부")
	@Column(length = 5)
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator enableStatus = Indicator.Y;
	
	

};

//ecd:702221252H20240517105348V0.7
package io.elasticore.demo2.entity;


import io.elasticore.demo2.enums.*;

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

public  class CheckDTO  implements java.io.Serializable  {

	@Column(name = "name")
	private Integer name;
	

	@Column(name = "update_date")
	private String updateDate;
	

	@Column(name = "created_by")
	private String createdBy;
	


};

//ecd:1210844210H20240517105348V0.7
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
@Embeddable

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class PictureInfo  implements java.io.Serializable  {

	@Column(name = "uri")
	private String uri;
	

	@Column(name = "file_name")
	private String fileName;
	

	@Column(name = "type")
	private String type;
	


};

//ecd:1577503075H20240527134244V0.7
package io.elasticore.demo.linkone.entity;

import io.elasticore.demo.linkone.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.hibernate.annotations.Comment;
import javax.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class PictureInfo  implements java.io.Serializable  {
	@Column(name = "uri")
	private String uri;
	
	
	@Column(name = "file_name", length = 64)
	private String fileName;
	
	
	@Column(name = "type")
	private String type;
	
	
};

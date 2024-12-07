//ecd:-147209298H20241207204628_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.entity;

import com.xsolcorpkorea.elasticore.test.rollup.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity  implements java.io.Serializable  {

	@Column(name = "owner_id")
	private String ownerId;
	
	
	@Column(name = "test_id")
	private String testId;
	
	
}

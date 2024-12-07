//ecd:-156056500H20241207204628_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.entity;


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

@Embeddable
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CommonCodeIdentity  implements java.io.Serializable  {

	@Column(name = "code_type", nullable = false, length = 20)
	private String codeType;
	
	@Column(name = "main_code", nullable = false, length = 20)
	private String mainCode;
	
}

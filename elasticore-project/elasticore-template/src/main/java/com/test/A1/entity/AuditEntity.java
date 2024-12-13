//ecd:-1091591503H20241213011350_V1.0
package com.test.A1.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity  implements java.io.Serializable  {

	@Column(name = "create_date", length = 10)
	private String createDate;
	
	
}

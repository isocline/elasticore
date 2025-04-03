//ecd:1831528907H20250402000028_V1.0
package com.xyrokorea.xyroplug.domain.channel.entity;

import com.xyrokorea.xyroplug.domain.channel.enums.*;
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
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Enployee  implements java.io.Serializable  {

	@Id
	@Column(name = "id", length = 30)
	private String id;
	
	
	@Column(name = "name", length = 500)
	private String name;
	
	
	@Column(name = "addr", length = 100)
	private String addr;
	
	
	@Column(name = "tel_no", length = 12)
	private String telNo;
	
	
}

//ecd:-1534010010H20241010182122_V1.0
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
import javax.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@DiscriminatorValue("MBLG")
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class ResidualMobillug extends BaseResidualInfo implements java.io.Serializable  {

	@Column(name = "period36")
	private Float period36;
	
	
	@Column(name = "period48")
	private Float period48;
	
	
	@Column(name = "period60")
	private Float period60;
	
	
}

//ecd:2101485337H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.entity;

import com.mobillug.leaserent.estimator.biz.enums.*;
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
public  class LeaseRentalCapital extends LifecycleEntity implements java.io.Serializable  {

	/*
	  캐피탈 아이디
	*/
	@Id
	@Comment("캐피탈 아이디")
	@Column(name = "id", length = 12)
	private String id;
	
	
	@Column(name = "name")
	private String name;
	
	
}

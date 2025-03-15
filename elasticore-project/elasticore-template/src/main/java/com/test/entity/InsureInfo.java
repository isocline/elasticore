//ecd:-1811481434H20250313130133_V1.0
package com.test.entity;

import com.test.enums.*;
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
import com.test.enums.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@IdClass(InsureInfoIdentity.class)
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class InsureInfo  implements java.io.Serializable  {

	/*
	  아이디
	*/
	@Id
	@Comment("아이디")
	@Column(name = "id", length = 12)
	private String id;
	
	
	@Id
	@Column(name = "id2")
	private Long id2;
	
	
	@Column(name = "vae_test", length = 12)
	private String name;
	
	
	/*
	  고객 분류 / 기준2
	*/
	@Comment("고객 분류 / 기준2")
	@ElementCollection( fetch = FetchType.EAGER)
	@CollectionTable(name = "insure_info_customer_type", joinColumns = @JoinColumn(name = "map_seq"))
	@Column(name = "code")
	@Convert(converter = CustomerType.EntityConverter.class)
	private List<CustomerType> customerType;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
}

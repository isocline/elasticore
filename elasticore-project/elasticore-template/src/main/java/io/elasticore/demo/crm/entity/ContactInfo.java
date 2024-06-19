//ecd:865468907H20240618012928_V0.8
package io.elasticore.demo.crm.entity;

import io.elasticore.demo.crm.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@Entity
@Table(name="T_CNCT_LST")
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public  class ContactInfo  implements java.io.Serializable  {

	@EmbeddedId
	private ContactInfoIdentity id;
	
	/*
	  이용자명
	*/
	@Comment("이용자명")
	@Column(name = "CONTACT_NAME", length = 100)
	private String contractName;
	
	
	/*
	  전화번호
	*/
	@Comment("전화번호")
	@Column(name = "contractTel", length = 20)
	private String contractTel;
	
	
	@ManyToOne
	@JoinColumn(columnDefinition = "contractGrp_id")
	private ContractGroup contractGrp;
	
	
};

//ecd:-1085828850H20240521223026V0.7
package io.elasticore.demo.crm.entity;


import io.elasticore.demo.crm.enums.*;

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
@Table(name="T_CNCT_LST")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class ContactInfo  implements java.io.Serializable  {

	@EmbeddedId
	private ContactInfoIdentity id;
	
	@Comment("이용자명")
	@Column(name = "CONTACT_NAME")
	private String contractName;
	
	
	@Comment("전화번호")
	@Column(name = "contractTel")
	private String contractTel;
	
	
	@ManyToOne
	@JoinColumn(columnDefinition = "contractGrp_id")
	private ContractGroup contractGrp;
	
	

};

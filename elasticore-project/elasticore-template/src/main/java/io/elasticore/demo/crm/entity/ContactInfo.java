package io.elasticore.demo.crm.entity;


import io.elasticore.demo.crm.enums.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
	
	@Column(name = "CONTACT_NAME")
	private String contractName;
	

	@Column(name = "contractTel")
	private String contractTel;
	

	@ManyToOne
	@JoinColumn(columnDefinition = "contractGrp_id")
	private ContractGroup contractGrp;
	


};

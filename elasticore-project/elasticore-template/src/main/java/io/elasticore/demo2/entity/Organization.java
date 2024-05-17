//ecd:1647013193H20240517105348V0.7
package io.elasticore.demo2.entity;


import io.elasticore.demo2.enums.*;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class Organization extends Party implements java.io.Serializable  {

	@Column(name = "organization_status_key")
	private String organizationStatusKey;
	

	@Column(name = "estab_date")
	private String estabDate;
	

	@Column(name = "num_owners")
	private Integer numOwners;
	


};

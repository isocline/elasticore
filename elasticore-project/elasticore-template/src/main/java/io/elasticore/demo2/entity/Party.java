//ecd:-1174996654H20240517105348V0.7
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "party_type", discriminatorType = DiscriminatorType.STRING)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class Party  implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "party_key")
	private Long partyKey;
	

	@Column(name = "party_type", insertable = false, updatable = false)
	private String partyType;
	

	private CurrencyCode preferCuurencyCode;
	


};

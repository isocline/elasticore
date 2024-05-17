//ecd:-1726190120H20240517105348V0.7
package io.elasticore.demo.crm.entity;



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

@Embeddable

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class ContactInfoIdentity  implements java.io.Serializable  {

	@Column(name = "GRP_SEQ")
	private Integer grpSeq;
	
	@Column(name = "CTR_SEQ")
	private Integer contactSeq;
	

};

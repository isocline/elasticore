//ecd:-1123887710H20240523142719V0.7
package io.elasticore.demo.crm.entity;



import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;

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

	@Comment("그릅 일련번호")
	@Column(name = "GRP_SEQ")
	private Integer grpSeq;
	
	@Comment("순번")
	@Column(name = "CTR_SEQ")
	private Integer contactSeq;
	

};

//ecd:-923054891H20240529100717V0.7
package io.elasticore.demo.crm.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
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
	// 그릅 일련번호
	@Comment("그릅 일련번호")
	@Column(name = "GRP_SEQ")
	private Integer grpSeq;
	
	// 순번
	@Comment("순번")
	@Column(name = "CTR_SEQ")
	private Integer contactSeq;
	
};

//ecd:1725153161H20240517105348V0.7
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
@Table(name="T_CNCT_GRP")
@org.hibernate.annotations.DynamicUpdate
@org.hibernate.annotations.DynamicInsert

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class ContractGroup  implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRP_SEQ")
	private Integer grpSeq;
	

	@Comment("그룹명")
	@Column(name = "GROUP_NAME")
	private String groupName;
	

	@OneToMany(fetch = FetchType.LAZY)
	private List<ContactInfo> contactInfoList;
	


};

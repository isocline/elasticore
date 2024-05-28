//ecd:-1925999692H20240528142316V0.7
package io.elasticore.demo.crm.entity;

import io.elasticore.demo.crm.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
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
	// 그릅 일련번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("그릅 일련번호")
	@Column(name = "GRP_SEQ")
	private Integer grpSeq;
	
	
	// 그룹명
	@Comment("그룹명")
	@Column(name = "GROUP_NAME", length = 200)
	private String groupName;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<ContactInfo> contactInfoList;
	
	
};

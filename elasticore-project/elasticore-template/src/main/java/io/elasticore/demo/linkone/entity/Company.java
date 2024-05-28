//ecd:-1541465211H20240528142512V0.7
package io.elasticore.demo.linkone.entity;

import io.elasticore.demo.linkone.enums.*;
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
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class Company extends AuditEntity implements java.io.Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "com_seq")
	private Long comSeq;
	
	
	@Column(length = 2)
	@Convert(converter = CompanyGroupCode.EntityConverter.class)
	private CompanyGroupCode comGrpCode;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<CompanyAreaInfo> areaCodeList;
	
	
	// 업체명
	@Comment("업체명")
	@Column(name = "com_name", length = 64)
	private String comName;
	
	
	@Column(name = "resp_name", length = 64)
	private String respName;
	
	
	// 담당자전화번호
	@Comment("담당자전화번호")
	@Column(name = "resp_tel", length = 15)
	private String respTel;
	
	
	@Column(name = "resp_zone", length = 100)
	private String respZone;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<CustUser> userList;
	
	
};

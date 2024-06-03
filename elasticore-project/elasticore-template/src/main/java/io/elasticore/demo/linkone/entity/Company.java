//ecd:-1943752054H20240531164502_V0.8
package io.elasticore.demo.linkone.entity;

import io.elasticore.demo.linkone.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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
@EntityListeners(AuditingEntityListener.class)
public  class Company extends AuditEntity implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "com_seq")
	private Long comSeq;
	
	
	@Convert(converter = CompanyGroupCode.EntityConverter.class)
	private CompanyGroupCode comGrpCode;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<CompanyAreaInfo> areaCodeList;
	
	
	@Column(name = "com_name", length = 64)
	private String comName;
	
	
	@Column(name = "resp_name", length = 64)
	private String respName;
	
	
	/*
	  담당자전화번호
	*/
	@Comment("담당자전화번호")
	@Column(name = "resp_tel", length = 15)
	private String respTel;
	
	
	@Column(name = "resp_zone", length = 100)
	private String respZone;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<CustUser> userList;
	
	
};

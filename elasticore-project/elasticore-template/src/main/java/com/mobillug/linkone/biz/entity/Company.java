//ecd:3741666H20240618012928_V0.8
package com.mobillug.linkone.biz.entity;

import com.mobillug.linkone.biz.enums.*;
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
	@Column(name = "id", length = 30)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.aventrix.jnanoid.jnanoid.NanoIdUtils.randomNanoId();
	}
	
	
	/*
	  업체그룹코드
	*/
	@Comment("업체그룹코드")
	@Convert(converter = CompanyGroupCode.EntityConverter.class)
	private CompanyGroupCode comGrpCode;
	
	
	/*
	  담당지역
	*/
	@Comment("담당지역")
	@ElementCollection( fetch = FetchType.EAGER)
	@CollectionTable(name = "company_area_code_list", joinColumns = @JoinColumn(name = "map_seq"))
	@Column(name = "code")
	@Convert(converter = AreaCode.EntityConverter.class)
	private List<AreaCode> areaCodeList;
	
	
	/*
	  제휴고객사
	*/
	@Comment("제휴고객사")
	@ManyToOne
	@JoinColumn(columnDefinition = "partnerCust_id")
	private CommonCode partnerCust;
	
	
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
	
	
	/*
	  업체활성화여부 Y:활성화 / N:비활성화
	*/
	@Comment("업체활성화여부 Y:활성화 / N:비활성화")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator useYn;
	
	
	/*
	  해당업체 사용자목록
	*/
	@Comment("해당업체 사용자목록")
	@OneToMany(fetch = FetchType.LAZY )
	private List<CustUser> userList;
	
	
};

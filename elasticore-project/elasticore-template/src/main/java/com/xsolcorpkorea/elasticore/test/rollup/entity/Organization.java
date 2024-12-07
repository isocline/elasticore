//ecd:-1805074198H20241207204628_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.entity;

import com.xsolcorpkorea.elasticore.test.rollup.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.*;
import java.time.*;
import javax.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class Organization extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "org_cd", nullable = false, length = 15)
	private String orgCd;
	
	
	@Column(name = "org_nm", nullable = false, length = 300)
	private String orgNm;
	
	
	/*
	  조직 레벨 코드
	*/
	@Comment("조직 레벨 코드")
	@Column(name = "org_level_cd", length = 15)
	private String orgLevelCd;
	
	
	/*
	  상위 조직 코드
	*/
	@Comment("상위 조직 코드")
	@Column(name = "up_org_cd", length = 15)
	private String upOrgCd;
	
	
	/*
	  정렬 순서
	*/
	@Comment("정렬 순서")
	@Column(name = "order_no")
	private Long orderNo;
	
	
	/*
	  조직장 ID
	*/
	@Comment("조직장 ID")
	@Column(name = "org_boss_id", length = 15)
	private String orgBossId;
	
	
	/*
	  직통전화 1
	*/
	@Comment("직통전화 1")
	@Column(name = "direct_phone1", length = 4)
	private String directPhone1;
	
	
	/*
	  직통전화 2
	*/
	@Comment("직통전화 2")
	@Column(name = "direct_phone2", length = 4)
	private String directPhone2;
	
	
	/*
	  직통전화 3
	*/
	@Comment("직통전화 3")
	@Column(name = "direct_phone3", length = 4)
	private String directPhone3;
	
	
	/*
	  팩스 전화 1
	*/
	@Comment("팩스 전화 1")
	@Column(name = "fax_phone1", length = 4)
	private String faxPhone1;
	
	
	/*
	  팩스 전화 2
	*/
	@Comment("팩스 전화 2")
	@Column(name = "fax_phone2", length = 4)
	private String faxPhone2;
	
	
	/*
	  팩스 전화 3
	*/
	@Comment("팩스 전화 3")
	@Column(name = "fax_phone3", length = 4)
	private String faxPhone3;
	
	
	/*
	  우편번호 1
	*/
	@Comment("우편번호 1")
	@Column(name = "post_no1", length = 7)
	private String postNo1;
	
	
	/*
	  우편번호 2
	*/
	@Comment("우편번호 2")
	@Column(name = "post_no2", length = 7)
	private String postNo2;
	
	
	/*
	  기본 주소
	*/
	@Comment("기본 주소")
	@Column(name = "base_addr", length = 200)
	private String baseAddr;
	
	
	/*
	  상세 주소
	*/
	@Comment("상세 주소")
	@Column(name = "detail_addr", length = 300)
	private String detailAddr;
	
	
	/*
	  개설일자
	*/
	@Comment("개설일자")
	@Column(name = "open_ymd", length = 8)
	private String openYmd;
	
	
	/*
	  폐쇄일자
	*/
	@Comment("폐쇄일자")
	@Column(name = "close_ymd", length = 8)
	private String closeYmd;
	
	
	/*
	  조직 유형 코드
	*/
	@Comment("조직 유형 코드")
	@Column(name = "org_type_cd", length = 15)
	private String orgTypeCd;
	
	
	/*
	  증감 조직 코드
	*/
	@Comment("증감 조직 코드")
	@Column(name = "increase_org_cd", length = 15)
	private String increaseOrgCd;
	
	
	/*
	  검색 여부
	*/
	@Comment("검색 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator searchYN = Indicator.YES;
	
	
}

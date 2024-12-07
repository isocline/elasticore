//ecd:1832174456H20241207204629_V1.0
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
@IdClass(InsProductCodeIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class InsProductCode extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "ins_cp_code", nullable = false, length = 6)
	private String insCpCode;
	
	
	@Id
	@Column(name = "product_code", nullable = false, length = 20)
	private String productCode;
	
	
	@Column(name = "prod_nm", nullable = false, length = 500)
	private String prodNm;
	
	
	@Column(name = "prod_type_cd", nullable = false, length = 3)
	private String prodTypeCd;
	
	
	@Column(name = "prod_grp_cd", nullable = false, length = 5)
	private String prodGrpCd;
	
	
	@Column(name = "ins_prod_cd", nullable = false, length = 20)
	private String insProdCd;
	
	
	@Column(name = "sale_start_date", nullable = false, length = 8)
	private String saleStartDate;
	
	
	/*
	  판매 종료일 (YYYYMMDD)
	*/
	@Comment("판매 종료일 (YYYYMMDD)")
	@Column(name = "sale_end_date", length = 8)
	private String saleEndDate;
	
	
	/*
	  판매 여부
	*/
	@Comment("판매 여부")
	@Convert(converter = Indicator.EntityConverter.class)
	private Indicator saleYN = Indicator.NO;
	
	
	/*
	  관리 수량
	*/
	@Comment("관리 수량")
	@Column(name = "mgt_cnt", length = 4)
	private Integer mgtCnt;
	
	
	@Column(name = "adjust_rate", nullable = false)
	private Integer adjustRate = 1;
	
	
	@Column(name = "new_adjust_rate", nullable = false)
	private Float newAdjustRate = 100F;
	
	
}

//ecd:732411906H20241207204629_V1.0
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
@IdClass(CustInsuranceStatusIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class CustInsuranceStatus extends AuditEntity implements java.io.Serializable  {

	@Id
	@Column(name = "cust_seq", nullable = false, length = 10)
	private String custSeq;
	
	
	/*
	  순번
	*/
	@Id
	@Comment("순번")
	@Column(name = "seq", length = 5)
	private Integer seq;
	
	
	@Column(name = "insu_com_nm", nullable = false, length = 30)
	private String insuComNm;
	
	
	@Column(name = "insu_item_nm", nullable = false, length = 20)
	private String insuItemNm;
	
	
	@Column(name = "insu_prod_nm", nullable = false, length = 100)
	private String insuProdNm;
	
	
	@Column(name = "start_ymd", nullable = false, length = 8)
	private String startYmd;
	
	
	@Column(name = "end_ymd", nullable = false, length = 8)
	private String endYmd;
	
	
}

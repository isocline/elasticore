//ecd:326357498H20240516173754V0.7
package io.elasticore.demo.linkone.entity;


import io.elasticore.demo.linkone.enums.*;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class CourtesyCarRequest  implements java.io.Serializable  {

	@Id
	@Comment("아이디")
	@Column(name = "id")
	private Long id;
	

	@Comment("대차접수번호")
	@Column(name = "recept_no")
	private String receptNo;
	

	@Comment("요청상담사")
	@Column(name = "emp_no")
	private String empNo;
	

	@Comment("대차용도")
	@Column(name = "rent_kind")
	private String rentKind;
	

	@Comment("대차차량코드")
	@Column(name = "rent_car_code")
	private String rentCarCode;
	

	@Comment("대차확정코드")
	@Column(name = "rent_car_fix_code")
	private String rentCarFixCode;
	

	@Comment("대차차량명")
	@Column(name = "rent_car_nm")
	private String rentCarNm;
	

	@Comment("대차비용")
	@Column(name = "rent_cost")
	private String rentCost;
	

	@Comment("보험사")
	@Column(name = "insur_nm")
	private String insurNm;
	

	@Comment("면책금")
	@Column(name = "inde_money")
	private String indeMoney;
	

	@Comment("신청일")
	@Column(name = "reg_date")
	private String regDate;
	

	@Comment("신청시간")
	@Column(name = "reg_time")
	private String regTime;
	

	@Comment("제휴사")
	@Column(name = "joincode")
	private String joincode;
	

	@Comment("고객명")
	@Column(name = "cust_nm")
	private String custNm;
	

	@Comment("연락처")
	@Column(name = "cust_tel")
	private String custTel;
	

	@Comment("VIP여부")
	@Column(name = "vip_yn")
	private String vipYn;
	

	@Comment("특이사항")
	@Column(name = "bigo")
	private String bigo;
	

	@Comment("요청일자")
	@Column(name = "req_date")
	private String reqDate;
	

	@Comment("요청시간")
	@Column(name = "req_time")
	private String reqTime;
	

	@Comment("요청장소우편번호")
	@Column(name = "req_zip")
	private String reqZip;
	

	@Comment("요청장소주소")
	@Column(name = "req_addr")
	private String reqAddr;
	

	@Comment("요청장소상세주소")
	@Column(name = "req_daddr")
	private String reqDaddr;
	

	@Comment("차량탁송여부")
	@Column(name = "consign_yn")
	private String consignYn;
	

	@Comment("'탁송장소(공장명")
	@Column(name = "consign_office_nm")
	private String consignOfficeNm;
	

	@Comment("'탁송장소(우편번호")
	@Column(name = "consign_zip")
	private String consignZip;
	

	@Comment("'탁송장소(주소")
	@Column(name = "consign_addr")
	private String consignAddr;
	

	@Comment("'탁송장소(상세주소")
	@Column(name = "consign_daddr")
	private String consignDaddr;
	

	@Comment("'탁송장소(전화번호")
	@Column(name = "consign_tel")
	private String consignTel;
	

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date createDate;
	

	@Column(name = "created_by")
	private String createdBy;
	

	@Column(name = "last_modified_by")
	private String lastModifiedBy;
	

	@Column(name = "last_modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date lastModifiedDate;
	


};

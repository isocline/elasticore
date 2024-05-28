//ecd:-742552722H20240528142512V0.7
package io.elasticore.demo.linkone.dto;

import io.elasticore.demo.linkone.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.*;
import java.time.*;



/**


*/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class CustUserDTO  implements java.io.Serializable  {
	private Long companyComSeq;
	private CompanyDTO company;
	private Long usrSeq;
	// 아이디
	private String usrId;
	// 패스워드
	private String password;
	// 이름
	private String name;
	// 전화번호
	private String telNo;
	// 이메일
	private String email;
	// 부서
	private String deptNm;
	// 직급
	private String grade;
	private java.time.LocalDateTime createDate;
	private String createdBy;
	private String lastModifiedBy;
	private java.time.LocalDateTime lastModifiedDate;
};

//ecd:-1866455749H20240805175914_V0.8
package com.mobillug.linkone.apisvc.entity;

import com.mobillug.linkone.apisvc.enums.*;
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
import jakarta.persistence.*;
import java.util.*;
import java.time.*;



/**


*/

@MappedSuperclass
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class ReCommon  implements java.io.Serializable  {

	/*
	  전문번호
	*/
	@Comment("전문번호")
	@Column(name = "packet_id")
	private String packetId;
	
	
	/*
	  전문 key 값
	*/
	@Comment("전문 key 값")
	@Column(name = "master_key")
	private String masterKey;
	
	
	/*
	  접수일자
	*/
	@Comment("접수일자")
	@Column(name = "send_ymd")
	private String sendYmd;
	
	
	/*
	  접수시간
	*/
	@Comment("접수시간")
	@Column(name = "send_hms")
	private String sendHms;
	
	
	/*
	  대차접수번호 (master)
	*/
	@Comment("대차접수번호 (master)")
	@Column(name = "recept_no", unique = true)
	private String receptNo;
	
	
	/*
	  전송코드
	*/
	@Comment("전송코드")
	@Column(name = "error_cd")
	private String errorCd;
	
	
	/*
	  전송내용
	*/
	@Comment("전송내용")
	@Column(name = "error_text")
	private String errorText;
	
	
};

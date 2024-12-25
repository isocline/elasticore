//ecd:-1588140326H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.entity;

import com.mobillug.leaserent.estimator.biz.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;
import com.mobillug.leaserent.estimator.biz.entity.*;
import jakarta.persistence.Entity;


/**


*/

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BASE")
@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class BaseCompany  implements java.io.Serializable  {

	/*
	  회사아이디
	*/
	@Id
	@Comment("회사아이디")
	@Column(name = "com_id", length = 12)
	private String comId;
	
	
	@PrePersist
	public void prePersist() {
	  if (comId == null)
	        comId = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
	@Column(name = "type", length = 10, insertable = false, updatable = false)
	private String type;
	
	
	@Column(name = "name", nullable = false, length = 256)
	private String name;
	
	
	/*
	  회사로고 이미지 url
	*/
	@Comment("회사로고 이미지 url")
	@Column(name = "logo_img_url", length = 512)
	private String logoImgUrl;
	
	
	/*
	  이미지 파일
	*/
	@Comment("이미지 파일")
	@ManyToOne
	@JoinColumn(columnDefinition = "uploadImgFile_id")
	private UploadFile uploadImgFile;
	
	
}

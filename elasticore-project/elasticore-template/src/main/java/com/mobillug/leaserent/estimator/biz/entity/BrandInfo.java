//ecd:2024216333H20241223210702_V1.0
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
import com.mobillug.leaserent.estimator.biz.enums.*;
import jakarta.persistence.Entity;


/**


*/

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class BrandInfo extends LifecycleEntity implements java.io.Serializable  {

	/*
	  브랜드 아이디
	*/
	@Id
	@Comment("브랜드 아이디")
	@Column(name = "id", length = 12)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
	/*
	  브랜드명 // 예
	*/
	@Comment("브랜드명 // 예")
	@Column(name = "brand_name", nullable = false, length = 50)
	private String brandName;
	
	
	/*
	  브랜드 타입 // 예
	*/
	@Comment("브랜드 타입 // 예")
	@Convert(converter = BrandType.EntityConverter.class)
	private BrandType brandType;
	
	
	/*
	  국가코드
	*/
	@Comment("국가코드")
	@Column(name = "nation", length = 2)
	private String nation;
	
	
	/*
	  로고 이미지 url
	*/
	@Comment("로고 이미지 url")
	@Column(name = "img_url", length = 512)
	private String imgUrl;
	
	
	/*
	  브랜드 로고 파일
	*/
	@Comment("브랜드 로고 파일")
	@ManyToOne
	@JoinColumn(columnDefinition = "logoImgFile_id")
	private UploadFile logoImgFile;
	
	
}

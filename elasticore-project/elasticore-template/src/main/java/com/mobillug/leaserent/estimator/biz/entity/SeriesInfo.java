//ecd:1172710937H20241223210702_V1.0
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

@Entity
@org.hibernate.annotations.DynamicUpdate
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class SeriesInfo extends LifecycleEntity implements java.io.Serializable  {

	/*
	  아이디
	*/
	@Id
	@Comment("아이디")
	@Column(name = "id", length = 12)
	private String id;
	
	
	@PrePersist
	public void prePersist() {
	  if (id == null)
	        id = com.mobillug.leaserent.estimator.common.utils.IdUtils.newId();
	}
	
	
	/*
	  시리즈명 // 예
	*/
	@Comment("시리즈명 // 예")
	@Column(name = "series_name", nullable = false, length = 50)
	private String seriesName;
	
	
	/*
	  차종 // 예
	*/
	@Comment("차종 // 예")
	@Column(name = "type")
	private String type;
	
	
	/*
	  브랜드 정보 참조
	*/
	@Comment("브랜드 정보 참조")
	@ManyToOne
	@JoinColumn(columnDefinition = "brandInfo_id")
	private BrandInfo brandInfo;
	
	
	/*
	  차량 대표 이미지 url
	*/
	@Comment("차량 대표 이미지 url")
	@Column(name = "img_url", length = 512)
	private String imgUrl;
	
	
	/*
	  차량 이미지 파일
	*/
	@Comment("차량 이미지 파일")
	@ManyToOne
	@JoinColumn(columnDefinition = "uploadImgFile_id")
	private UploadFile uploadImgFile;
	
	
}

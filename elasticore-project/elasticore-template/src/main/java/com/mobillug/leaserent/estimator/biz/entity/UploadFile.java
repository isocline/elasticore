//ecd:1725127144H20241223210702_V1.0
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
public  class UploadFile extends AuditEntity implements java.io.Serializable  {

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
	  업로드 종류
	*/
	@Comment("업로드 종류")
	@Convert(converter = FileKind.EntityConverter.class)
	private FileKind fileKind;
	
	
	@Column(name = "file_url", nullable = false, length = 512)
	private String fileUrl;
	
	
	/*
	  경로정보
	*/
	@Comment("경로정보")
	@Column(name = "file_path", length = 512)
	private String filePath;
	
	
	/*
	  파일 타입
	*/
	@Comment("파일 타입")
	@Column(name = "file_type", length = 16)
	private String fileType;
	
	
	/*
	  파일 크기
	*/
	@Comment("파일 크기")
	@Column(name = "size")
	private Long size;
	
	
}

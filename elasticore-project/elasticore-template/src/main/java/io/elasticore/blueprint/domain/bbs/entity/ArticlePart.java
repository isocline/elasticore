//ecd:-1440478151H20250520141003_V1.0
package io.elasticore.blueprint.domain.bbs.entity;

import io.elasticore.blueprint.domain.bbs.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import java.util.*;
import java.time.*;
import io.elasticore.blueprint.domain.bbs.entity.*;
import jakarta.persistence.Entity;


/**
 * ArticlePart
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Entity
@org.hibernate.annotations.DynamicUpdate
@IdClass(ArticlePartIdentity.class)
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class ArticlePart  implements java.io.Serializable  {

	public ArticlePart(Article article,Article partnerArticle) {
	    this.article = article;    this.partnerArticle = partnerArticle;
	}
	
	/*
	  article ID
	*/
	@Id
	@Comment("article ID")
	@ManyToOne
	@JoinColumn(columnDefinition = "article_id")
	private Article article;
	
	
	/*
	  article ID
	*/
	@Id
	@Comment("article ID")
	@ManyToOne
	@JoinColumn(columnDefinition = "partnerArticle_id")
	private Article partnerArticle;
	
	
}

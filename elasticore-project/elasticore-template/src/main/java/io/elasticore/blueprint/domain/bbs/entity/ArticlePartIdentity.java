//ecd:1212611369H20250520141003_V1.0
package io.elasticore.blueprint.domain.bbs.entity;


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



/**
 * ArticlePartIdentity
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */

@Embeddable
@lombok.EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
public  class ArticlePartIdentity  implements java.io.Serializable  {

	/*
	  article ID
	*/
	private Article article;
	
	/*
	  article ID
	*/
	private Article partnerArticle;
	
}

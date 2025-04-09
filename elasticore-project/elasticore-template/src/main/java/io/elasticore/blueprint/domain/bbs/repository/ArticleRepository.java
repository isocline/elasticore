//ecd:-818382055H20250409104819_V1.0
package io.elasticore.blueprint.domain.bbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import io.elasticore.blueprint.domain.bbs.entity.*;
import io.elasticore.blueprint.domain.bbs.dto.*;
import io.elasticore.blueprint.domain.bbs.enums.*;



/**
 * JPA repository interface for managing Article entities.
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */


public interface ArticleRepository extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {



}

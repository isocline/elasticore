//ecd:2003106702H20250422172634_V1.0
package io.elasticore.blueprint.domain.parts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import io.elasticore.blueprint.domain.parts.entity.*;
import io.elasticore.blueprint.domain.parts.dto.*;




/**
 * JPA repository interface for managing PartPosition entities.
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */


public interface PartPositionRepository extends JpaRepository<PartPosition,PartPositionIdentity>, JpaSpecificationExecutor<PartPosition> {



}

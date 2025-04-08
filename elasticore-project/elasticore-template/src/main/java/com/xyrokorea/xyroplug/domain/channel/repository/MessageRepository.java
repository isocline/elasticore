//ecd:-196146574H20250405141628_V1.0
package com.xyrokorea.xyroplug.domain.channel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.xyrokorea.xyroplug.domain.channel.entity.*;
import com.xyrokorea.xyroplug.domain.channel.dto.*;
import com.xyrokorea.xyroplug.domain.channel.enums.*;



/**
 * JPA repository interface for managing Message entities.
 *
 * Modify this code only as specified in the ElastiCORE guidelines
 * to avoid regeneration conflicts.
 *
 * Generated and managed by ElastiCORE.
 */


public interface MessageRepository extends JpaRepository<Message,Long>, JpaSpecificationExecutor<Message> {



}

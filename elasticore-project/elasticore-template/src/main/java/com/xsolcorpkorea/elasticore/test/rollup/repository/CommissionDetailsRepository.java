//ecd:-1204263046H20241207204629_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.xsolcorpkorea.elasticore.test.rollup.entity.*;
import com.xsolcorpkorea.elasticore.test.rollup.dto.*;
import com.xsolcorpkorea.elasticore.test.rollup.enums.*;



/**


 */

public interface CommissionDetailsRepository extends JpaRepository<CommissionDetails,CommissionDetailsIdentity>, JpaSpecificationExecutor<CommissionDetails> {



}

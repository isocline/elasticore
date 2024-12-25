//ecd:1037566711H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.mobillug.leaserent.estimator.biz.entity.*;
import com.mobillug.leaserent.estimator.biz.dto.*;
import com.mobillug.leaserent.estimator.biz.enums.*;



/**


 */

public interface SeriesInfoRepository extends JpaRepository<SeriesInfo,String>, JpaSpecificationExecutor<SeriesInfo> {

    @org.springframework.data.jpa.repository.Modifying
    @jakarta.transaction.Transactional
    @Query(nativeQuery=false, value=" UPDATE SeriesInfo e SET e.endDate = :endDate WHERE e.txId != :txId")
    int updateEndDateExcludingTxId(@Param("txId") String txId ,@Param("endDate") String endDate);
    

}

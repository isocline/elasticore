//ecd:1507443255H20240805175916_V0.8
package com.mobillug.linkone.apisvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.*;
import com.mobillug.linkone.apisvc.entity.*;
import com.mobillug.linkone.apisvc.dto.*;



/**


 */



public interface ReData100Repository extends JpaRepository<ReData100,Long>, JpaSpecificationExecutor<ReData100> {

    List<ReData100> findAllByChkCd(String chkCd);
    
    Optional<ReData100> findByReceptNo(String receptNo);
    
    @Query(nativeQuery=false, value=" SELECT MAX(e.id) FROM ReData100 e")
    Long findMaxId();
    

}

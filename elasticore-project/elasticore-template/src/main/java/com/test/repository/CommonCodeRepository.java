//ecd:-2087540540H20241224183051_V1.0
package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.test.entity.*;
import com.test.dto.*;




/**


 */

public interface CommonCodeRepository extends JpaRepository<CommonCode,String>, JpaSpecificationExecutor<CommonCode> {

    @org.springframework.data.jpa.repository.Modifying
    @jakarta.transaction.Transactional
    @Query(nativeQuery=true, value=" update common_code set last_modified_date=NOW() ,code_value =:codeValue where code_id =:codeNm AND NOW() - last_modified_date > INTERVAL '5 seconds'")
    int updateCodeValueWithCheckTime(@Param("codeNm") String codeNm ,@Param("codeVal") String codeVal);
    

}

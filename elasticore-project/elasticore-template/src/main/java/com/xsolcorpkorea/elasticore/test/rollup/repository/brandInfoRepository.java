//ecd:-348655524H20241206183822_V1.0
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




/**


 */

public interface BrandInfoRepository extends JpaRepository<BrandInfo,String>, JpaSpecificationExecutor<BrandInfo> {



}

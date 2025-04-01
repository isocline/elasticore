//ecd:-889220963H20250401183440_V1.0
package com.xyrokorea.xyroplug.domain.unitprice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.xyrokorea.xyroplug.domain.unitprice.entity.*;
import com.xyrokorea.xyroplug.domain.unitprice.dto.*;




/**


 */

public interface TestInUnitPriceRepository extends JpaRepository<TestInUnitPrice,String>, JpaSpecificationExecutor<TestInUnitPrice> {



}

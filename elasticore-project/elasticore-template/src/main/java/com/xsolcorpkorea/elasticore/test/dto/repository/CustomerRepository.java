//ecd:1611563917H20240924235117_V1.0
package com.xsolcorpkorea.elasticore.test.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.xsolcorpkorea.elasticore.test.dto.entity.*;
import com.xsolcorpkorea.elasticore.test.dto.dto.*;




/**


 */

public interface CustomerRepository extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {



}

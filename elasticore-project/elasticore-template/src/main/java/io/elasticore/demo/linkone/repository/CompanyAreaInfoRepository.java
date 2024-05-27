//ecd:-823491992H20240528005422V0.7
package io.elasticore.demo.linkone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.*;
import io.elasticore.demo.linkone.entity.*;
import io.elasticore.demo.linkone.dto.*;



/**


 */



public interface CompanyAreaInfoRepository extends JpaRepository<CompanyAreaInfo,Long> , JpaSpecificationExecutor<LoanCar> {



}

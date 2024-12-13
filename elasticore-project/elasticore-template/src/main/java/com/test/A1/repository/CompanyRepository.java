//ecd:1029299129H20241213011350_V1.0
package com.test.A1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.test.A1.entity.*;
import com.test.A1.dto.*;




/**


 */

public interface CompanyRepository extends JpaRepository<Company,String>, JpaSpecificationExecutor<Company> {



}

//ecd:-769945888H20250204104408_V1.0
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

public interface CompanyRepository extends JpaRepository<Company,String>, JpaSpecificationExecutor<Company> {

    @Query(nativeQuery=true, value=" SELECT "
     		 +  "   emp.id AS employee_id,"
     		 +  "   emp.name AS employee_name,"
     		 +  "   emp.gender AS gender_code,"
     		 +  "   emp.age AS employee_age,"
     		 +  "   emp.addr AS employee_address,"
     		 +  "   emp.is_adult AS is_adult,"
     		 +  "   com.cid AS company_id,"
     		 +  "   com.name AS company_name,"
     		 +  "   com.addr AS company_address,"
     		 +  "   com.tel_no AS company_phone,"
     		 +  "   com.capital AS company_capital"
     		 +  " FROM"
     		 +  " Employee emp"
     		 +  " INNER JOIN"
     		 +  " Company com"
     		 +  " ON"
     		 +  " emp.company = com.cid"
     		 +  " WHERE"
     		 +  " emp.age BETWEEN :minAge AND :maxAge  "
     		 +  " ORDER BY"
     		 +  " emp.name ASC, com.name ASC;")
    List<Object[]> selectJoinData(@Param("minAge") Integer minAge ,@Param("maxAge") Integer maxAge);
    

}

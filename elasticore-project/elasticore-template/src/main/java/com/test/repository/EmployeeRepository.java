//ecd:5056720H20250204110511_V1.0
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

public interface EmployeeRepository extends JpaRepository<Employee,String>, JpaSpecificationExecutor<Employee> {



}

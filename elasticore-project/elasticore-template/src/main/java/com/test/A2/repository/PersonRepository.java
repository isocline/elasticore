//ecd:-1343510808H20241213011350_V1.0
package com.test.A2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.test.A2.entity.*;
import com.test.A2.dto.*;




/**


 */

public interface PersonRepository extends JpaRepository<Person,String>, JpaSpecificationExecutor<Person> {



}

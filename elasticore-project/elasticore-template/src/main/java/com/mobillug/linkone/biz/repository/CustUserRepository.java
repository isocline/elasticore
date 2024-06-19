//ecd:750188284H20240618012928_V0.8
package com.mobillug.linkone.biz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.*;
import com.mobillug.linkone.biz.entity.*;
import com.mobillug.linkone.biz.dto.*;



/**


 */



public interface CustUserRepository extends JpaRepository<CustUser,String> , JpaSpecificationExecutor<LoanCar> {



}

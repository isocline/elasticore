//ecd:604753018H20240805175926_V0.8
package io.elasticore.demo.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.*;
import io.elasticore.demo.crm.entity.*;
import io.elasticore.demo.crm.dto.*;
import io.elasticore.demo.crm.enums.*;




/**


 */



public interface ContractGroupRepository extends JpaRepository<ContractGroup,Integer> , JpaSpecificationExecutor<LoanCar> {

    List<ContractGroup> findAllByOrderByGrpSeqDesc();
    

}

package io.elasticore.demo.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import io.elasticore.demo.crm.entity.*;

import io.elasticore.demo.crm.dto.*;




/**


 */



public interface ContractGroupRepository extends JpaRepository<ContractGroup,Integer> {


    List<ContractGroup> findAllByOrderByGrpSeqDesc();
    


}

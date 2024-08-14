//ecd:423018055H20240806171759_V0.8
package com.mobillug.gateone.biz.repository;

import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;



import com.mobillug.gateone.biz.entity.*;
import com.mobillug.gateone.biz.dto.*;




@Getter
@Service
@AllArgsConstructor
public class GateoneRepositoryHelper {

    private final EntityManager entityManager;

    private final LoginUserRepository loginUser;
    
    private final LoginHistoryRepository loginHistory;
    
    private final ServiceInfoRepository serviceInfo;
    
    private final MappingServiceRepository mappingService;
    


}




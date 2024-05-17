//ecd:1650581584H20240517105348V0.7
package io.elasticore.demo.linkone.repository;


import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;



import io.elasticore.demo.linkone.entity.*;

import io.elasticore.demo.linkone.dto.*;





@Getter
@Service
@AllArgsConstructor
public class RepositoryHelper {


    private final EntityManager entityManager;

    private final AuditEntityRepository auditEntity;
    
    private final PictureInfoRepository pictureInfo;
    
    private final CallInfoRepository callInfo;
    
    private final LoanCarProcessRepository loanCarProcess;
    
    private final CapitalRepository capital;
    
    private final LoanCarRepository loanCar;
    



}




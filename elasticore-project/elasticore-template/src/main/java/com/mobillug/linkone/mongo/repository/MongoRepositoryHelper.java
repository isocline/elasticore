//ecd:-345211580H20240911095941_V1.0
package com.mobillug.linkone.mongo.repository;

import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mobillug.linkone.mongo.entity.*;
import com.mobillug.linkone.mongo.dto.*;



@Getter
@Service
@AllArgsConstructor
public class MongoRepositoryHelper {

    private final EntityManager entityManager;

    private final PremiumCalcResultRepository premiumCalcResult;
    


}




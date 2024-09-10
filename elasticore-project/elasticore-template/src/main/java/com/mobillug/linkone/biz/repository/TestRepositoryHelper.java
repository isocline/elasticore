//ecd:-404891887H20240903204447_V1.0
package com.mobillug.linkone.biz.repository;

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

import com.mobillug.linkone.biz.entity.*;
import com.mobillug.linkone.biz.dto.*;



@Getter
@Service
@AllArgsConstructor
public class TestRepositoryHelper {

    private final EntityManager entityManager;

    private final PremiumCalcResultRepository premiumCalcResult;
    


}




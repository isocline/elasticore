//ecd:-2070007194H20241010182122_V1.0
package com.xsolcorpkorea.elasticore.test.rollup.repository;

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

import com.xsolcorpkorea.elasticore.test.rollup.entity.*;
import com.xsolcorpkorea.elasticore.test.rollup.dto.*;



@Getter
@Service
@AllArgsConstructor
public class Rollup2RepositoryHelper {

    private final EntityManager entityManager;

    private final BaseResidualInfoRepository baseResidualInfo;
    


}




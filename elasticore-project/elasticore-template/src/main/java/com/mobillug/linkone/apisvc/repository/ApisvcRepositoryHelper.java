//ecd:1007776248H20240805175916_V0.8
package com.mobillug.linkone.apisvc.repository;

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



import com.mobillug.linkone.apisvc.entity.*;
import com.mobillug.linkone.apisvc.dto.*;




@Getter
@Service
@AllArgsConstructor
public class ApisvcRepositoryHelper {

    private final EntityManager entityManager;

    private final ReData100Repository reData100;
    


}




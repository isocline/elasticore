//ecd:-1621092712H20240924235117_V1.0
package com.xsolcorpkorea.elasticore.test.dto.repository;

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

import com.xsolcorpkorea.elasticore.test.dto.entity.*;
import com.xsolcorpkorea.elasticore.test.dto.dto.*;



@Getter
@Service
@AllArgsConstructor
public class DtoRepositoryHelper {

    private final EntityManager entityManager;

    private final CustomerRepository customer;
    
    private final CompanyRepository company;
    


}




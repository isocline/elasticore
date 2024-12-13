//ecd:-1967911161H20241213011350_V1.0
package com.test.A1.repository;

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

import com.test.A1.entity.*;
import com.test.A1.dto.*;



@Getter
@Service
@AllArgsConstructor
public class A1RepositoryHelper {

    private final EntityManager entityManager;

    private final CompanyRepository company;
    


}




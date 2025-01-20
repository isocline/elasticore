//ecd:1631461500H20250117173850_V1.0
package com.test.repository;

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

import com.test.entity.*;
import com.test.dto.*;



@Getter
@Service
@AllArgsConstructor
public class A0RepositoryHelper {

    private final EntityManager entityManager;

    private final ProductRepository product;
    


}




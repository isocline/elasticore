//ecd:1532732151H20241213011350_V1.0
package com.test.A2.repository;

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

import com.test.A2.entity.*;
import com.test.A2.dto.*;



@Getter
@Service
@AllArgsConstructor
public class A2RepositoryHelper {

    private final EntityManager entityManager;

    private final PersonRepository person;
    


}




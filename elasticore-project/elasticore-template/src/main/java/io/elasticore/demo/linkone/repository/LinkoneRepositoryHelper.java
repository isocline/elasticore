//ecd:-776164090H20240528142316V0.7
package io.elasticore.demo.linkone.repository;

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



import io.elasticore.demo.linkone.entity.*;
import io.elasticore.demo.linkone.dto.*;




@Getter
@Service
@AllArgsConstructor
public class LinkoneRepositoryHelper {

    private final EntityManager entityManager;

    private final CompanyAreaInfoRepository companyAreaInfo;
    
    private final CompanyRepository company;
    
    private final CustUserRepository custUser;
    


}




//ecd:378967536H20240517105348V0.7
package io.elasticore.demo2.repository;


import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;



import io.elasticore.demo2.entity.*;

import io.elasticore.demo2.dto.*;





@Getter
@Service
@AllArgsConstructor
public class RepositoryHelper {


    private final EntityManager entityManager;

    private final PersonRepository person;
    
    private final CheckDTORepository checkDTO;
    
    private final BaseModelRepository baseModel;
    
    private final PartyRepository party;
    
    private final OrganizationRepository organization;
    
    private final CompanyRepository company;
    
    private final MyEntityRepository myEntity;
    



}




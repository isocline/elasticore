package io.elasticore.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import io.elasticore.demo.entity.*;




/**


 */



public interface PersonRepository extends JpaRepository<Person,Long> {


    List<Person> findAllByDeathRegistrationUserIdAndAttainedAge(String deathRegistrationUserId ,Integer attainedAge);
    
    @Query(nativeQuery=false, value=" select m from Person m"
 		 +  " where"
 		 +  " 1 =1"
 		 +  " and dateOfBirth=:dateOfBirth --if"
 		 +  " and deathRegistrationUserId=:deathRegistrationUserId")
    List<Person> listByDateOfBirthAndDeathRegistrationUserId(@Param("dateOfBirth") String dateOfBirth ,@Param("deathRegistrationUserId") String deathRegistrationUserId);
    


}

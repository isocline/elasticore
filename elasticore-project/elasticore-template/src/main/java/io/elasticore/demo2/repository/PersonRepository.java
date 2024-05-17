//ecd:-973396052H20240517105348V0.7
package io.elasticore.demo2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import io.elasticore.demo2.entity.*;

import io.elasticore.demo2.dto.*;




/**


 */



public interface PersonRepository extends JpaRepository<Person,Long> {


    List<Person> findByDeathRegistrationUserIdAndAttainedAge(String deathRegistrationUserId ,Integer attainedAge);
    
    @Query(nativeQuery=false, value=" select m from Person m"
 		 +  " where"
 		 +  " 1 =1"
 		 +  " and dateOfBirth=:dateOfBirth --if"
 		 +  " and deathRegistrationUserId=:deathRegistrationUserId --if")
    List<Person> getByDateOfBirthAndDeathRegistrationUserId(@Param("dateOfBirth") String dateOfBirth ,@Param("deathRegistrationUserId") String deathRegistrationUserId);
    


}

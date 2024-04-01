package io.elasticore.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;
import io.elasticore.demo.entity.*;



/**


 */

public interface PersonRepository extends JpaRepository<Person,Long> {


    List<Person> findAllByDeathRegistrationUserIDAndAttAinedAge(String deathRegistrationUserId ,Integer attainedAge);
    


}

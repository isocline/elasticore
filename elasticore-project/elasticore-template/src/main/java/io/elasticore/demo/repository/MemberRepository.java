package io.elasticore.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;
import io.elasticore.demo.entity.*;



/**


 */

public interface MemberRepository extends JpaRepository<Member,MemberIdentity> {


    @Query(value="select * from member where username=:username and age=:age",nativeQuery=false)
    Member getMember(String username ,int age);
    


}

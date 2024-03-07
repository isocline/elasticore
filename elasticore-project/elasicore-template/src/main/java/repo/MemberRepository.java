package com.elasticore.sample.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


/**


 */

public interface MemberRepository extends JpaRepository<Member,Long> {


    @Query(value="select * from member where username=:username and age=:age",nativeQuery=false)
    Member getMember(string username ,int age);
    


}

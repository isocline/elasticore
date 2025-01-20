//ecd:36032055H20250120224746_V1.0
package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.test.entity.*;
import com.test.dto.*;




/**


 */

public interface ProductRepository extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {

    @Query(nativeQuery=false, value=" select a from Product where a.engName = :engName or desc like concat('%' , :desc, '%')")
    List<Product> selectByCustom(@Param("engName") String engName ,@Param("desc") String desc);
    
    @Query(nativeQuery=false, value=" select a from Product where a.name = :name or desc like concat('%' , :desc, '%')")
    List<Product> selectByCustom2(@Param("name") String name ,@Param("desc") String desc);
    

}

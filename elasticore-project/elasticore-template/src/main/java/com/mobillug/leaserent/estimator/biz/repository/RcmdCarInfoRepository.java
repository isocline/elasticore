//ecd:653374151H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.mobillug.leaserent.estimator.biz.entity.*;
import com.mobillug.leaserent.estimator.biz.dto.*;
import com.mobillug.leaserent.estimator.biz.enums.*;



/**


 */

public interface RcmdCarInfoRepository extends JpaRepository<RcmdCarInfo,String>, JpaSpecificationExecutor<RcmdCarInfo> {

    @Query(nativeQuery=true, value=" SELECT r.* "
     		 +  " FROM Rcmd_Car_Info r "
     		 +  " JOIN Rcmd_Car_Info_Option_List o ON r.id = o.rcmd_car_info_id "
     		 +  " WHERE r.car_id = :carId "
     		 +  "   AND r.inner_color_id = :innerColorId "
     		 +  "   AND r.outside_color_id = :outsideColorId "
     		 +  "   AND ( "
     		 +  "     SELECT COUNT(*) "
     		 +  "     FROM Rcmd_Car_Info_Option_List o2 "
     		 +  "     WHERE o2.rcmd_car_info_id = r.id "
     		 +  "       AND o2.option_list_id IN :optionList "
     		 +  "   ) = :listSize")
    List<RcmdCarInfo> selectBySameCarOptions(@Param("carId") String carId ,@Param("innerColorId") String innerColorId ,@Param("outsideColorId") String outsideColorId ,@Param("optionList") List<String> optionList ,@Param("listSize") Integer listSize);
    

}

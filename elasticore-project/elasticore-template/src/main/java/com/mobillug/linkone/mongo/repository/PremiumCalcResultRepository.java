//ecd:-423176265H20240911111052_V1.0
package com.mobillug.linkone.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.*;
import com.mobillug.linkone.mongo.entity.*;
import com.mobillug.linkone.mongo.dto.*;




/**


 */

public interface PremiumCalcResultRepository extends MongoRepository<PremiumCalcResult,Long> {



}
